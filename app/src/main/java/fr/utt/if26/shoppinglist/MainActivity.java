package fr.utt.if26.shoppinglist;

import static fr.utt.if26.shoppinglist.holder.ListeViewHolder.CONTENT_LIST_ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Menu menu;

    private ListeViewModel listeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigator_layout);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        switch (new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                navigationView.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.banana_color_strong));
                navigationView.setBackgroundColor(getResources().getColor(R.color.banana_color));
                toolbar.setBackgroundColor(getResources().getColor(R.color.banana_color));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                navigationView.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.watermelon_color_strong));
                navigationView.setBackgroundColor(getResources().getColor(R.color.watermelon_color));
                toolbar.setBackgroundColor(getResources().getColor(R.color.watermelon_color));
                break;
            default:
                navigationView.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.apple_color_strong));
                navigationView.setBackgroundColor(getResources().getColor(R.color.apple_color));
                toolbar.setBackgroundColor(getResources().getColor(R.color.apple_color));
                break;
        }


        menu = navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.listes);

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.aliments:
                selectedFragment = new AlimentsFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.conso:
                selectedFragment = new ConsoFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.favorite:
                selectedFragment = new FavoriteFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.param:
                selectedFragment = new ParamFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.about:
                selectedFragment = new AboutFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                selectedFragment = new MainFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment, selectedFragment).commit();
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONTENT_LIST_ACTIVITY && resultCode == RESULT_OK) {
            listeViewModel.deleteListeById(data.getIntExtra(ListeContentActivity.DELETE_LISTE, 0));
        }
    }

}