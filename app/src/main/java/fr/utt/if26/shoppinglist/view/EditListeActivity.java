package fr.utt.if26.shoppinglist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.SharedPreferencesManager;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class EditListeActivity extends AppCompatActivity {

    public static final String DELETE_LISTE = "DELETE";

    private int id;
    private long date = 0;

    private EditText editTextNom;
    private EditText editTextLieu;
    private CalendarView calendarViewDate;
    private Button imageButtonDelete;
    private Button imageButtonUpdate;
    private Toolbar toolbar;

    private ListeViewModel listeViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        id = getIntent().getIntExtra("ID", 0);

        editTextNom = (EditText) findViewById(R.id.activity_edit_list_tv_nom);
        editTextLieu = (EditText) findViewById(R.id.activity_edit_list_tv_lieu);
        calendarViewDate = (CalendarView) findViewById(R.id.activity_edit_list_cv);
        imageButtonUpdate = findViewById(R.id.activity_edit_list_bt_update);
        imageButtonDelete = findViewById(R.id.activity_edit_list_bt_delete);
        toolbar = findViewById(R.id.activity_edit_list_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        switch(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                imageButtonDelete.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                imageButtonUpdate.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.banana_color_strong));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                imageButtonDelete.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                imageButtonUpdate.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.watermelon_color_strong));
                break;
            default:
                imageButtonDelete.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                imageButtonUpdate.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                toolbar.setBackgroundColor(getResources().getColor(R.color.apple_color_strong));
                break;
        }

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);

        listeViewModel.getListeById(id).observe(this, options -> {
            try {
                editTextNom.setText(options.getNom());
                editTextLieu.setText(options.getLieu());
                calendarViewDate.setDate(options.getDate().getTime());
            } catch (NullPointerException e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Liste supprimée",
                        Toast.LENGTH_LONG).show();
            }
        });

        calendarViewDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

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

        imageButtonUpdate.setOnClickListener(view -> {
            listeViewModel.getListeById(id).observe(this, options -> {
                options.setNom(editTextNom.getText().toString());
                options.setLieu(editTextLieu.getText().toString());
                if (date != 0) {
                    options.setDate(new Date(date));
                }
                listeViewModel.update(options);
            });
            finish();
        });

        imageButtonDelete.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra(DELETE_LISTE, id);
            setResult(RESULT_OK, intent);
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