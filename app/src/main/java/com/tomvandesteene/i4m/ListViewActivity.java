package com.tomvandesteene.i4m;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private DbOperations db;
    private ListView lv;
    private ProfileAdapter data;
    private Profile dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Instantiate database handler
        db = new DbOperations(this);
        lv = (ListView)findViewById(R.id.display_listview);

        ShowRecords();
    }

    //Retrieve data from the database and set to the list view
    private void ShowRecords(){
        final ArrayList<Profile> profiles = new ArrayList<>(db.getAllProfiles());
        data=new ProfileAdapter(this, profiles);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = profiles.get(position);

                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getId()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
