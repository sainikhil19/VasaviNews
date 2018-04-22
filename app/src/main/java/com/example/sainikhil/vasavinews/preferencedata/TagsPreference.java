package com.example.sainikhil.vasavinews.preferencedata;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.sainikhil.vasavinews.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipListener;

import static android.content.ContentValues.TAG;

public class TagsPreference extends DialogPreference {
    private static class SavedState extends BaseSavedState {
        // Member that holds the setting's value
        // Change this data type to match the type saved by your Preference
        String value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            // Get the current preference's value
            value = source.readString();  // Change this to read the appropriate data type
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            // Write the preference's value
            dest.writeString(value);  // Change this to write the appropriate data type
        }

        // Standard creator object using an instance of this class
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    private boolean flag=false;
    private boolean[] selected_tags = new boolean[28];
    String tags="";
    String[] tags_array;
    int len = 0;
    public TagsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDialogLayoutResource(R.layout.tags_add);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        FlexboxLayout flexbox = (FlexboxLayout) view.findViewById(R.id.flexbox_frag);
        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"));

        final ChipCloud chipCloud = new ChipCloud(view.getContext(), flexbox, config);

        final String[] tagsArray = view.getContext().getResources().getStringArray(R.array.tags_array);
        tags_array=tagsArray;
        len=tagsArray.length;
        chipCloud.addChip("All News");
        chipCloud.addChips(tagsArray);
        for(int i=0;i<len+1;i++)
        {
            if(selected_tags[i])
                chipCloud.setChecked(i);
            else
                chipCloud.deselectIndex(0);
        }
        chipCloud.setListener(new ChipListener() {
            @Override
            public void chipCheckedChange(int index, boolean checked, boolean userClick) {
                if (userClick) {
                    Log.d(TAG, String.format("chipCheckedChange Label at index: %d checked: %s", index, checked));
                    if (index == 0) {

                        for (int i = 0; i < tagsArray.length; i++) {
                            if (!flag) {
                                selected_tags[i] = true;
                                chipCloud.setChecked(i + 1);

                            } else {
                                selected_tags[i] = false;
                                chipCloud.deselectIndex(i + 1);
                            }
                        }
                        flag = !flag;
                    } else {
                        selected_tags[index] = !selected_tags[index];
                        flag = false;
                        chipCloud.deselectIndex(0);
                    }
                }
            }
        });
        super.onBindDialogView(view);

    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            int count = 0;
            for (boolean i : selected_tags)
            {
                if(i)
                    count++;
            }
            tags="";
            for(int i=0;i<tags_array.length+1;i++)
            {
                if(selected_tags[i])
                    tags+="1";
                else
                    tags+="0";
            }


            persistString(tags);

        }
    }
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            // Restore existing state
            tags = this.getPersistedString("");
            len = 27;
            for(int i=0;i<len+1;i++)
            {
                if(tags.charAt(i)=='1')
                    selected_tags[i]=true;
                else
                    selected_tags[i]=false;
            }
        } else {
            // Set default state from the XML attribute
            tags = "";
            for(int i=0;i<len+1;i++)
            {
                if(selected_tags[i])
                    tags+="1";
                else
                    tags+="0";
            }
            persistString(tags);
        }
    }
}
