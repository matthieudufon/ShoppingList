package fr.utt.if26.shoppinglist;

import static fr.utt.if26.shoppinglist.holder.ListeViewHolder.CONTENT_LIST_ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

import fr.utt.if26.shoppinglist.adapters.ListeListAdapter;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    RecyclerView recyclerView;

    private ListeViewModel listeViewModel;
    public static final int NEW_LISTE_ACTIVITY_REQUEST_CODE = 1;
    public static final int DEL_LISTE_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigator_layout);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        menu = navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        recyclerView = findViewById(R.id.activity_main_rv);
        final ListeListAdapter adapter = new ListeListAdapter(new ListeListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        listeViewModel.getAllListes().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.activity_main_fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewListeActivity.class);
            startActivityForResult(intent, NEW_LISTE_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_LISTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ListeEntity liste = null;
            liste = new ListeEntity(
                    data.getStringExtra(NewListeActivity.EXTRA_REPLY_NOM),
                    data.getStringExtra(NewListeActivity.EXTRA_REPLY_LIEU),
                    new Date(data.getLongExtra(NewListeActivity.EXTRA_REPLY_DATE, new Date().getTime()))
            );
            listeViewModel.insert(liste);
        } else if (requestCode == CONTENT_LIST_ACTIVITY && resultCode == RESULT_OK) {
            listeViewModel.deleteListeById(data.getIntExtra(ListeContentActivity.DELETE_LISTE, 0));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.aliments:
                // startActivity(new Intent())
                break;
            default:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return false;
    }
}