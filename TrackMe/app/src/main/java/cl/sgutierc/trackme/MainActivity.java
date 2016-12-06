package cl.sgutierc.trackme;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

import cl.sgutierc.trackme.activity.MapActivity;
import cl.sgutierc.trackme.view.ListableFragment;


public class MainActivity extends AppCompatActivity {
    private List<ListableFragment> fragmentItems;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listview = (ListView) findViewById(R.id.navList);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.addDrawerListener(mToggle);


        {
            fragmentItems = new ArrayList<>();

            ListableFragment mapaActivity = new MapActivity();

            /*fragmentItems.add(new GastoActivity());
            fragmentItems.add(new PresupuestoActivity());
            fragmentItems.add(new CategoriaActivity());*/
            fragmentItems.add(mapaActivity);

            ArrayAdapter<ListableFragment> itemsAdapter = new ArrayAdapter<ListableFragment>(this, android.R.layout.simple_list_item_1, fragmentItems);
            listview.setAdapter(itemsAdapter);
            loadFragment(mapaActivity);
            listview.setItemChecked(fragmentItems.size() - 1, true);
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                loadFragment(fragmentItems.get(position));
                listview.setItemChecked(position, true);
                drawerLayout.closeDrawers();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleDrawer() {
        View view = findViewById(R.id.drawerLayout);
        boolean isOpen = drawerLayout.isDrawerOpen(view);
        if (isOpen)
            drawerLayout.closeDrawer(view);
        else
            drawerLayout.openDrawer(view);
    }

    private void loadFragment(ListableFragment lfragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, lfragment)
                .commit();

        this.setTitle(lfragment.getTitle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mToggle.onConfigurationChanged(newConfig);
    }
}

