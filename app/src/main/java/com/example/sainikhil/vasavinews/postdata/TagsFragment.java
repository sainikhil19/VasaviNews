package com.example.sainikhil.vasavinews.postdata;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sainikhil.vasavinews.R;
import com.google.android.flexbox.FlexboxLayout;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;


public class TagsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags, null);

        FlexboxLayout flexbox = (FlexboxLayout) view.findViewById(R.id.flexbox);
        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"));

        ChipCloud chipCloud = new ChipCloud(getActivity(), flexbox, config);

        final String[] tagsArray = getResources().getStringArray(R.array.tags_array);
        chipCloud.addChips(tagsArray);

        return view;

    }
}
