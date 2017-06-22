package com.tomvandesteene.i4m;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Van de Steene on 20/06/2017.
 */

public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "profile_info.db";

    private static final String CREATE_QUERY = "create table " + ProfileContract.ProfileEntry.TABLE_NAME +
            "("+ ProfileContract.ProfileEntry.ID+ " integer primary key,"+
            ProfileContract.ProfileEntry.PHOTO + " blob,"+ ProfileContract.ProfileEntry.FIRST_NAME+ " text,"+
            ProfileContract.ProfileEntry.LAST_NAME+ " text,"+ ProfileContract.ProfileEntry.TELEPHONE+ " text,"+
            ProfileContract.ProfileEntry.EMAIL+ " text,"+ ProfileContract.ProfileEntry.STREET_NUMBER+ " text,"+
            ProfileContract.ProfileEntry.STREET_NAME+ " text,"+ ProfileContract.ProfileEntry.POSTAL_CODE+ " text,"+
            ProfileContract.ProfileEntry.LOCATION+ " text,"+ ProfileContract.ProfileEntry.FIRST_LANGUAGE+ " text,"+
            ProfileContract.ProfileEntry.SECOND_LANGUAGE+ " text,"+ ProfileContract.ProfileEntry.THIRD_LANGUAGE+ " text,"+
            ProfileContract.ProfileEntry.FOURTH_LANGUAGE+ " text,"+ ProfileContract.ProfileEntry.SKILLS+ " text,"+
            ProfileContract.ProfileEntry.DATE_OF_BIRTH+ " text);";


    public DbOperations(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("Database operations", "Database created ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created ...");
    }

    //Insert values to the table profiles
    public void addProfiles(Profile profile){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ProfileContract.ProfileEntry.PHOTO, profile.getPhoto());
        contentValues.put(ProfileContract.ProfileEntry.FIRST_NAME, profile.getFirstName());
        contentValues.put(ProfileContract.ProfileEntry.LAST_NAME, profile.getLastName());
        contentValues.put(ProfileContract.ProfileEntry.TELEPHONE, profile.getTelephone());
        contentValues.put(ProfileContract.ProfileEntry.EMAIL, profile.getEmail());
        contentValues.put(ProfileContract.ProfileEntry.STREET_NUMBER, profile.getStreetNumber());
        contentValues.put(ProfileContract.ProfileEntry.STREET_NAME, profile.getStreetName());
        contentValues.put(ProfileContract.ProfileEntry.POSTAL_CODE, profile.getPostalCode());
        contentValues.put(ProfileContract.ProfileEntry.LOCATION, profile.getLocation());
        contentValues.put(ProfileContract.ProfileEntry.FIRST_LANGUAGE, profile.getLang1());
        contentValues.put(ProfileContract.ProfileEntry.SECOND_LANGUAGE, profile.getLang2());
        contentValues.put(ProfileContract.ProfileEntry.THIRD_LANGUAGE, profile.getLang3());
        contentValues.put(ProfileContract.ProfileEntry.FOURTH_LANGUAGE, profile.getLang4());
        contentValues.put(ProfileContract.ProfileEntry.SKILLS, profile.getSkills());
        contentValues.put(ProfileContract.ProfileEntry.DATE_OF_BIRTH, profile.getDateOfBirth());

        db.insert(ProfileContract.ProfileEntry.TABLE_NAME, null, contentValues);
        Log.d("Database operations", "One row inserted ...");
        db.close();
    }

    /**
     *Getting All Profiles
     **/
    public List<Profile> getAllProfiles() {
        List<Profile> profileList = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + ProfileContract.ProfileEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Profile profile = new Profile();
                profile.setId(Integer.parseInt(cursor.getString(0)));
                profile.setPhoto(cursor.getBlob(1));
                profile.setFirstName(cursor.getString(2));
                profile.setLastName(cursor.getString(3));
                profile.setTelephone(cursor.getString(4));
                profile.setEmail(cursor.getString(5));
                profile.setStreetNumber(cursor.getString(6));
                profile.setStreetName(cursor.getString(7));
                profile.setPostalCode(cursor.getString(8));
                profile.setLocation(cursor.getString(9));
                profile.setLang1(cursor.getString(10));
                profile.setLang2(cursor.getString(11));
                profile.setLang3(cursor.getString(12));
                profile.setLang1(cursor.getString(13));
                profile.setSkills(cursor.getString(14));
                profile.setDateOfBirth(cursor.getString(15));

                //Adding contact to list
                profileList.add(profile);
            }while (cursor.moveToNext());
        }
        return profileList;
    }
    /**
     *Updating single profile
     **/
    public int updateProfile(Profile profile, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProfileContract.ProfileEntry.PHOTO, profile.getPhoto());
        contentValues.put(ProfileContract.ProfileEntry.FIRST_NAME, profile.getFirstName());
        contentValues.put(ProfileContract.ProfileEntry.LAST_NAME, profile.getLastName());
        contentValues.put(ProfileContract.ProfileEntry.TELEPHONE, profile.getTelephone());
        contentValues.put(ProfileContract.ProfileEntry.EMAIL, profile.getEmail());
        contentValues.put(ProfileContract.ProfileEntry.STREET_NUMBER, profile.getStreetNumber());
        contentValues.put(ProfileContract.ProfileEntry.STREET_NAME, profile.getStreetName());
        contentValues.put(ProfileContract.ProfileEntry.POSTAL_CODE, profile.getPostalCode());
        contentValues.put(ProfileContract.ProfileEntry.LOCATION, profile.getLocation());
        contentValues.put(ProfileContract.ProfileEntry.FIRST_LANGUAGE, profile.getLang1());
        contentValues.put(ProfileContract.ProfileEntry.SECOND_LANGUAGE, profile.getLang2());
        contentValues.put(ProfileContract.ProfileEntry.THIRD_LANGUAGE, profile.getLang3());
        contentValues.put(ProfileContract.ProfileEntry.FOURTH_LANGUAGE, profile.getLang4());
        contentValues.put(ProfileContract.ProfileEntry.SKILLS, profile.getSkills());
        contentValues.put(ProfileContract.ProfileEntry.DATE_OF_BIRTH, profile.getDateOfBirth());

        //updating row
        return db.update(ProfileContract.ProfileEntry.TABLE_NAME, contentValues,
                ProfileContract.ProfileEntry.ID + " = ?", new String[]{String.valueOf(id)});
    }
    /**
     *Deleting single profile
     **/
    public void deleteProfile(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProfileContract.ProfileEntry.TABLE_NAME,
                ProfileContract.ProfileEntry.ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ProfileContract.ProfileEntry.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
