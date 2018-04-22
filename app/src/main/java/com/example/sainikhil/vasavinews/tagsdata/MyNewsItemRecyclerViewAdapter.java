package com.example.sainikhil.vasavinews.tagsdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sainikhil.vasavinews.R;
import com.example.sainikhil.vasavinews.tagsdata.NewsItemFragment.OnListFragmentInteractionListener;
import com.example.sainikhil.vasavinews.tagsdata.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNewsItemRecyclerViewAdapter extends RecyclerView.Adapter<MyNewsItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mTitles;
    private final List<String> mImages;
    private final OnListFragmentInteractionListener mListener;

    public MyNewsItemRecyclerViewAdapter(List<String> titles,List<String> images, OnListFragmentInteractionListener listener) {
        mTitles = titles;
        mImages = images;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        byte[] decodedString = Base64.decode(mImages.get(position), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.mImageView. setImageBitmap(decodedByte);
        holder.mTitleView.setText(mTitles.get(position));
       // holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                   // mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final ImageView mImageView;
       // public final TextView mContentView;
       // public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.news_item_text);
            mImageView = (ImageView) view.findViewById(R.id.news_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" +/* mContentView.getText()*/  "'";
        }
    }


}
