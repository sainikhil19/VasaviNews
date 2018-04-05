package com.example.sainikhil.vasavinews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sainikhil.vasavinews.tagsdata.TagsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //private android.widget.SearchView searchView;
    private SearchView searchView;
    public boolean[] selected_tags;
    String[] selected;
    int position;
    String postDescription = "",postTitle = "",postImage = "";
    DatabaseReference databaseReference;
    int index=0;
    ArrayList<String> title_recycler_list=new ArrayList<String>();
    ArrayList<String> description_recycler_list=new ArrayList<String>();
    ArrayList<Bitmap> bitmap=new ArrayList<Bitmap>();
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private static final int TAGS_ACTIVITY_REQUEST_CODE = 0;
    private static final int POST_NEWS_ACTIVITY_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //searchView=(SearchView)findViewById(R.id.sv);
        //Intent i1=getIntent();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, PostNewsActivity.class);
                startActivityForResult(intent,POST_NEWS_ACTIVITY_REQUEST_CODE);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        SharedPreferences values = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor keyValuesEditor;
//        String pref_tags = values.getString(getString(R.string.key_save_tags));
//        if(pref_tags.compareTo("")==0) {
//
//        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent i= new Intent(this,TagsActivity.class);
        startActivityForResult(i,TAGS_ACTIVITY_REQUEST_CODE);

        String[] tagsArray = getResources().getStringArray(R.array.tags_array);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TagsAdapter adapter = new TagsAdapter( getSupportFragmentManager(),tagsArray);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)



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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==TAGS_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            selected_tags=data.getBooleanArrayExtra("selected_tags");

            int count = 0;
            for (boolean i : selected_tags)
            {
                if(i==true)
                    count++;
            }
            selected = new String[count];
            String[] tagsArray = getResources().getStringArray(R.array.tags_array);
            int current=0;
            for(int i=0;i<tagsArray.length;i++) {
                if (selected_tags[i]) {
                    selected[current] = tagsArray[i];
                    current++;
                }
            }


        }

        if(requestCode==POST_NEWS_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK) {

            position=data.getIntExtra("position",0)-1;

            /*databaseReference = FirebaseDatabase.getInstance().getReference().child("position");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, String> data1 = (Map<String, String>) dataSnapshot.getValue();
                    for (Map.Entry<String, String> e1 : data1.entrySet()) {
                        if (e1.getKey().equals("index_value")) {
                            position = Integer.parseInt(e1.getValue())-1;
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Post"+position);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String,String> data1 =(Map<String,String>) dataSnapshot.getValue();
                    for(Map.Entry<String,String> e1:data1.entrySet()){
                        if (e1.getKey().equals("Post_Description")) {
                            postDescription = e1.getValue();
                        } else if (e1.getKey().equals("Post_Title")) {
                            postTitle = e1.getValue();
                        }else if(e1.getKey().equals("Imagebitmap")){
                            postImage = e1.getValue();
                        }

                    }
//                    if(data.hasExtra("imagebitmap")) {
//                        previewThumbnail = (ImageView)findViewById(R.id.imageView);
//                        bitmap.add(BitmapFactory.decodeByteArray(
//                                data.getByteArrayExtra("imagebitmap"),0,data.getByteArrayExtra("imagebitmap").length));
//                    }
                    //boolean[] post_related_tags=data.getBooleanArrayExtra("post_related_tags");
                    title_recycler_list.add(postTitle);
                    description_recycler_list.add(postDescription);


                    byte[] decodedString = Base64.decode(postImage, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    bitmap.add(decodedByte);
                    String [] title_list = title_recycler_list.toArray(new String[title_recycler_list.size()]);
                    Bitmap [] bitmap_list = bitmap.toArray(new Bitmap[bitmap.size()]);
                    String [] description_list = description_recycler_list.toArray(new String[description_recycler_list.size()]);
                    MyAdapter mAdapter = new MyAdapter(title_list,bitmap_list,description_list);
                    mRecyclerView.setAdapter(mAdapter);
                    Toast.makeText(HomePage.this, "Post Title is: " + postTitle + " Post Description: " + postDescription, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "Cant retrive data", Toast.LENGTH_LONG).show();

                }
            });


            /*DataSnapshot dataSnapshot = null;
            Map<String, String> get_data = (Map<String, String>) dataSnapshot.getValue();
            //String postDescription = null, postTitle = null, postTag = null;
            for (Map.Entry<String, String> e1 : get_data.entrySet()) {
                if (e1.getKey().equals("Post_Description")) {
                        postDescription = e1.getValue();
                } else if (e1.getKey().equals("Post_Title")) {
                        postTitle = e1.getValue();
                } else if (e1.getKey().equals("Post_tag")) {
                        postTag = e1.getValue();
                }
            }*/
//            if (postDescription == null || postTitle == null) {
//                Toast.makeText(HomePage.this, "No text to show", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(HomePage.this, "Post Title is: " + postTitle + " Post Tags: " + postTag + " Post Description: " + postDescription, Toast.LENGTH_LONG).show();
//            }


            //String title=data.getStringExtra("title");
            //String description=data.getStringExtra("description");

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
            //Intent intent=new Intent(this, Personal_Settings.class);
            //startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();





        if (id == R.id.nav_settings) {
            // launch settings activity
            startActivity(new Intent(HomePage.this, SettingsActivity.class));
            return true;
        }
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
