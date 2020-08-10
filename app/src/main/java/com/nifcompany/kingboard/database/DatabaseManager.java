package com.nifcompany.kingboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.util.Log;


import com.nifcompany.kingboard.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maihan Nijat on 2016-12-30.
 */

public class DatabaseManager {

    private final Context mContext;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public DatabaseManager(Context context) {
        if(db != null) {
            db.close();
        }

        this.mContext = context;
        Log.d("Create Database", "Database");
    }

    /**
     * The method returns the list of words to appear as suggestions.
     * @return wordList
     */
    public ArrayList<String> getAllRow(String str, String subType) {
        ArrayList<String> wordList = new ArrayList<>();
        try {

            queryString(str, subType);

            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    String word = cursor.getString(0);
                    word = String.valueOf(Html.fromHtml(String.valueOf(word)));
                    wordList.add(word);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
        }
        return wordList;
    }
    public void open() throws SQLException {
        dbHelper.getWritableDatabase();
    }

    private void queryString(String str, String subType) {
        dbHelper.getReadableDatabase();

        switch (subType) {
            case "english":
                cursor =   db.rawQuery("SELECT " + getWordColumnName() + " FROM " + getEnglishTableName() + " WHERE " + getWordColumnName()
                        + " LIKE '" + str + "%' ORDER BY " + getFreqColumnName() + " DESC LIMIT 10", null);
                break;
            case "pashto":
                cursor = db.rawQuery("SELECT " + getWordColumnName() + " FROM " + getPashtoTableName() + " WHERE " + getWordColumnName()
                        + " LIKE '" + str + "%' AND " + getFreqColumnName() + " > 10 ORDER BY " + getFreqColumnName() + " DESC LIMIT 10", null);
                break;
            case "farsi":
                cursor = db.rawQuery("SELECT " + getWordColumnName() + " FROM " + getFarsiTableName() + " WHERE " + getWordColumnName()
                        + " LIKE '" + str + "%' AND " + getFreqColumnName() + " > 10 ORDER BY " + getFreqColumnName() + " DESC LIMIT 10", null);
                break;
            default:
                break;
        }

        dbHelper.close();
    }

    public void delete(String str, String subType) {
        String query = "";
        switch (subType) {
            case "english":
                query = ("DELETE FROM " + getEnglishTableName() + " WHERE " + getWordColumnName()
                        + " = \"" + str + "\"");
                break;
            case "pashto":
                query = ("DELETE FROM " + getPashtoTableName() + " WHERE " + getWordColumnName()
                        + " = '" + str + "'");
                break;
            case "farsi":
                query = ("DELETE FROM " + getFarsiTableName() + " WHERE " + getWordColumnName()
                        + " = '" + str + "'");
                break;
            default:
                break;
        }

        db.execSQL(query);
    }

    /**
     * The method adds the new words into database to use it in suggestions
     */
    public void insertNewRecord(String str, String subType) {

        String tableName = "";

        switch (subType) {
            case "en_US":
                tableName = getEnglishTableName();
                break;
            case "fa_AF":
                tableName = getFarsiTableName();
                break;
            case "ps_AF":
                tableName = getPashtoTableName();
                break;
            default:
                break;
        }

        if(!tableName.equals("")) {
            dbHelper.open();

            ContentValues values = new ContentValues();
            values.put("freq", 1);
            values.put("word", str);

            db.insert(tableName, null, values);
            db.close();
        }
    }
    /**
     * The method adds the new words into database to use it in suggestions
     */
    public void updateRecord(String str, Integer freq, String subType) {

        String tableName = "";

        switch (subType) {
            case "en_US":
                tableName = getEnglishTableName();
                break;
            case "fa_AF":
                tableName = getFarsiTableName();
                break;
            case "ps_AF":
                tableName = getPashtoTableName();
                break;
            default:
                break;
        }

        if(!tableName.equals("")) {

            String insertQuery = "UPDATE " + tableName
                    + " SET " + getFreqColumnName() + "= " + (freq + 1) + " WHERE word = '" + str + "'";
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(insertQuery);
        }
    }

    public Integer getWordFrequency(String str, String subType) {

        String tableName = "";

        switch (subType) {
            case "en_US":
                tableName = getEnglishTableName();
                break;
            case "fa_AF":
                tableName = getFarsiTableName();
                break;
            case "ps_AF":
                tableName = getPashtoTableName();
                break;
            default:
                break;
        }

        Integer freq = 0;

        dbHelper = new DatabaseHelper(mContext, getDatabaseName());
        dbHelper.open();

        if(!tableName.equals("")) {

            try {
                cursor = db.rawQuery("SELECT " + getFreqColumnName() + " FROM " + tableName + " WHERE " + getWordColumnName()
                        + " = '" + str + "'", null);

                cursor.moveToFirst();
                if (!cursor.isAfterLast()) {
                    do {
                        freq = cursor.getInt(0);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("DB ERROR", e.toString());
            }
        }
        return freq;
    }

    public ArrayList<HashMap<String, String>> getJudul(ArrayList<HashMap<String, String>> arrayData){

        dbHelper = new DatabaseHelper(mContext, "db_king_board");
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            cursor = db.rawQuery("SELECT  * FROM tbl_text  LIMIT 3", null);

            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                HashMap<String,String> user = new HashMap<>();
                do {
                    user.put("judul",cursor.getString(cursor.getColumnIndex("judul")));
                    Log.d("TAG "," Isi Judul : " + cursor.getString(cursor.getColumnIndex("judul;")));
                    user.put("teks",cursor.getString(cursor.getColumnIndex("teks")));
                    Log.d("TAG "," Isi Teks : " + cursor.getString(cursor.getColumnIndex("teks;")));
                    arrayData.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
        }
        
        return  arrayData;
    }

    private String getDatabaseName() {
        return mContext.getResources().getString(R.string.database_name);
    }

    private String getEnglishTableName() {
        return mContext.getResources().getString(R.string.en_table_name);
    }

    private String getPashtoTableName() {
        return mContext.getResources().getString(R.string.pa_table_name);
    }

    private String getFarsiTableName() {
        return mContext.getResources().getString(R.string.fa_table_name);
    }

    private String getFreqColumnName() {
        return mContext.getResources().getString(R.string.freq_column);
    }

    private String getWordColumnName() {
        return mContext.getResources().getString(R.string.word_column);
    }

    /**
     * Close database connection.
     */
    public void close() {
        if(cursor != null) { cursor.close(); }
        if(db != null) { db.close(); }
    }
}