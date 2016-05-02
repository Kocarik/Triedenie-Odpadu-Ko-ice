package com.pmm.triedenieodpadukosice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Boss on 24. 4. 2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Odvozy.db";
    public static final String TABLE_NAME ="Harmonogram";
    public static final String TABLE_MC ="MestskeCasti";
    public static final String TABLE_GROUPS ="Obvody";
    public static final String TABLE_DATES ="Terminy";
    public static final String COL1 ="ID";

    public static final String TAG="SQLite";

    /*public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }*/
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MC + " (ulica text, mc text, primary key(ulica))");
        db.execSQL("create table " + TABLE_GROUPS +" (mc text, ido integer, foreign key(mc) references "+TABLE_MC+"(mc))");
        db.execSQL("create table " + TABLE_DATES + " (id integer auto_increment, datum text, ido integer, odpad integer, " +
                "primary key(id), foreign key(ido) references " + TABLE_GROUPS + "(ido))");
        //db.execSQL("create table"+TABLE_NAME+"(ID INTEGER  PRIMARY KEY AUTOINCREMENT)");
        Log.d(TAG, "DB Created");

        insertObvody(db);
        insertUlice(db);
        insertTerminy(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_MC);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_DATES);
        Log.d(TAG, "Tables dropped");
        onCreate(db);
    }

    public void insertTerminy(SQLiteDatabase db){
        insertTermin(db, 2, 1, "2016-05-04");
        insertTermin(db, 2, 2, "2016-05-05");
        insertTermin(db, 2, 3, "2016-05-06");
        insertTermin(db, 0, 1, "2016-05-11");
        insertTermin(db, 0, 2, "2016-05-12");
        insertTermin(db, 0, 3, "2016-05-13");
        insertTermin(db, 1, 1, "2016-05-18");
        insertTermin(db, 1, 2, "2016-05-19");
        insertTermin(db, 1, 3, "2016-05-20");
    }

    public void insertUlice(SQLiteDatabase db){
        insertUlica(db, "Abovská", "Barca");
        insertUlica(db, "Adamova", "Krásna");
        insertUlica(db, "Agátová", "Košická Nová Ves");
        insertUlica(db, "Alešovo nábrežie", "Sever");

    }

    public void insertObvody(SQLiteDatabase db){
        insertObvod(db, "Juh", 1);
        insertObvod(db, "Barca", 1);
        insertObvod(db, "Šebastovce", 1);
        insertObvod(db, "Krásna", 1);
        insertObvod(db, "Vyšné Opátske", 1);
        insertObvod(db, "Myslava", 2);
        insertObvod(db, "Pereš", 2);
        insertObvod(db, "Lorinčík", 2);
        insertObvod(db, "Poľov", 2);
        insertObvod(db, "Šaca", 2);
        insertObvod(db, "Ľudvíkov Dvor", 2);
        insertObvod(db, "Západ", 2);
        insertObvod(db, "Kavečany", 2);
        insertObvod(db, "Ťahanovce - obec", 2);
        insertObvod(db, "Sever", 3);
        insertObvod(db, "Džungľa", 3);
        insertObvod(db, "Košická Nová Ves", 3);
    }

    public boolean insertUlica (SQLiteDatabase db, String ulica, String mc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ulica", ulica);
        contentValues.put("mc", mc);
        db.insert(TABLE_MC, null, contentValues);
        Log.d(TAG, "Ulica Inserted");
        return true;
    }

    public boolean insertObvod (SQLiteDatabase db, String mc, int ido) {
        /*
        1, 2, 3
         */
        ContentValues contentValues = new ContentValues();
        contentValues.put("mc", mc);
        contentValues.put("ido", ido);
        db.insert(TABLE_GROUPS, null, contentValues);
        Log.d(TAG, "Obvod Inserted");
        return true;
    }

    public boolean insertTermin (SQLiteDatabase db, int odpad, int ido, String datum) {
        /*
        papier 0
        sklo 1
        plast+kov 2
         */
        ContentValues contentValues = new ContentValues();
        contentValues.put("odpad", odpad);
        contentValues.put("ido", ido);
        contentValues.put("datum", datum);
        db.insert(TABLE_DATES, null, contentValues);
        Log.d(TAG, "Termin Inserted");
        return true;
    }

    public Odvoz getNextOdvoz(String ulica) {
        Odvoz odvoz=null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select datum, odpad from Terminy INNER JOIN Obvody ON Terminy.ido=Obvody.ido " +
                "INNER JOIN MestskeCasti ON Obvody.mc=MestskeCasti.mc " +
                "WHERE MestskeCasti.ulica LIKE '"+ulica+"' AND date(datum) > date('now') ORDER BY date(datum) ASC LIMIT 1", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            odvoz=new Odvoz(res.getInt(res.getColumnIndex("odpad")), res.getString(res.getColumnIndex("datum")));
            res.moveToNext();
        }
        System.out.println(odvoz.getDatum() +" "+ odvoz.getTyp());
        return odvoz;
    }

}
