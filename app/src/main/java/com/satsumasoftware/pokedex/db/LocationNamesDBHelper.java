package com.satsumasoftware.pokedex.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.satsumasoftware.pokedex.util.CSVUtils;
import com.univocity.parsers.csv.CsvParser;

import java.io.IOException;
import java.util.List;

public class LocationNamesDBHelper extends SQLiteOpenHelper {

    /* General Database and Table information */
    private static final String DATABASE_NAME = "location_names.db";
    public static final String TABLE_NAME = "location_names";
    public static final int DATABASE_VERSION = 1;

    /* All Column Names */
    public static final String COL_LOCATION_ID = "location_id";
    public static final String COL_LANGUAGE_ID = "local_language_id";
    public static final String COL_NAME = "name";

    /* SQL CREATE command creates all columns as defined above */
    private static final String SQL_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            COL_LOCATION_ID + " INTEGER, " +
            COL_LANGUAGE_ID + " INTEGER, " +
            COL_NAME + " TEXT" +
            ")";

    /* SQL DROP command deletes the SQL table */
    private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context mContext;


    public LocationNamesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        populateDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }

    private void populateDatabase(SQLiteDatabase db) {
        CsvParser parser = CSVUtils.getMyParser();
        try {
            db.beginTransaction();
            List<String[]> allRows = parser.parseAll(mContext.getAssets().open(CSVUtils.LOCATION_NAMES));
            for (String[] line : allRows) {
                ContentValues values = new ContentValues();
                int locationId = Integer.parseInt(line[0]);
                values.put(COL_LOCATION_ID, locationId);
                values.put(COL_LANGUAGE_ID, Integer.parseInt(line[1]));
                values.put(COL_NAME, line[2]);
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}