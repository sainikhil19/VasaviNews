package com.example.sainikhil.vasavinews.tagsdata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sainikhil.vasavinews.R;
import com.example.sainikhil.vasavinews.tagsdata.dummy.DummyContent;
import com.example.sainikhil.vasavinews.tagsdata.dummy.DummyContent.DummyItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final int MIN = 0, MAX = 10000;
    private static final String ARG_COLUMN_COUNT = "column-count";
    MyNewsItemRecyclerViewAdapter mAdapter;
    List<String> titles,images;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsItemFragment newInstance(int columnCount) {
        NewsItemFragment fragment = new NewsItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        titles=new ArrayList<String>();
        images = new ArrayList<String>();
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        mAdapter = new MyNewsItemRecyclerViewAdapter(titles,images, mListener);
        recyclerView.setAdapter(mAdapter);
        db.collection("Posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public static final String TAG = "Message " ;

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int latest = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> data = document.getData();

                                for (Map.Entry<String, Object> entry : data.entrySet())
                                {

                                        String key = entry.getKey();
                                        String val =(String)entry.getValue();
                                        if(key.equals("Title"))
                                            titles.add(val);
                                        if(key.equals("Image"))
                                            images.add(val);


                                }
                                addToList();

                                latest++;
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        // Set the adapter




        return view;
    }

    public void addToList()
    {
        int randomVal = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
        titles.add(String.valueOf(randomVal));
        titles.remove(titles.size()-1);
        images.add(String.valueOf(randomVal));
        images.remove(images.size()-1);
        mAdapter.notifyDataSetChanged();
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
