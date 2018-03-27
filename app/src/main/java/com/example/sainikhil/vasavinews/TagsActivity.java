package com.example.sainikhil.vasavinews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;
import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipListener;

public class TagsActivity extends AppCompatActivity {

    public boolean[] selected_tags= new boolean[40];//contains the selected tags in order of the array specified
    private static final String TAG = "TagsActivity";
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tags);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        FlexboxLayout flexbox = (FlexboxLayout) findViewById(R.id.flexbox);
        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"));

        final ChipCloud chipCloud = new ChipCloud(this, flexbox, config);

        final String[] tagsArray = getResources().getStringArray(R.array.tags_array);
        chipCloud.addChip("All News");
        chipCloud.addChips(tagsArray);
        chipCloud.setListener(new ChipListener() {
            @Override
            public void chipCheckedChange(int index, boolean checked, boolean userClick) {
                if(userClick) {
                    Log.d(TAG, String.format("chipCheckedChange Label at index: %d checked: %s", index, checked));
                    if(index==0)
                    {

                        for(int i=0;i<tagsArray.length; i++) {
                            if(!flag)
                            {
                                selected_tags[i]= true;
                                chipCloud.setChecked(i + 1);

                            }
                            else
                            {
                                selected_tags[i]=false;
                                chipCloud.deselectIndex(i+1);
                            }
                        }
                        flag=!flag;
                    }
                    else {
                        selected_tags[index] = !selected_tags[index];
                        flag=false;
                        chipCloud.deselectIndex(0);
                    }
                }
            }
        });

        Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected_tags", selected_tags);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}