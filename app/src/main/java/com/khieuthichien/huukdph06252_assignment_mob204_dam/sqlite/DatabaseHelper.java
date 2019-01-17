package com.khieuthichien.huukdph06252_assignment_mob204_dam.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.khieuthichien.huukdph06252_assignment_mob204_dam.Constant;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{


    public DatabaseHelper(Context context) {
        super(context, "BookManager", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user table
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TYPE_BOOK_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_BILL_TABLE);
        db.execSQL(CREATE_HOADONCHITIET_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_USER_TABLE", CREATE_USER_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_TYPE_BOOK_TABLE", CREATE_TYPE_BOOK_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_BOOK_TABLE", CREATE_BOOK_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_BILL_TABLE", CREATE_BILL_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_HOADONCHITIET_Tb", CREATE_HOADONCHITIET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TYPE_BOOK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BILL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HOADONCHITIET_TABLE);
        onCreate(db);
    }



}
