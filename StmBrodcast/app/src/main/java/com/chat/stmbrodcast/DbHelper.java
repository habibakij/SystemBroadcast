package com.chat.stmbrodcast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "allNumber";
    private static final String CREATE_DATABASE="CREATE TABLE "+DbContact.TABLE_NAME+ "( id integer primary key autoincrement,"+DbContact.INCOMING_NUMBER+" text);";
    private static final String DROP_TABLE= "drop table if exists "+DbContact.TABLE_NAME;


    //String s=("CREATE TABLE " + DbContact.TABLE_NAME +"(" +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +DbContact.INCOMINT_NUMBER+ " TEXT "+")");

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void SaveNumber(String number, SQLiteDatabase sqLiteDatabase){
        ContentValues cv= new ContentValues();
        cv.put(DbContact.INCOMING_NUMBER, number);
        sqLiteDatabase.insert(DbContact.TABLE_NAME,null,cv);
        //return check;
    }

    public Cursor Display_Data(){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+DbContact.TABLE_NAME, null);
        return cursor;
    }
}
