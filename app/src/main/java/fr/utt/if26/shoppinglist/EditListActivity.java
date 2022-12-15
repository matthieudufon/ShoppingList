package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class EditListActivity extends AppCompatActivity {

    public static final String DELETE_LISTE = "DELETE";

    private int id;
    private long date = 0;

    private EditText editTextNom;
    private EditText editTextLieu;
    private CalendarView calendarViewDate;
    private ImageButton imageButtonDelete;
    private ImageButton imageButtonUpdate;

    private ListeViewModel listeViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        id = getIntent().getIntExtra("ID", 0);

        editTextNom = (EditText) findViewById(R.id.activity_edit_list_tv_nom);
        editTextLieu = (EditText) findViewById(R.id.activity_edit_list_tv_lieu);
        calendarViewDate = (CalendarView) findViewById(R.id.activity_edit_list_cv);
        imageButtonUpdate = (ImageButton) findViewById(R.id.activity_edit_list_bt_update);
        imageButtonDelete = (ImageButton) findViewById(R.id.activity_edit_list_bt_delete);

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);

        listeViewModel.getListeById(id).observe(this, options -> {
            try {
                editTextNom.setText(options.getNom());
                editTextLieu.setText(options.getLieu());
                calendarViewDate.setDate(options.getDate().getTime());
            } catch (NullPointerException e) {
                e.printStackTrace();
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
}