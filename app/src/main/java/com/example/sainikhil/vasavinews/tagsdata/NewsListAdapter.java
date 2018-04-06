//package com.example.sainikhil.vasavinews.tagsdata;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.util.Base64;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import  android.support.v7.widget.RecyclerView.ViewHolder;
//import com.example.sainikhil.vasavinews.R;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.Map;
//
//import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
//
//public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
//
//
//    FirebaseFirestore db;
//    int count =0 ;
//    TextView postednewstitle,postednewsdescription;
//    ImageView postednewsimage;
//    Task<QuerySnapshot> docs;
//    public NewsListAdapter() {
////        db = FirebaseFirestore.getInstance();
////        db.collection("Posts")
////                .get()
////                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////                    @Override
////                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                        if (task.isSuccessful()) {
////                            docs = task;
////                        } else {
////                            docs = null;
////                            Log.d(TAG, "Error getting documents: ", task.getException());
////                        }
////                    }
////                });
//
//    }
//    @NonNull
//    @Override
//    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = (View) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.news_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v) {
//            @Override
//            public String toString() {
//                return super.toString();
//            }
//        }
//        return viewHolder;
//
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
////        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
////        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {
//
//        TextView NewsText = (TextView) holder.mView.findViewById(R.id.news_item_text);
//        ImageView NewsImage = (ImageView) holder.mView.findViewById(R.id.news_image);
//        NewsText.setText("HELp");
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        int flag=0;
//        if(docs==null)
//            return;
//        for (QueryDocumentSnapshot document : docs.getResult()) {
//            if(count==position)
//                flag=1;
//            count++;
//            Map<String,Object> data = document.getData();
//            for (Map.Entry<String, Object> entry : data.entrySet())
//            {
//                if(flag==1)
//                {
//                    String key = entry.getKey();
//                    String val =(String)entry.getValue();
//                    if(key=="Title")
//
//                    if(key=="Image"){
//                    byte[] decodedString = Base64.decode(val, Base64.DEFAULT);
//                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                    NewsImage.setImageBitmap(decodedByte);}
//                }
//            }if(flag==1)
//                break;
//
//            Log.d(TAG, document.getId() + " => " + document.getData());
//        }
//
//
//
//
////        mTextView.setText(mtitle[position]);
////        mImageView.setImageBitmap(mbitmap[position]);
////        final CardView mCardView=(CardView)holder.mView.findViewById(R.id.card_view);
////        mCardView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE );
////                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
////                final View myView = inflater.inflate(R.layout.posted_news_layout, null);
////                //Toast.makeText(view.getContext(), "OnLongClick Called at position " + holder.getAdapterPosition()+mtitle[holder.getAdapterPosition()], Toast.LENGTH_SHORT).show();
////                postednewstitle=(TextView)myView.findViewById(R.id.posted_news_title);
////                postednewsimage=(ImageView)myView.findViewById(R.id.posted_news_image);
////                postednewsdescription=(TextView)myView.findViewById(R.id.posted_news_description);
////                postednewstitle.setText(mtitle[holder.getAdapterPosition()]);
////                postednewsimage.setImageBitmap(mbitmap[holder.getAdapterPosition()]);
////                postednewsdescription.setText(mdescription[holder.getAdapterPosition()]);
////                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////
////                    }
////                });
////                builder.setView(myView);
////                AlertDialog dialog=builder.create();
////                dialog.show();
////
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return count;
//    }
//
//}
