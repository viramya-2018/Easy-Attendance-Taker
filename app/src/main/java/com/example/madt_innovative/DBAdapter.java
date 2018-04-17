package com.example.madt_innovative;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 17-Apr-18.
 */

public class DBAdapter {

    public ArrayList<String> listDataHeader;
    public HashMap<String, List<String>> listDataChild;

    private static final String dbName = "innovative_madt";
    private static final int dbVersion = 3;
    private static final String  tableName = "classDetails";
    private static final String className = "className";
    private static final String sectionName = "sectionName";
    private static final String numberOfStudent = "numberOfStudent";

    private static final String createTable = "create table " +
            tableName +
            "( " +
            className + " text not null , " +
            sectionName + " text not null , " +
            numberOfStudent + " text not null );";
    /*
        create table classDetails (className text not null ,sectionName text not null ,numOfStudent text not null )
     */
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DBHelper dbHelper;

    DBAdapter(Context context){
        this.context = context;
        dbHelper = new DBHelper(context, dbName, null, dbVersion);
    }

    public DBAdapter open(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void display(){
        sqLiteDatabase = dbHelper.getReadableDatabase();

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        Cursor cursorEmployees = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);
        if (cursorEmployees.moveToFirst()) {
            do{
                String cn = cursorEmployees.getString(0);
                String sn = cursorEmployees.getString(1);
                String nos = cursorEmployees.getString(2);
                List<String> temp = new ArrayList<String>();
                temp.add(sn);
                temp.add(nos);
                listDataHeader.add(cn);
                listDataChild.put(cn, temp);
            }while(cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
    }

    public void delete(String delStr){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete(tableName, className + "=?", new String[]{delStr});
    }

    public long insertFunction(String cn, String sn, String nos){
        ContentValues contentValues = new ContentValues();
        contentValues.put(className, cn);
        contentValues.put(sectionName, sn);
        contentValues.put(numberOfStudent, nos);
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