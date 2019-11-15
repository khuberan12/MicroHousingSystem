package com.example.microhousingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "microHousingSystem";//DATABASE NAME
    public static final int DATABASE_VERSION = 1;//DATABASE VERSION


    public static final String TABLE_APPLICANT = "applicant"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID = "id"; //ID COLUMN @primaryKey
    public static final String KEY_USER_NAME = "username";  //COLUMN user name
    public static final String KEY_PASSWORD = "password";//COLUMN password
    public static final String KEY_FULLNAME = "fullname";//COLUMN fullname
    public static final String KEY_EMAIL = "email";//COLUMN fullname
    public static final String KEY_MONTHLYINCOME = "monthlyIncome";//COLUMN monthlyIncome

    public static final String SQL_TABLE_APPLICANT = " CREATE TABLE " + TABLE_APPLICANT //SQL for creating users table

            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_FULLNAME + "TEXT,"
            + KEY_EMAIL + "TEXT,"
            + KEY_MONTHLYINCOME + "TEXT"
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_APPLICANT);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_HO);
    }

    //using this method we can add users to user table
    public void addApplicant(Applicant ap) {

        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_USER_NAME,ap.getUsername() ); //Put username in  @values
        values.put(KEY_PASSWORD, ap.getPassword()); //Put password in  @values
        values.put(KEY_FULLNAME, ap.getFullname()); //Put  in  @values
        values.put(KEY_PASSWORD, ap.getPassword()); //Put  in  @values
        values.put(KEY_EMAIL, ap.getEmail()); //Put password in  @values
        values.put(KEY_MONTHLYINCOME, ap.getMonthlyIncome()); //Put password in  @values
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
    public static final String KEY_STAFFID = "staffID";//COLUMN fullname
    public static final String SQL_TABLE_HO = " CREATE TABLE " + TABLE_HO //SQL for creating users table

            + " ( "
            + KEY_ID_HO + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME_HO + " TEXT, "
            + KEY_PASSWORD_HO + " TEXT,"
            + KEY_FULLNAME_HO + "TEXT,"
            + KEY_STAFFID + "TEXT"
            + " ) ";

    //using this method we can add users to user table
    public void addHO(HousingOfficer ho) {

        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_USER_NAME,ho.getUsername() ); //Put username in  @values
        values.put(KEY_PASSWORD, ho.getPassword()); //Put password in  @values
        values.put(KEY_FULLNAME, ho.getFullname()); //Put  in  @values
        values.put(KEY_STAFFID, ho.getStaffID()); //Put  in  @values

        long todo_id = db.insert(TABLE_HO, null, values); // insert row
    }

    public HousingOfficer AuthenticateHO(HousingOfficer user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HO,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
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







}
