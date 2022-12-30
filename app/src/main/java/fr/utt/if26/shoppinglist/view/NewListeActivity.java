package fr.utt.if26.shoppinglist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.SharedPreferencesManager;

public class NewListeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NOM = "REPLYNOM";
    public static final String EXTRA_REPLY_LIEU = "REPLYLIEU";
    public static final String EXTRA_REPLY_DATE = "REPLYDATE";

    private EditText editTextNom;
    private EditText editTextLieu;
    private CalendarView calendarView;
    private Button buttonAdd;
    private Toolbar toolbar;
    private long date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_liste);
        editTextNom = findViewById(R.id.new_liste_activity_et1);
        editTextLieu = findViewById(R.id.new_liste_activity_et2);
        calendarView = (CalendarView) findViewById(R.id.new_liste_activity_cv);
        buttonAdd = findViewById(R.id.new_liste_activity_bt);
        toolbar = findViewById(R.id.new_liste_activity_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        switch(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                buttonAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.banana_color_strong));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                buttonAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.watermelon_color_strong));
                break;
            default:
                buttonAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.apple_color_strong));
                break;
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}