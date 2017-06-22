package com.tomvandesteene.i4m;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tom Van de Steene on 20/06/2017.
 */

public class ProfileAdapter extends ArrayAdapter<Profile> {

    Context context;
    ArrayList<Profile> mProfile;

    public ProfileAdapter(@NonNull Context context, ArrayList<Profile> profiles) {
        super(context, R.layout.activity_list_view, profiles);
        this.context = context;
        this.mProfile=profiles;
    }

    public void add(Profile object) {
        mProfile.add(object);
        super.add(object);
    }

    public void delete(Profile object) {
        mProfile.remove(object);
        super.remove(object);
    }

    @Override
    public int getCount() {
        return mProfile.size();
    }

    @Nullable
    @Override
    public Profile getItem(int position) {
        return mProfile.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get the data item for this position
        Profile data = getItem(position);
        ProfileHolder profileHolder; //view lookup cache stored in tag

        if (convertView == null){

            profileHolder = new ProfileHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.display_profile_row, parent, false);

            profileHolder.ivLvPhoto = (ImageView) convertView.findViewById(R.id.iv_lv_photo);
            profileHolder.tvLvFirstName = (TextView) convertView.findViewById(R.id.tv_lv_first_name);
            profileHolder.tvLvLastName = (TextView) convertView.findViewById(R.id.tv_lv_last_name);
            profileHolder.tvLvTelephone = (TextView) convertView.findViewById(R.id.tv_lv_telephone);
            profileHolder.tvLvEmail = (TextView) convertView.findViewById(R.id.tv_lv_email);
            convertView.setTag(profileHolder);
        }
        else{
            profileHolder = (ProfileHolder) convertView.getTag();
        }

        profileHolder.ivLvPhoto.setImageBitmap(convertToBitmap(data.getPhoto()));
        profileHolder.tvLvFirstName.setText(data.getFirstName());
        profileHolder.tvLvLastName.setText(data.getLastName());
        profileHolder.tvLvTelephone.setText(data.getTelephone());
        profileHolder.tvLvEmail.setText(data.getEmail());

        return convertView;
    }

    static class ProfileHolder{
        ImageView ivLvPhoto;
        TextView tvLvFirstName, tvLvLastName, tvLvTelephone, tvLvEmail;
    }
    //get bitmap image from byte array
    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}
