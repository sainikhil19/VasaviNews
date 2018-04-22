package com.example.sainikhil.vasavinews.postdata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sainikhil.vasavinews.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipListener;
import moe.feng.common.stepperview.VerticalStepperItemView;

import static android.app.Activity.RESULT_OK;

public class PostNewsFragment extends Fragment {

    FirebaseFirestore db;
    private static final String TITLE_KEY = "Title";
    private static final String DESC_KEY = "Description";
    private static final String IMAGE_KEY = "Image";
    private static final String TAGS_KEY = "Tags";
    private static final String TIME_KEY = "Time";

    private static final String TAG = "PostNewsFragment";
    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    static int position=0;
    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[4];
    public boolean[] selected_tags= new boolean[40];//contains the selected tags in order of the array specified
    boolean flag=false;
    String title="",description="";
    ImageView imageview;
    TextView news_title_preview,news_details_preview;
    EditText headline,details;
    Bitmap bitmap;
    Button imagefromstock;
    DatabaseReference databaseReference;
    ImageButton imagefromgallery,imagefromcamera;
    public PostNewsFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        headline = (EditText) view.findViewById(R.id.news_title);
        details = (EditText) view.findViewById(R.id.news_details);
        news_title_preview=(TextView)view.findViewById(R.id.news_title_preview);
        news_details_preview=(TextView)view.findViewById(R.id.news_details_preview);
        imageview = (ImageView)view.findViewById(R.id.previewimageView);
        imagefromgallery=(ImageButton)view.findViewById(R.id.button_from_gallery);
        imagefromcamera=(ImageButton)view.findViewById(R.id.button_from_camera);
        imagefromstock=(Button)view.findViewById(R.id.button_from_stock);
        mSteppers[0] = view.findViewById(R.id.info_part);
        mSteppers[1] = view.findViewById(R.id.image_part);
        mSteppers[2] = view.findViewById(R.id.tags_part);
        mSteppers[3]= view.findViewById(R.id.preview_part);
        VerticalStepperItemView.bindSteppers(mSteppers);

        Button mNextBtn0 = view.findViewById(R.id.button_next_0);
        mNextBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTitleAndDescripton(view);
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

                imageview.buildDrawingCache();
                bitmap= imageview.getDrawingCache();
                ByteArrayOutputStream bytearrayoutputstream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearrayoutputstream);

                byte[] imageBytes =bytearrayoutputstream.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


                db = FirebaseFirestore.getInstance();
                Map < String, Object > newPost = new HashMap < > ();
                newPost.put(TITLE_KEY, title);
                newPost.put(DESC_KEY, description);
                newPost.put(IMAGE_KEY, imageString);
                newPost.put(TAGS_KEY, "test");
                newPost.put(TIME_KEY, "test");

                db.collection("Posts").add(newPost)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });


//                Map<String, Object> data = new HashMap<>();
//                data.put("name", "Tokyo");
//                data.put("country", "Japan");
//                ApiFuture<DocumentReference> addedDocRef = db.collection("cities").add(data);

/*
                databaseReference = FirebaseDatabase.getInstance().getReference().child("position");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            Map<String, String> data1 = (Map<String, String>) dataSnapshot.getValue();
                            for (Map.Entry<String, String> e1 : data1.entrySet()) {
                                if (e1.getKey().equals("index_value")) {
                                    position = Integer.valueOf(e1.getValue());
                                    Map<String, String> dataMap_position = new HashMap<String, String>();
                                    dataMap_position.put("index_value",String.valueOf(position+1));
                                    databaseReference.setValue(dataMap_position);

                                    Toast.makeText(getContext(),String.valueOf(dataMap_position),Toast.LENGTH_SHORT).show();
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Post"+position);
                                    HashMap<String, String> dataMap = new HashMap<String, String>();
                                    dataMap.put("Post_Description", description);
                                    dataMap.put("Post_Title", title);
                                    dataMap.put("Imagebitmap", imageString);
                                    databaseReference.setValue(dataMap);
                                    Intent intent=new Intent();
//                                    intent.putExtra("title",title);
//                                    intent.putExtra("description",description);
//                                    intent.putExtra("imagebitmap",bytearrayoutputstream.toByteArray());
//                                    intent.putExtra("post_related_tags",selected_tags);
//                                    intent.putExtra("position",position);
                                    getActivity().setResult(RESULT_OK, intent);
                                    getActivity().finish();
                                }

                            }
                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
*/
                getActivity().finish();
            }
        });

        getImage(view);
        createChips(view);
    }
    //get the edit text data (title and description)
    private void getTitleAndDescripton(View view)
    {
        title=headline.getText().toString();
        description=details.getText().toString();
        news_title_preview.setText(title);
        news_details_preview.setText(description);
    }

    //choose image from gallery
    private void getImage(View view) {
        imagefromgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromGallery(view);

            }
        });
        imagefromcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageUsingCamera(view);
            }
        });
        imagefromstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromStock(view);
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
    private void pickImageFromGallery(View view)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

    }

    private void pickImageUsingCamera(View view)
    {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, CAMERA_REQUEST);

    }

    private void pickImageFromStock(View view)
    {
        imageview.setImageDrawable(getResources().getDrawable(R.drawable.logo_vce));
        Toast.makeText(view.getContext(), "image Selected", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        imageview.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
                case CAMERA_REQUEST:
                    try {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        imageview.setImageBitmap(bitmap);
                    } catch (Exception e1) {
                        Log.i("TAG", "Some exception " + e1);
                    }
                    break;
            }
            Toast.makeText(getContext(),  "image Selected", Toast.LENGTH_SHORT).show();
        }

    }

}
