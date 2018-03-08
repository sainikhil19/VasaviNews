package com.example.sainikhil.vasavinews.tagsdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sainikhil.vasavinews.R;

/**
 * Created by Omar on 07-Mar-18.
 */

public class TabsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container,false);
        return view;
    }


}
