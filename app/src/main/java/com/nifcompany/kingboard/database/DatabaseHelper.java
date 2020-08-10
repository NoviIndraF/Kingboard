package com.nifcompany.kingboard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static String DB_PATH;
    private static String DB_NAME;
    public static SQLiteDatabase database;
    public final Context context;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS  in_table (freq VARCHAR(12), word VARCHAR(50))";
    private static final String SQL_CREATE_TABLE_TEXT = "CREATE TABLE IF NOT EXISTS  tbl_text (judul VARCHAR(50), teks VARCHAR(1000))";

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
        this.context = context;

        if(android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        DB_NAME = databaseName;

    }

    @Override
    public synchronized void close() {
        if(database != null)

        super.close();

    }

    public void open(){
        database = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("Table Word", "Table Word has ben created");
        db.execSQL(SQL_CREATE_TABLE_TEXT);
        Log.d("Table Text", "Table Text has ben created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}