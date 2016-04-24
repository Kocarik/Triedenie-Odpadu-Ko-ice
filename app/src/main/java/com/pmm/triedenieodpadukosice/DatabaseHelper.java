package com.pmm.triedenieodpadukosice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Boss on 24. 4. 2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME ="Odvozy.db";
    public  static final String TABLE_NAME ="Harmonogram";
    public  static final String COL1 ="ID";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table"+TABLE_NAME+"(ID INTEGER  PRIMARY KEY AUTOINCREMENT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
