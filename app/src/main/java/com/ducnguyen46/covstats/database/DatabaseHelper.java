package com.ducnguyen46.covstats.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ducnguyen46.covstats.models.CovidCountry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.ducnguyen46.covstats.constant.Constant.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "covstats.db";
    static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + COVID_COUNTRY_TBL +
                " ( " + COUNTRY_NAME_COL + " TEXT PRIMARY KEY, " +
                COUNTRY_CODE_COL + " TEXT, " +
                NEW_CASE_COL + " INTEGER, " +
                TOTAL_CASE_COL + " INTEGER, " +
                NEW_RECOVERED_COL + " INTEGER, " +
                TOTAL_RECOVERED_COL + " INTEGER, " +
                NEW_DEATH_COL + " INTEGER, " +
                TOTAL_DEATH_COL + " INTEGER, " +
                DATE_COL + " INTERGER );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public CovidCountry getCovidCountryByName(String countryName){
        SQLiteDatabase db = getReadableDatabase();
        CovidCountry covidCountry;
        String[] args = {countryName};
        Cursor countryCurson = db.query(COVID_COUNTRY_TBL, null, COUNTRY_NAME_COL + " = ?", args,
                null, null, null, null);
        while(countryCurson != null && countryCurson.moveToNext()){
            try {
                covidCountry = new CovidCountry(
                        countryCurson.getString(countryCurson.getColumnIndex(COUNTRY_NAME_COL)),
                        countryCurson.getString(countryCurson.getColumnIndex(COUNTRY_CODE_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(NEW_CASE_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(TOTAL_CASE_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(NEW_RECOVERED_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(TOTAL_CASE_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(NEW_DEATH_COL)),
                        countryCurson.getInt(countryCurson.getColumnIndex(TOTAL_DEATH_COL)),
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(
                                countryCurson.getString(countryCurson.getColumnIndex(DATE_COL))));

                return covidCountry;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<String> getCountryName(String countryName){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] args = {countryName};
        String[] cols = {"%" + COUNTRY_NAME_COL  +"%"};
        Cursor countryCurson = db.query(COVID_COUNTRY_TBL, cols, COUNTRY_NAME_COL + " LIKE ?", args,
                null, null, COUNTRY_NAME_COL + " ASC ", null);
        while(countryCurson != null && countryCurson.moveToNext()){
            String name = countryCurson.getString(1);
            list.add(name);
        }
        return list;
    }

    public long insertCovidCountry(CovidCountry covidCountry){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME_COL, covidCountry.getCountry());
        values.put(COUNTRY_CODE_COL, covidCountry.getCountryCode());
        values.put(NEW_CASE_COL, covidCountry.getNewConfirmed());
        values.put(TOTAL_CASE_COL, covidCountry.getTotalConfirmed());
        values.put(NEW_RECOVERED_COL, covidCountry.getNewRecovered());
        values.put(TOTAL_RECOVERED_COL, covidCountry.getTotalRecovered());
        values.put(NEW_DEATH_COL, covidCountry.getNewDeath());
        values.put(TOTAL_DEATH_COL, covidCountry.getTotalDeath());
        values.put(DATE_COL,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(covidCountry.getDate()));
        return db.insert(COVID_COUNTRY_TBL, null, values);
    }
}
