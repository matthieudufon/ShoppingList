package fr.utt.if26.shoppinglist;

import android.content.Context;
import android.util.JsonReader;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.utt.if26.shoppinglist.converters.DateConverter;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

@Database(entities = {AlimentEntity.class, ListeEntity.class, ComposeEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDAO appDAO();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                AppDAO dao = INSTANCE.appDAO();
                dao.deleteAllCompose();
                dao.deleteAllListe();
                dao.deleteAllAliment();

                try {
                    URL url = new URL("https://koumoul.com/data-fair/api/v1/datasets/agribalyse-synthese/lines?&select=Nom_du_Produit_en_Fran%C3%A7ais,Groupe_d%27aliment&size=9999");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("accept", "application/json");
                    connection.setRequestMethod("GET");
                    InputStream responseStream = connection.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));
                    JsonReader jsonReader = new JsonReader(in);
                    List<AlimentEntity> alimentList = new ArrayList<AlimentEntity>();

                    jsonReader.beginObject();
                    while(jsonReader.hasNext()) {
                        String name = jsonReader.nextName();
                        if (name.equals("results")) {
                            jsonReader.beginArray();
                            while(jsonReader.hasNext()) {
                                String nom = "";
                                String categorie = "";
                                jsonReader.beginObject();
                                while(jsonReader.hasNext()) {
                                    String otherName = jsonReader.nextName();

                                    if (otherName.equals("Groupe_d'aliment")) {
                                        categorie = jsonReader.nextString();
                                    } else if (otherName.equals("Nom_du_Produit_en_Français")) {
                                        nom = jsonReader.nextString();
                                    } else {
                                        jsonReader.skipValue();
                                    }
                                }
                                jsonReader.endObject();
                                alimentList.add(new AlimentEntity(nom, categorie));
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    in.close();
                    connection.disconnect();
                    for (AlimentEntity aliment : alimentList) {
                        dao.insert(aliment);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ListeEntity liste = new ListeEntity("Courses du samedi", "Supermarché", new Date());
                dao.insert(liste);

                ComposeEntity compose1 = new ComposeEntity(1, 1, 1, 1);
                ComposeEntity compose2 = new ComposeEntity(20, 1, 1, 1);
                ComposeEntity compose3 = new ComposeEntity(14, 1, 1, 1);
                ComposeEntity compose4 = new ComposeEntity(2, 1, 1, 1);
                ComposeEntity compose5 = new ComposeEntity(4, 1, 1, 1);
                ComposeEntity compose6 = new ComposeEntity(7, 1, 1, 1);
                ComposeEntity compose7 = new ComposeEntity(64, 1, 1, 1);
                dao.insert(compose1);
                dao.insert(compose2);
                dao.insert(compose3);
                dao.insert(compose4);
                dao.insert(compose5);
                dao.insert(compose6);
                dao.insert(compose7);
            });
        }
    };


}
