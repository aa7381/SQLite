package com.example.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 2;
    String strCreate , strDelete;

    public HelperDB (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        strCreate = "CREATE TABLE " + Worker.TABLE_WORKER;
        strCreate+=" ("+Worker.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+Worker.NAME+" TEXT,";
        strCreate+=" "+Worker.THE_COMPANY_HE_WORKS_FOR+" TEXT,";
        strCreate+=" "+Worker.LAST_NAME+" TEXT,";
        strCreate+=" "+Worker.PHONE_NUMBER+" INTEGER,";
        strCreate+=" "+Worker.ID+" INTEGER,";
        strCreate+=" "+Worker.CARD_NUMBER+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+ParkFood.TABLE_PARKFOOD;
        strCreate+=" ("+ParkFood.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ParkFood.NAME_COMPANY+" TEXT,";
        strCreate+=" "+ParkFood.COMPANY_ID+" INTEGER,";
        strCreate+=" "+ParkFood.MAIN_PHONE+" INTEGER,";
        strCreate+=" "+ParkFood.SECONDARY_PHONE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+Meal.TABLE_MEAL;
        strCreate +=" ("+Meal.KEY_ID +" INTEGER PRIMARY KEY,";
        strCreate +=" "+Meal.STARTER+" TEXT,";
        strCreate +=" "+Meal.MAIN_MEAL+" TEXT,";
        strCreate +=" "+Meal.SIDE_MEAL+" TEXT,";
        strCreate +=" "+Meal.DESSERT+" TEXT";
        strCreate += ");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+Order.TABLE_ORDER;
        strCreate+=" ("+Order.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+Order.MEAL+" TEXT,";
        strCreate+=" "+ Order.EMPLOYEE+" TEXT,";
        strCreate+=" "+Order.PROVIDER_COMPANY+" TEXT,";
        strCreate+=" "+Order.DATE+" TEXT,";
        strCreate+=" "+Order.TIME+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        strDelete ="DROP TABLE IF EXISTS " +Worker.TABLE_WORKER;
        db.execSQL(strDelete);

        strDelete ="DROP TABLE IF EXISTS " +Meal.TABLE_MEAL;
        db.execSQL(strDelete);

        strDelete ="DROP TABLE IF EXISTS " +ParkFood.TABLE_PARKFOOD;
        db.execSQL(strDelete);

        strDelete ="DROP TABLE IF EXISTS " +Order.TABLE_ORDER;
        db.execSQL(strDelete);

        onCreate(db);


    }
}
