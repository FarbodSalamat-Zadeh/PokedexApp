/*
 * Copyright 2016-2017 Farbod Salamat-Zadeh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.satsumasoftware.pokedex.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.satsumasoftware.pokedex.framework.ability.MiniAbility;

import java.util.ArrayList;

public final class AbilitiesDBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "AbilitiesDBHelper";

    /* General Database and Table information */
    private static final String DATABASE_NAME = "abilities.db";
    public static final String TABLE_NAME = "abilities";
    public static final int DATABASE_VERSION = 10;

    /* All Column Names */
    public static final String COL_ID = "id";
    public static final String COL_GENERATION_ID = "generation_id";

    public static final String COL_NAME = "name_en";
    public static final String COL_NAME_JAPANESE = "name_ja";
    public static final String COL_NAME_KOREAN = "name_ko";
    public static final String COL_NAME_FRENCH = "name_fr";
    public static final String COL_NAME_GERMAN = "name_de";
    public static final String COL_NAME_SPANISH = "name_es";
    public static final String COL_NAME_ITALIAN = "name_it";

    /* SQL CREATE command creates all columns as defined above */
    private static final String SQL_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            COL_ID + " INTEGER, " +
            COL_GENERATION_ID + " INTEGER, " +
            COL_NAME + " TEXT, " +
            COL_NAME_JAPANESE + " TEXT, " +
            COL_NAME_KOREAN + " TEXT, " +
            COL_NAME_FRENCH + " TEXT, " +
            COL_NAME_GERMAN + " TEXT, " +
            COL_NAME_SPANISH + " TEXT, " +
            COL_NAME_ITALIAN + " TEXT" +
            ")";

    /* SQL DROP command deletes the SQL table */
    private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context mContext;

    private static AbilitiesDBHelper sInstance;

    public static synchronized AbilitiesDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AbilitiesDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private AbilitiesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Creating database");
        db.execSQL(SQL_CREATE);
        populateDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOG_TAG, "Upgrading database");
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }

    private void populateDatabase(SQLiteDatabase db) {
        PokeDB pokeDB = PokeDB.getInstance(mContext);
        Cursor cursor = pokeDB.getReadableDatabase().query(
                PokeDB.Abilities.TABLE_NAME,
                null,
                PokeDB.Abilities.COL_IS_MAIN_SERIES + "=1",  // if "is_main_series" (not conquest)
                null, null, null, null);
        cursor.moveToFirst();
        db.beginTransaction();
        while (!cursor.isAfterLast()) {
            ContentValues values = new ContentValues();

            int id = cursor.getInt(cursor.getColumnIndex(PokeDB.Abilities.COL_ID));
            values.put(COL_ID, id);

            // the identifier will not be used so it's not put in db

            values.put(COL_GENERATION_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Abilities.COL_GENERATION_ID)));

            putNameValues(values, id, pokeDB);

            db.insert(TABLE_NAME, null, values);

            Log.d(LOG_TAG, "Added ability");

            cursor.moveToNext();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        pokeDB.close();
    }

    private void putNameValues(ContentValues values, int abilityId, PokeDB pokeDB) {
        Cursor cursor = pokeDB.getReadableDatabase().query(
                PokeDB.AbilityNames.TABLE_NAME,
                null,
                PokeDB.AbilityNames.COL_ABILITY_ID + "=?",
                new String[] {String.valueOf(abilityId)},
                null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int languageId =
                    cursor.getInt(cursor.getColumnIndex(PokeDB.AbilityNames.COL_LOCAL_LANGUAGE_ID));
            String name =
                    cursor.getString(cursor.getColumnIndex(PokeDB.AbilityNames.COL_NAME));

            switch (languageId) {
                case 1:
                    values.put(COL_NAME_JAPANESE, name);
                    break;
                case 3:
                    values.put(COL_NAME_KOREAN, name);
                    break;
                case 5:
                    values.put(COL_NAME_FRENCH, name);
                    break;
                case 6:
                    values.put(COL_NAME_GERMAN, name);
                    break;
                case 7:
                    values.put(COL_NAME_SPANISH, name);
                    break;
                case 8:
                    values.put(COL_NAME_ITALIAN, name);
                    break;
                case 9:
                    values.put(COL_NAME, name);
                    break;
                default:
                    throw new IllegalArgumentException("language id '" + languageId +
                            "' is invalid");
            }
            cursor.moveToNext();
        }
        cursor.close();
    }


    public ArrayList<MiniAbility> getAllMiniAbilities() {
        ArrayList<MiniAbility> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                MiniAbility.DB_COLUMNS,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            MiniAbility ability = new MiniAbility(id, name);
            list.add(ability);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
