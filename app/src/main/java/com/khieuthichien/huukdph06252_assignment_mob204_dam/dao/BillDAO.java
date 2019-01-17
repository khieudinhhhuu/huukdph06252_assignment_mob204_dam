package com.khieuthichien.huukdph06252_assignment_mob204_dam.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.khieuthichien.huukdph06252_assignment_mob204_dam.Constant;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.Bill;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.Book;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.TypeBook;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BillDAO implements Constant{

    private DatabaseHelper databaseHelper;

    public BillDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public long insertBill(Bill bill){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HD_COLUMN_BILL_ID, bill.getIdhoadon());
        contentValues.put(HD_COLUMN_DATE, bill.getDatehoadon());

        long result = db.insert(BILL_TABLE, null, contentValues);

        db.close();
        return result;
    }

    public Bill getBill(String bill){
        Bill bill3 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(BILL_TABLE, new String[]{HD_COLUMN_BILL_ID, HD_COLUMN_DATE}, HD_COLUMN_BILL_ID + "=?",
                new String[]{String.valueOf(bill)}, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String id = cursor.getString(cursor.getColumnIndex(HD_COLUMN_BILL_ID));

            long date = cursor.getLong(cursor.getColumnIndex(HD_COLUMN_DATE));

            //khoi tao user voi cac gia tri lay duoc
            bill3 = new Bill(id, date);
        }

        cursor.close();
        return bill3;

    }


    public List<Bill> getAllBill(){

        List<Bill> billList = new ArrayList<>();

        String SELECT_ALL_BILL = "SELECT * FROM " + BILL_TABLE;

        Log.e("getAllTypeBook", SELECT_ALL_BILL);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BILL, null);

        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0){
            do {

                String id = cursor.getString(cursor.getColumnIndex(HD_COLUMN_BILL_ID));

                long date = cursor.getLong(cursor.getColumnIndex(HD_COLUMN_DATE));

                Bill bil2 = new Bill();
                bil2.setIdhoadon(id);
                bil2.setDatehoadon(date);

                //them typebook vao list<TypeBook>
                billList.add(bil2);

            }while (cursor.moveToNext());
        }else {

        }



        cursor.close();
        sqLiteDatabase.close();

        return billList;

    }


    public long updateBill(Bill bill){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HD_COLUMN_DATE, bill.getDatehoadon());

        long result = sqLiteDatabase.update(BILL_TABLE, contentValues,
                HD_COLUMN_BILL_ID + "=?", new String[]{bill.getIdhoadon()});

        sqLiteDatabase.close();
        return result;
    }


    public long deleteBill(String billId){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(BILL_TABLE, HD_COLUMN_BILL_ID + "=?", new String[]{billId});


        sqLiteDatabase.close();
        return result;

    }


}
