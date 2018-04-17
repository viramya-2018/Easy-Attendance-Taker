package com.example.madt_innovative;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 17-Apr-18.
 */

public class DBAdapter_student {

    public String classNameFromDB, rollNumberFromDB;

    private static final String dbName = "innovative_madt";
    private static final int dbVersion = 3;
    private static final String  tableName = "student_roll";
    private static final String className = "className";
    private static final String rollNumber = "rollNumber";


    private static final String createTable = "create table " +
            tableName +
            "( " +
            className + " text not null , " +
            rollNumber + " text not null );";

    /*
        create table classDetails (className text not null ,sectionName text not null ,numOfStudent text not null )
     */
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DBHelper dbHelper;

    DBAdapter_student(Context context){
        this.context = context;
        dbHelper = new DBHelper(context, dbName, null, dbVersion);
    }

    public DBAdapter_student open(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void display(String a){
        sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursorEmployees = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " WHERE " + className + " = " + a + ";", null);
        if (cursorEmployees.moveToFirst()) {
            String cn = cursorEmployees.getString(0);
            String rn = cursorEmployees.getString(1);
            classNameFromDB = cn;
            rollNumberFromDB = rn;
        }
        cursorEmployees.close();
    }

    public void delete(String delStr){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete(tableName, className + "=?", new String[]{delStr});
    }

    public long insertFunction(String cn, String rn){
        ContentValues contentValues = new ContentValues();
        contentValues.put(className, cn);
        contentValues.put(rollNumber, rn);
        return sqLiteDatabase.insert(tableName, null, contentValues);
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
