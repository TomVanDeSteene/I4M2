package com.tomvandesteene.i4m;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AddProfileActivity extends FragmentActivity implements Language1Dialog.Language1PickerDialogFragmentListener {

    private static final int CAMERA_REQUEST = 1888;

    private ImageView ivPhoto;

    private AppCompatButton btn1stLang;
    private AppCompatButton btn2ndLang;
    private AppCompatButton btn3rdLang;
    private AppCompatButton btn4thLang;

    private TextView tv1stLang;
    private TextView tv2ndLang;
    private TextView tv3rdLang;
    private TextView tv4thLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        initViews();

        ivPhoto.setOnClickListener(imageClickListener);

        btn1stLang.setOnClickListener(languageClickListener);

    }

    public void initViews(){

        ivPhoto = (ImageView) findViewById(R.id.iv_photo);

        btn1stLang = (AppCompatButton)findViewById(R.id.btn_1st_language);
        btn2ndLang = (AppCompatButton)findViewById(R.id.btn_2nd__language);
        btn3rdLang = (AppCompatButton)findViewById(R.id.btn_3rd_language);
        btn4thLang = (AppCompatButton)findViewById(R.id.btn_4th_language);

        tv1stLang = (TextView)findViewById(R.id.tv_1st_language);
        tv2ndLang = (TextView) findViewById(R.id.tv_2nd_language);
        tv3rdLang = (TextView) findViewById(R.id.tv_3rd_language);
        tv4thLang = (TextView) findViewById(R.id.tv_4th_language);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(photo);
        }
    }

    View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    };

    View.OnClickListener languageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Language1Dialog().show(getSupportFragmentManager(), "language1dialog");
        }
    };

    @Override
    public void language1Picked(String language1) {
        tv1stLang.setText(language1);
    }

}
