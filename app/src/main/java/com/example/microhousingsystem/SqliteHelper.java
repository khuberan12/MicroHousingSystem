package com.example.microhousingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SqliteHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "microHousingSystem";//DATABASE NAME
    public static final int DATABASE_VERSION = 26;//DATABASE VERSION


    public static final String TABLE_APPLICANT = "applicant"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID = "id"; //ID COLUMN @primaryKey
    public static final String KEY_FULL_NAME = "fullname";  //COLUMN FULL NAME
    public static final String KEY_USER_NAME = "username";  //COLUMN USER NAME
    public static final String KEY_PASSWORD = "password";//COLUMN PASSWORD
    public static final String KEY_EMAIL = "email";//COLUMN EMAIL
    public static final String KEY_MONTHLYINCOME = "monthlyIncome";  //COLUMN MONTHLY INCOME
    public static final String KEY_USER_TYPE = "userType";


    public static final String SQL_TABLE_APPLICANT = " CREATE TABLE " + TABLE_APPLICANT //SQL FOR CREATING APPLICANT TABLE

            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_FULL_NAME + " TEXT,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_MONTHLYINCOME + " TEXT,"
            + KEY_USER_TYPE + " TEXT "
            + " ) ";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_APPLICANT);
        sqLiteDatabase.execSQL(SQL_TABLE_HO);
        sqLiteDatabase.execSQL(SQL_TABLE_RESIDENCE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_APPLICANT);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_HO);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_RESIDENCE);

        onCreate(sqLiteDatabase);

    }

    //using this method we can add users to user table
    public void addApplicant(Applicant ap) {

        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_FULL_NAME, ap.getFullname()); //Put  in  @values
        values.put(KEY_USER_NAME, ap.getUsername()); //Put username in  @values
        values.put(KEY_PASSWORD, ap.getPassword()); //Put password in  @values
        values.put(KEY_EMAIL, ap.getEmail()); //Put password in  @values
        values.put(KEY_MONTHLYINCOME, ap.getMonthlyIncome()); //Put password in  @values
        values.put(KEY_USER_TYPE, ap.getUserType());//pls work laa babi


        long todo_id = db.insert(TABLE_APPLICANT, null, values); // insert row
    }

    public Applicant Authenticate(Applicant user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_APPLICANT,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given username
            Applicant user1 = new Applicant(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2));
            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String TABLE_HO = "housingOfficer"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID_HO = "idHO"; //ID COLUMN @primaryKey
    public static final String KEY_USER_NAME_HO = "usernameHO";  //COLUMN user name
    public static final String KEY_PASSWORD_HO = "passwordHO";//COLUMN password
    public static final String KEY_FULLNAME_HO = "fullnameHO";//COLUMN fullname
    public static final String KEY_USER_TYPEHO = "userTypeHO";//COLUMN fullname
    public static final String SQL_TABLE_HO = " CREATE TABLE " + TABLE_HO //SQL for creating users table

            + " ( "
            + KEY_ID_HO + " INTEGER PRIMARY KEY,"
            + KEY_USER_NAME_HO + " TEXT,"
            + KEY_PASSWORD_HO + " TEXT,"
            + KEY_FULLNAME_HO + " TEXT,"
            + KEY_USER_TYPEHO + " TEXT "
            + " ) ";

    //using this method we can add users to user table
    public void addHO(HousingOfficer ho) {

        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_USER_NAME_HO, ho.getUsername()); //Put username in  @values
        values.put(KEY_PASSWORD_HO, ho.getPassword()); //Put password in  @values
        values.put(KEY_FULLNAME_HO, ho.getFullname()); //Put  in  @values


        long todo_id = db.insert(TABLE_HO, null, values); // insert row
    }

    public HousingOfficer AuthenticateHO(HousingOfficer user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HO,// Selecting Table
                new String[]{KEY_ID_HO, KEY_USER_NAME_HO, KEY_PASSWORD_HO},//Selecting columns want to query
                KEY_USER_NAME_HO + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given username
            HousingOfficer user1 = new HousingOfficer(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public static final String TABLE_RESIDENCE = "residence"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID_R = "idR"; //ID COLUMN @primaryKey
    public static final String KEY_RESIDENCE_ADDRESS = "address";  //COLUMN user name
    public static final String KEY_NUM_OF_UNITS = "numberOfUnit";//COLUMN password
    public static final String KEY_SIZE_PER_UNIT = "sizePerUnit";//COLUMN fullname
    public static final String KEY_MONTHLY_RENTAL = "monthlyRental";//COLUMN fullname
    public static final String SQL_TABLE_RESIDENCE = " CREATE TABLE " + TABLE_RESIDENCE //SQL for creating users table

            + " ( "
            + KEY_ID_R + " INTEGER PRIMARY KEY,"
            + KEY_RESIDENCE_ADDRESS + " TEXT,"
            + KEY_NUM_OF_UNITS + " TEXT,"
            + KEY_SIZE_PER_UNIT + " TEXT,"
            + KEY_MONTHLY_RENTAL + " TEXT "
            + " ) ";

    //using this method we can add users to user table
    public void addResidence(Residence r) {

        try {
            SQLiteDatabase db = this.getWritableDatabase(); //get writable database
            ContentValues values = new ContentValues(); //create content values to insert
            values.put(KEY_ID_R, r.getResidenceID());//Put id in @values
            values.put(KEY_RESIDENCE_ADDRESS, r.getAddress()); //Put address in  @values
            values.put(KEY_NUM_OF_UNITS, r.getNumOfUnits()); //Put units in  @values
            values.put(KEY_SIZE_PER_UNIT, r.getSizePerUnit()); //Put  size  @values
            values.put(KEY_MONTHLY_RENTAL, r.getMonthlyRental()); //Put  rental  @values

            //Insert into database
            db.insert(TABLE_RESIDENCE, null, values);
            Log.i("Database", "added Residence!");
        } catch (Exception e) {
            Log.d("AddResidence: ", e.getMessage());
        }
    }


    public Residence getResidence(int id) {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_RESIDENCE,
                    new String[]{KEY_ID_R,
                            KEY_RESIDENCE_ADDRESS,
                            KEY_NUM_OF_UNITS,
                            KEY_SIZE_PER_UNIT,
                            KEY_MONTHLY_RENTAL},
                    KEY_ID_R + "=?",
                    new String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            Residence item = new Residence();

            if (cursor != null) {
                item.setResidenceID(cursor.getString(cursor.getColumnIndex((KEY_ID_R))));
                item.setAddress(cursor.getString(cursor.getColumnIndex(KEY_RESIDENCE_ADDRESS)));
                item.setNumOfUnits(cursor.getString(cursor.getColumnIndex(KEY_NUM_OF_UNITS)));
                item.setSizePerUnit(cursor.getString(cursor.getColumnIndex(KEY_SIZE_PER_UNIT)));
                item.setMonthlyRental(cursor.getString(cursor.getColumnIndex(KEY_MONTHLY_RENTAL)));
            }
            return item;
        } catch (Exception e) {
            Log.d("getResidence: ", e.getMessage());
            return null;
        }
    }

    public List<Residence> getAllResidence() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            List<Residence> residenceList = new ArrayList<>();

            Cursor cursor = db.query(TABLE_RESIDENCE,
                    new String[]{KEY_ID_R,
                            KEY_RESIDENCE_ADDRESS,
                            KEY_NUM_OF_UNITS,
                            KEY_SIZE_PER_UNIT,
                            KEY_MONTHLY_RENTAL},
                    null, null, null, null,
                    KEY_ID_R + " DESC");

            if (cursor.moveToFirst()) {

                do {

                    Residence item = new Residence();
                    item.setResidenceID(cursor.getString(cursor.getColumnIndex((KEY_ID_R))));
                    item.setAddress(cursor.getString(cursor.getColumnIndex(KEY_RESIDENCE_ADDRESS)));
                    item.setNumOfUnits(cursor.getString(cursor.getColumnIndex(KEY_NUM_OF_UNITS)));
                    item.setSizePerUnit(cursor.getString(cursor.getColumnIndex(KEY_SIZE_PER_UNIT)));
                    item.setMonthlyRental(cursor.getString(cursor.getColumnIndex(KEY_MONTHLY_RENTAL)));

                    residenceList.add(item);
                } while (cursor.moveToNext());
            }
            db.close();
            return residenceList;
        } catch (Exception e) {
            Log.d("getAllResidence: ", e.getMessage());
            return null;
        }
    }

    public int getResidenceCount() {

        try {
            String countQuery = "SELECT * FROM " + TABLE_RESIDENCE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int result = cursor.getCount();
            db.close();

            return result;
        } catch (Exception e) {
            Log.d("getItemCount: ", e.getMessage());
            return -1;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    public static final String TABLE_APPLICATION = "application"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID_APP = "idApp"; //ID COLUMN @primaryKey
    public static final String KEY_APP_DATE = "appDate";  //COLUMN user name
    public static final String KEY_REQ_MONTH = "reqMonth";//COLUMN password
    public static final String KEY_REQ_YEAR = "reqYear";//COLUMN fullname
    public static final String KEY_STATUS = "status";//COLUMN fullname
    public static final String SQL_TABLE_APPLICATION = " CREATE TABLE " + TABLE_APPLICATION //SQL for creating users table

            + " ( "
            + KEY_ID_APP + " INTEGER PRIMARY KEY,"
            + KEY_APP_DATE + " TEXT,"
            + KEY_REQ_MONTH + " TEXT,"
            + KEY_REQ_YEAR + " TEXT,"
            + KEY_STATUS + " TEXT "
            + " ) ";

    //using this method we can add users to user table
    public void addApplication(Application a) {

        try {
            SQLiteDatabase db = this.getWritableDatabase(); //get writable database
            ContentValues values = new ContentValues(); //create content values to insert
            values.put(KEY_APP_DATE, a.getApplicationDate()); //Put username in  @values
            values.put(KEY_REQ_MONTH, a.getRequiredMonth()); //Put password in  @values
            values.put(KEY_REQ_YEAR, a.getRequiredYear()); //Put  in  @values
            values.put(KEY_STATUS, a.getStatus()); //Put  in  @values

            //Insert into database
            db.insert(TABLE_APPLICATION, null, values);
            Log.i("Database", "added Application!");
        } catch (Exception e) {
            Log.d("AddApplication: ", e.getMessage());
        }
    }


    public Application getApplication(int id) {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_APPLICATION,
                    new String[]{KEY_ID_APP,
                            KEY_APP_DATE,
                            KEY_REQ_MONTH,
                            KEY_REQ_YEAR,
                            KEY_STATUS},
                    KEY_ID_APP + "=?",
                    new String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            Application item = new Application();

            if (cursor != null) {
                item.setIdApp(cursor.getString(cursor.getColumnIndex((KEY_ID_APP))));
                item.setApplicationDate(cursor.getString(cursor.getColumnIndex(KEY_APP_DATE)));
                item.setRequiredMonth(cursor.getString(cursor.getColumnIndex(KEY_REQ_MONTH)));
                item.setRequiredYear(cursor.getString(cursor.getColumnIndex(KEY_REQ_YEAR)));
                item.setRequiredYear(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
            }
            return item;
        } catch (Exception e) {
            Log.d("getApplication: ", e.getMessage());
            return null;
        }
    }

    public List<Application> getAllApplication() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            List<Application> applicationList = new ArrayList<>();

            Cursor cursor = db.query(TABLE_APPLICATION,
                    new String[]{KEY_ID_APP,
                            KEY_APP_DATE,
                            KEY_REQ_MONTH,
                            KEY_REQ_YEAR,
                            KEY_STATUS},
                    null, null, null, null,
                    KEY_ID_APP + " DESC");

            if (cursor.moveToFirst()) {

                do {

                    Application item = new Application();
                    item.setIdApp(cursor.getString(cursor.getColumnIndex((KEY_ID_APP))));
                    item.setApplicationDate(cursor.getString(cursor.getColumnIndex(KEY_APP_DATE)));
                    item.setRequiredMonth(cursor.getString(cursor.getColumnIndex(KEY_REQ_MONTH)));
                    item.setRequiredYear(cursor.getString(cursor.getColumnIndex(KEY_REQ_YEAR)));
                    item.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));

                    applicationList.add(item);
                } while (cursor.moveToNext());
            }
            db.close();
            return applicationList;
        } catch (Exception e) {
            Log.d("getAllApplication: ", e.getMessage());
            return null;
        }
    }

    public int getApplicationCount() {

        try {
            String countQuery = "SELECT * FROM " + TABLE_APPLICATION;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int result = cursor.getCount();
            db.close();

            return result;
        } catch (Exception e) {
            Log.d("getApplicationCount: ", e.getMessage());
            return -1;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


}
