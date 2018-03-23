package com.example.sainikhil.vasavinews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.SearchView;

import com.example.sainikhil.vasavinews.menuactions.MenuCreate;
import com.example.sainikhil.vasavinews.tagsdata.TagsAdapter;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //private android.widget.SearchView searchView;
    private SearchView searchView;
    public boolean[] selected_tags;
    private String[] selected;
    private String[] tagsArray;
    private TabLayout tabLayout;
    private static final int TAGS_ACTIVITY_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //searchView=(SearchView)findViewById(R.id.sv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, PostNewsActivity.class);
                startActivity(intent);
            }
        });
        /*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Intent i= new Intent(this,TagsActivity.class);
        startActivityForResult(i,TAGS_ACTIVITY_REQUEST_CODE);

        tagsArray = getResources().getStringArray(R.array.tags_array);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TagsAdapter adapter = new TagsAdapter(getSupportFragmentManager(), tagsArray);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //generating menu items
        MenuCreate menuCreate = new MenuCreate(navigationView,tagsArray,tabLayout);
        menuCreate.addMenu();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==TAGS_ACTIVITY_REQUEST_CODE)
        {
            selected_tags=data.getBooleanArrayExtra("selected_tags");
           // TextView dummy_text=(TextView)findViewById(R.id.dummy_text);

            int count = 0;
            for (boolean i : selected_tags)
            {
                if(i==true)
                    count++;
            }
            selected = new String[count];
            String[] tagsArray = getResources().getStringArray(R.array.tags_array);
            int current=0;
            for(int i=0;i<tagsArray.length;i++)
            {
                if(selected_tags[i])
                {
                    selected[current]=tagsArray[i];
                    current++;
                }
            }
            //dummy_text.setText(java.util.Arrays.toString(selected_tags));


        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        /*searchView= (android.widget.SearchView) menu.findItem(R.id.action_search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnCloseListener((android.widget.SearchView.OnCloseListener) this);
        searchView.requestFocus();*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();





        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean[] getSelected_tags()
    {
        return selected_tags;
    }



         /*
        uncomment after implementing tagslist adapter
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        TagAdapter adapter = new TagAdapter(this, getSupportFragmentManager());


        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);


        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);


        */



}
