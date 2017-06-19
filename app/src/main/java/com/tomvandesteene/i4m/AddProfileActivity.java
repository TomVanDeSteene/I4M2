package com.tomvandesteene.i4m;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddProfileActivity extends FragmentActivity implements
        Language1Dialog.Language1PickerDialogFragmentListener,
        Language2Dialog.Language2PickerDialogFragmentListener,
        Language3Dialog.Language3PickerDialogFragmentListener,
        Language4Dialog.Language4PickerDialogFragmentListener,
        SkillsDialog.SkillsPickerDialogFragmentListener{

    private static final int CAMERA_REQUEST = 1888;

    private ImageView ivPhoto;

    private AppCompatButton btn1stLang;
    private AppCompatButton btn2ndLang;
    private AppCompatButton btn3rdLang;
    private AppCompatButton btn4thLang;
    private AppCompatButton btnSkills;
    private AppCompatButton btnDateOfBirth;

    private TextView tv1stLang;
    private TextView tv2ndLang;
    private TextView tv3rdLang;
    private TextView tv4thLang;
    private TextView tvSkills;
    private TextView tvDateOfBirth;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        initViews();

        ivPhoto.setOnClickListener(imageClickListener);

        btn1stLang.setOnClickListener(language1ClickListener);
        btn2ndLang.setOnClickListener(language2ClickListener);
        btn3rdLang.setOnClickListener(language3ClickListener);
        btn4thLang.setOnClickListener(language4ClickListener);
        btnSkills.setOnClickListener(skillsClickListener);
        btnDateOfBirth.setOnClickListener(dateClickListener);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth +"/"+ month + "/" + year;
                tvDateOfBirth.setText(date);
            }
        };
    }

    public void initViews(){
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);

        btn1stLang = (AppCompatButton)findViewById(R.id.btn_1st_language);
        btn2ndLang = (AppCompatButton)findViewById(R.id.btn_2nd__language);
        btn3rdLang = (AppCompatButton)findViewById(R.id.btn_3rd_language);
        btn4thLang = (AppCompatButton)findViewById(R.id.btn_4th_language);
        btnSkills = (AppCompatButton)findViewById(R.id.btn_skills);
        btnDateOfBirth = (AppCompatButton)findViewById(R.id.btn_date_of_birth);

        tv1stLang = (TextView)findViewById(R.id.tv_1st_language);
        tv2ndLang = (TextView)findViewById(R.id.tv_2nd_language);
        tv3rdLang = (TextView)findViewById(R.id.tv_3rd_language);
        tv4thLang = (TextView)findViewById(R.id.tv_4th_language);
        tvSkills = (TextView)findViewById(R.id.tv_skills);
        tvDateOfBirth = (TextView)findViewById(R.id.tv_date_of_birth);
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

    View.OnClickListener language1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Language1Dialog().show(getSupportFragmentManager(), "language1dialog");
        }
    };

    View.OnClickListener language2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Language2Dialog().show(getSupportFragmentManager(), "language2dialog");
        }
    };

    View.OnClickListener language3ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Language3Dialog().show(getSupportFragmentManager(), "language3dialog");
        }
    };

    View.OnClickListener language4ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Language4Dialog().show(getSupportFragmentManager(), "language4dialog");
        }
    };

    View.OnClickListener skillsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new SkillsDialog().show(getSupportFragmentManager(), "skillsdialog");
        }
    };

    View.OnClickListener dateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar cal = Calendar.getInstance();

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(AddProfileActivity.this,
                    android.R.style.Theme_Holo_Dialog,
                    onDateSetListener,
                    year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    };

    //target dialoginterfaces
    @Override
    public void skillsPicked(String skills) {
        tvSkills.setText(skills);
    }


    //target dialoginterfaces
    @Override
    public void language1Picked(String language1) {
        tv1stLang.setText(language1);
    }

    @Override
    public void language2Picked(String language2) {
        if (language2.equals(tv1stLang.getText().toString())) {
            Toast.makeText(getApplicationContext(), "You can't choose twice the same language", Toast.LENGTH_LONG).show();
        }else{
        tv2ndLang.setText(language2);
        }
        if (language2.equals("None")){
            tv3rdLang.setText(language2);
            tv4thLang.setText(language2);
        }
    }

    @Override
    public void language3Picked(String language3){
        if (language3.equals(tv1stLang.getText().toString()) || language3.equals(tv2ndLang.getText().toString())){
            Toast.makeText(getApplicationContext(), "You can't choose twice the same language", Toast.LENGTH_LONG).show();
        }else{
            tv3rdLang.setText(language3);
        }
        if (language3.equals("None")){
            tv4thLang.setText(language3);
        }
    }

    @Override
    public void language4Picked(String language4){
        if (language4.equals(tv1stLang.getText().toString()) || language4.equals(tv2ndLang.getText().toString())
                || language4.equals(tv3rdLang.getText().toString())){
            Toast.makeText(getApplicationContext(), "You can't choose twice the same language", Toast.LENGTH_LONG).show();
        }else{
            tv4thLang.setText(language4);
        }
    }
}
