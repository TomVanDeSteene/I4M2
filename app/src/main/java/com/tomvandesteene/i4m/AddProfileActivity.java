package com.tomvandesteene.i4m;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class AddProfileActivity extends FragmentActivity implements
        Language1Dialog.Language1PickerDialogFragmentListener,
        Language2Dialog.Language2PickerDialogFragmentListener,
        Language3Dialog.Language3PickerDialogFragmentListener,
        Language4Dialog.Language4PickerDialogFragmentListener,
        SkillsDialog.SkillsPickerDialogFragmentListener{

    private static final int CAMERA_REQUEST = 1888;

    private ImageView ivPhoto;

    private TextInputEditText etFirstName;
    private TextInputEditText etLastName;
    private TextInputEditText etTelephone;
    private TextInputEditText etEmail;
    private TextInputEditText etStreetNumber;
    private TextInputEditText etStreetName;
    private TextInputEditText etPostalCode;
    private TextInputEditText etLocation;

    private AppCompatButton btn1stLang;
    private AppCompatButton btn2ndLang;
    private AppCompatButton btn3rdLang;
    private AppCompatButton btn4thLang;
    private AppCompatButton btnSkills;
    private AppCompatButton btnDateOfBirth;
    private AppCompatButton btnSave;

    private TextView tv1stLang;
    private TextView tv2ndLang;
    private TextView tv3rdLang;
    private TextView tv4thLang;
    private TextView tvSkills;
    private TextView tvDateOfBirth;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    int id;

    String firstName;
    String lastName;
    String telephone;
    String email;
    String streetNumber;
    String streetName;
    String postalCode;
    String location;
    String lang1;
    String lang2;
    String lang3;
    String lang4;
    String skills;
    String dateOfBirth;

    private DbOperations db;

    private Bitmap bp;
    private byte[] photo;

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

        db = new DbOperations(this);
    }

    public void initViews(){
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);

        etFirstName = (TextInputEditText)findViewById(R.id.textInputEditTextFirstName);
        etLastName = (TextInputEditText)findViewById(R.id.textInputEditTextLastName);
        etTelephone = (TextInputEditText)findViewById(R.id.textInputEditTextTelephone);
        etEmail = (TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        etStreetNumber = (TextInputEditText)findViewById(R.id.textInputEditTextStreetNumber);
        etStreetName = (TextInputEditText)findViewById(R.id.textInputEditTextStreet);
        etPostalCode = (TextInputEditText)findViewById(R.id.textInputEditTextPostalCode);
        etLocation = (TextInputEditText)findViewById(R.id.textInputEditTextLocation);

        btn1stLang = (AppCompatButton)findViewById(R.id.btn_1st_language);
        btn2ndLang = (AppCompatButton)findViewById(R.id.btn_2nd__language);
        btn3rdLang = (AppCompatButton)findViewById(R.id.btn_3rd_language);
        btn4thLang = (AppCompatButton)findViewById(R.id.btn_4th_language);
        btnSkills = (AppCompatButton)findViewById(R.id.btn_skills);
        btnDateOfBirth = (AppCompatButton)findViewById(R.id.btn_date_of_birth);
        btnSave = (AppCompatButton)findViewById(R.id.btn_save);

        tv1stLang = (TextView)findViewById(R.id.tv_1st_language);
        tv2ndLang = (TextView)findViewById(R.id.tv_2nd_language);
        tv3rdLang = (TextView)findViewById(R.id.tv_3rd_language);
        tv4thLang = (TextView)findViewById(R.id.tv_4th_language);
        tvSkills = (TextView)findViewById(R.id.tv_skills);
        tvDateOfBirth = (TextView)findViewById(R.id.tv_date_of_birth);
    }

    public void saveProfile(View view){

        photo = profileImage(bp);

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        telephone = etTelephone.getText().toString();
        email = etEmail.getText().toString();
        streetNumber = etStreetNumber.getText().toString();
        streetName = etStreetName.getText().toString();
        postalCode = etPostalCode.getText().toString();
        location = etLocation.getText().toString();

        lang1 = tv1stLang.getText().toString();
        lang2 = tv2ndLang.getText().toString();
        lang3 = tv3rdLang.getText().toString();
        lang4 = tv4thLang.getText().toString();
        skills = tvSkills.getText().toString();
        dateOfBirth = tvDateOfBirth.getText().toString();

        db.addProfiles(new Profile(id, photo, firstName, lastName, telephone, email, streetNumber, streetName,
                postalCode, location, lang1, lang2, lang3, lang4, skills, dateOfBirth));
        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bp = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(bp);
        }
    }
    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

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
