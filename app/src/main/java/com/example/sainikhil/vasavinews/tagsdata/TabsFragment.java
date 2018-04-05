package com.example.sainikhil.vasavinews.tagsdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sainikhil.vasavinews.R;



public class TabsFragment extends Fragment {
    RecyclerView postList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container,false);

        postList = view.findViewById(R.id.post_list);

        return view;
    }


}
