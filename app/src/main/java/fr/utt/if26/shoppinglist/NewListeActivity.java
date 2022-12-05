package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewListeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NOM = "com.example.android.listelistsql.REPLYNOM";
    public static final String EXTRA_REPLY_LIEU = "com.example.android.listelistsql.REPLYLIEU";

    private EditText editTextNom;
    private EditText editTextLieu;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_liste);
        editTextNom = findViewById(R.id.new_liste_activity_et1);
        editTextLieu = findViewById(R.id.new_liste_activity_et2);
        buttonAdd = findViewById(R.id.new_liste_activity_bt);

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
                setResult(RESULT_OK, replyIntent);
            }
            finish();

        });
    }
}