package com.tomvandesteene.i4m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btnAddProfile;
    private AppCompatButton btnViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddProfile = (AppCompatButton)findViewById(R.id.btn_add_profile);
        btnAddProfile.setOnClickListener(addProfileListener);

        btnViewList = (AppCompatButton)findViewById(R.id.btn_view_list);
        btnViewList.setOnClickListener(viewListListener);
    }

    View.OnClickListener addProfileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddProfileActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener viewListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        }
    };
}
