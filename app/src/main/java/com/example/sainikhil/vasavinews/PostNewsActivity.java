package com.example.sainikhil.vasavinews;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.badoualy.stepperindicator.StepperIndicator;
import com.example.sainikhil.vasavinews.postdata.PageAdapter;


public class PostNewsActivity extends AppCompatActivity {
    Button b1, b2;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);
        final ViewPager pager = findViewById(R.id.pager);

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        assert pager != null;
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));


        final StepperIndicator indicator = findViewById(R.id.stepper_indicator);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);
        FragmentManager fm = getSupportFragmentManager();
        final PageAdapter pagerAdapter = new PageAdapter(fm);
        // Here you would declare which page to visit on creation
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(count);
        b1 = (Button) findViewById(R.id.next);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 5) {
                    count++;
                    pager.setCurrentItem(count);
                }
            }
        });
        b2 = (Button) findViewById(R.id.back);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 0) {
                    count--;
                    pager.setCurrentItem(count);
                }
            }
        });
    }
}

