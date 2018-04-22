//package com.example.sainikhil.vasavinews.tagsdata;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.sainikhil.vasavinews.PostNewsActivity;
//import com.example.sainikhil.vasavinews.R;
//
//
//
//public class TabsFragment extends Fragment {
//    RecyclerView postList;
//    String pageTitle="";
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.news_fragment, container,false);
//
//        postList = view.findViewById(R.id.post_list);
////        pageTitle = getArguments().getString("Title");
//        NewsListAdapter mAdapter = new NewsListAdapter();
//        postList.setAdapter(mAdapter);
//        new RecyclerView().set
//        postList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//        {
//            Intent intent = new Intent(getContext(), PostNewsActivity.class);
//        }
//        return view;
//    }
//
//
//}
