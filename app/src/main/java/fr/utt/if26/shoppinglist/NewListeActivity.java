package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewListeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NOM = "com.example.android.listelistsql.REPLYNOM";
    public static final String EXTRA_REPLY_LIEU = "com.example.android.listelistsql.REPLYLIEU";
    public static final String EXTRA_REPLY_DATE = "com.example.android.listelistsql.REPLYDATE";

    private EditText editTextNom;
    private EditText editTextLieu;
    private CalendarView calendarView;
    private Button buttonAdd;
    private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_liste);
        editTextNom = findViewById(R.id.new_liste_activity_et1);
        editTextLieu = findViewById(R.id.new_liste_activity_et2);
        calendarView = (CalendarView) findViewById(R.id.new_liste_activity_cv);
        buttonAdd = findViewById(R.id.new_liste_activity_bt);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    Date dateResult = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth + "/" + month + "/" + year);
                    date = dateResult.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonAdd.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextNom.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String nom = editTextNom.getText().toString();
                String lieu;
                if (TextUtils.isEmpty(editTextLieu.getText())) {
                    lieu = "";
                } else {
                    lieu = editTextLieu.getText().toString();
                }
                replyIntent.putExtra(EXTRA_REPLY_NOM, nom);
                replyIntent.putExtra(EXTRA_REPLY_LIEU, lieu);
                replyIntent.putExtra(EXTRA_REPLY_DATE, this.date);
                Log.d("DEBUG-MATTHIEU", date + " long");
                setResult(RESULT_OK, replyIntent);
            }
            finish();

        });
    }
}