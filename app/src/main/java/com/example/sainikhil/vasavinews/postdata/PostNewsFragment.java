package com.example.sainikhil.vasavinews.postdata;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sainikhil.vasavinews.PostNewsActivity;
import com.example.sainikhil.vasavinews.R;
import com.google.android.flexbox.FlexboxLayout;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipListener;
import moe.feng.common.stepperview.VerticalStepperItemView;


public class PostNewsFragment extends Fragment {


    private static final String TAG = "PostNewsFragment";
    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[4];
    public boolean[] selected_tags= new boolean[40];//contains the selected tags in order of the array specified
    boolean flag=false;
    public PostNewsFragment() {
        // Required empty public constructor
    }



    public void onViewCreated(View view, Bundle savedInstanceState) {


        createChips(view);


        mSteppers[0] = view.findViewById(R.id.info_part);
        mSteppers[1] = view.findViewById(R.id.image_part);
        mSteppers[2] = view.findViewById(R.id.tags_part);
        mSteppers[3]= view.findViewById(R.id.preview_part);
        VerticalStepperItemView.bindSteppers(mSteppers);

        Button mNextBtn0 = view.findViewById(R.id.button_next_0);
        mNextBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[0].nextStep();
            }
        });

        Button mPrevBtn1 = view.findViewById(R.id.button_prev_1);
        mPrevBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[1].prevStep();
            }
        });

        Button mNextBtn1 = view.findViewById(R.id.button_next_1);
        mNextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[1].nextStep();
            }
        });

        Button mNextBtn2 = view.findViewById(R.id.button_next_2);
        mNextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[2].nextStep();
            }
        });

        Button mPrevBtn2 = view.findViewById(R.id.button_prev_2);
        mPrevBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[2].prevStep();
            }
        });

        Button mPrevBtn3 = view.findViewById(R.id.button_prev_3);
        mPrevBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[3].prevStep();
            }
        });

        Button mNextBtn3 = view.findViewById(R.id.button_next_3);
        mNextBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Finish!", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void createChips(View view)
    {
        FlexboxLayout flexbox = (FlexboxLayout) view.findViewById(R.id.flexbox_frag);
        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"));

        final ChipCloud chipCloud = new ChipCloud(getActivity(), flexbox, config);

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

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_news, container, false);
    }






}
