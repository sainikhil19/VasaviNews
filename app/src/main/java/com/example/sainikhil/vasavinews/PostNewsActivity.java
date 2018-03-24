package com.example.sainikhil.vasavinews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.sainikhil.vasavinews.postdata.PostNewsFragment;
import com.google.android.flexbox.FlexboxLayout;


public class PostNewsActivity extends AppCompatActivity {



    private Fragment mPostNewsFragment = new PostNewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);



        if (savedInstanceState == null) {
            replaceFragment(mPostNewsFragment);
        }

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}

