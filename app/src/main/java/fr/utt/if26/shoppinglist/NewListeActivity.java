package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewListeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NOM = "REPLYNOM";
    public static final String EXTRA_REPLY_LIEU = "REPLYLIEU";
    public static final String EXTRA_REPLY_DATE = "REPLYDATE";

    private EditText editTextNom;
    private EditText editTextLieu;
    private CalendarView calendarView;
    private Button buttonAdd;
    private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList));
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
                    Date dateResult = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth + "/" + (month + 1)  + "/" + year);
                    date = dateResult.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonAdd.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextNom.getText()) || TextUtils.isEmpty(editTextLieu.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String nom = editTextNom.getText().toString();
                String lieu = editTextLieu.getText().toString();

                replyIntent.putExtra(EXTRA_REPLY_NOM, nom);
                replyIntent.putExtra(EXTRA_REPLY_LIEU, lieu);
                replyIntent.putExtra(EXTRA_REPLY_DATE, this.date);
                setResult(RESULT_OK, replyIntent);
            }
            finish();

        });
    }
}