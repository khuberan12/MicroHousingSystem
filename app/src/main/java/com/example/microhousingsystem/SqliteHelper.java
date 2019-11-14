package com.example.microhousingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "USER";//DATABASE NAME
    public static final int DATABASE_VERSION = 1;//DATABASE VERSION
    public static final String TABLE_USERS = "users"; //TABLE NAME

    //TABLE USERS COLUMNS
    public static final String KEY_ID = "id"; //ID COLUMN @primaryKey
    public static final String KEY_USER_NAME = "username";  //COLUMN user name
    public static final String KEY_PASSWORD = "password";//COLUMN password
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS //SQL for creating users table

            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addApplicant(Applicant ap) {

        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_USER_NAME,ap.getUsername() ); //Put username in  @values
        values.put(KEY_PASSWORD, ap.getPassword()); //Put password in  @values
        long todo_id = db.insert(TABLE_USERS, null, values); // insert row
    }

    public Applicant Authenticate(Applicant user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given username
            Applicant user1 = new Applicant(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }


}
