package com.pmm.triedenieodpadukosice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    /*public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }*/
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_MC + "(ulica text, mc text, primary key(ulica)");
        db.execSQL("create table"+ TABLE_GROUPS +"(mc text, ido integer, foreign key(mc) references "+TABLE_MC+"(mc))");
        db.execSQL("create table" + TABLE_DATES + "(id integer auto_increment, datum text, ido integer, odpad integer, " +
                "primary key(id), foreign key(ido) references " + TABLE_GROUPS + "(ido))");
        //db.execSQL("create table"+TABLE_NAME+"(ID INTEGER  PRIMARY KEY AUTOINCREMENT)");

        insertObvody();
        insertUlice();
        insertTerminy();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTerminy(){
        insertTermin(2, 1, "2016-05-04");
        insertTermin(2, 2, "2016-05-05");
        insertTermin(2, 3, "2016-05-06");
        insertTermin(0, 1, "2016-05-11");
        insertTermin(0, 2, "2016-05-12");
        insertTermin(0, 3, "2016-05-13");
        insertTermin(1, 1, "2016-05-18");
        insertTermin(1, 2, "2016-05-19");
        insertTermin(1, 3, "2016-05-20");
    }

    public void insertUlice(){
        insertUlica("Abovská", "Barca");
        insertUlica("Adamova", "Krásna");
        insertUlica("Agátová", "Košická Nová Ves");
        insertUlica("Alešovo nábrežie", "Sever");

    }

    public void insertObvody(){
        insertObvod("Juh", 1);
        insertObvod("Barca", 1);
        insertObvod("Šebastovce", 1);
        insertObvod("Krásna", 1);
        insertObvod("Vyšné Opátske", 1);
        insertObvod("Myslava", 2);
        insertObvod("Pereš", 2);
        insertObvod("Lorinčík", 2);
        insertObvod("Poľov", 2);
        insertObvod("Šaca", 2);
        insertObvod("Ľudvíkov Dvor", 2);
        insertObvod("Západ", 2);
        insertObvod("Kavečany", 2);
        insertObvod("Ťahanovce - obec", 2);
        insertObvod("Sever", 3);
        insertObvod("Džungľa", 3);
        insertObvod("Košická Nová Ves", 3);
    }

    public boolean insertUlica (String ulica, String mc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ulica", ulica);
        contentValues.put("mc", mc);
        db.insert(TABLE_MC, null, contentValues);
        return true;
    }

    public boolean insertObvod (String mc, int ido) {
        /*
        1, 2, 3
         */
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mc", mc);
        db.insert(TABLE_GROUPS, null, contentValues);
        return true;
    }

    public boolean insertTermin (int odpad, int ido, String datum) {
        /*
        papier 0
        sklo 1
        plast+kov 2
         */
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("odpad", odpad);
        contentValues.put("ido", ido);
        contentValues.put("datum", datum);
        db.insert(TABLE_DATES, null, contentValues);
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
        return odvoz;
    }

}
