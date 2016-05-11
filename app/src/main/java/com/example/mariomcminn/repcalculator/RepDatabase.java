package com.example.mariomcminn.repcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by mariomcminn on 5/1/16.
 */
public class RepDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "rep.db";
    private static final int DB_VERSION = 1;
    public static final String REP_TABLE = "rep";
    public static final String REP_NUMBER = "rep";
    public static final String DATE = "date";
    public static final String EXERCISE = "exercise";

    public RepDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table rep (" +
                "_id integer primary key autoincrement, rep integer, date integer, exercise string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REP_TABLE);
        onCreate(db);
    }

    public boolean insertCartItem(double reps, String date, String exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REP_NUMBER, reps);
        values.put(DATE, date);
        values.put(EXERCISE, exercise);
        long result = db.insert(REP_TABLE, null, values);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(REP_TABLE, null, null, null, null, null, REP_NUMBER + " DESC", null);
        return result;
    }

    public Cursor getTotalRepCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor total = db.rawQuery("SELECT SUM(rep) FROM " + REP_TABLE, null);
        return total;
    }

    public void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ REP_TABLE);
    }
}
