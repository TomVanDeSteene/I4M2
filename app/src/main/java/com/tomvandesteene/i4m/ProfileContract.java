package com.tomvandesteene.i4m;

/**
 * Created by Tom Van de Steene on 19/06/2017.
 */

public final class ProfileContract {

    public static abstract class ProfileEntry{

        //columns names
        public static final String ID = "id";
        public static final String PHOTO = "photo";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String TELEPHONE = "telephone";
        public static final String EMAIL = "email";
        public static final String STREET_NUMBER = "street_number";
        public static final String STREET_NAME = "street_name";
        public static final String POSTAL_CODE = "postal_code";
        public static final String LOCATION = "location";
        public static final String FIRST_LANGUAGE = "first_language";
        public static final String SECOND_LANGUAGE = "second_language";
        public static final String THIRD_LANGUAGE = "third_language";
        public static final String FOURTH_LANGUAGE = "fourth_language";
        public static final String SKILLS = "skills";
        public static final String DATE_OF_BIRTH = "date_of_birth";

        //table name
        public static final String TABLE_NAME = "profiles";
    }
}
