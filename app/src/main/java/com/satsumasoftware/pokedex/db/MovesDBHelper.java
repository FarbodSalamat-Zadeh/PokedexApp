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

import com.satsumasoftware.pokedex.framework.move.MiniMove;

import java.util.ArrayList;

public final class MovesDBHelper extends SQLiteOpenHelper {

    /* General Database and Table information */
    private static final String DATABASE_NAME = "moves.db";
    public static final String TABLE_NAME = "moves";
    public static final int DATABASE_VERSION = 10;

    /* All Column Names */
    public static final String COL_ID = "id";
    public static final String COL_GENERATION_ID = "generation_id";
    public static final String COL_TYPE_ID = "type_id";
    public static final String COL_POWER = "power";
    public static final String COL_PP = "pp";
    public static final String COL_ACCURACY = "accuracy";
    public static final String COL_PRIORITY = "priority";
    public static final String COL_TARGET_ID = "target_id";
    public static final String COL_DAMAGE_CLASS_ID = "damage_class_id";
    public static final String COL_EFFECT_ID = "effect_id";
    public static final String COL_EFFECT_CHANCE = "effect_chance";
    public static final String COL_CONTEST_TYPE_ID = "contest_type_id";
    public static final String COL_CONTEST_EFFECT_ID = "contest_effect_id";
    public static final String COL_SUPER_CONTEST_EFFECT_ID = "super_contest_effect_id";

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
            COL_TYPE_ID + " INTEGER, " +
            COL_POWER + " INTEGER, " +
            COL_PP + " INTEGER, " +
            COL_ACCURACY + " INTEGER, " +
            COL_PRIORITY + " INTEGER, " +
            COL_TARGET_ID + " INTEGER, " +
            COL_DAMAGE_CLASS_ID + " INTEGER, " +
            COL_EFFECT_ID + " INTEGER, " +
            COL_EFFECT_CHANCE + " INTEGER, " +
            COL_CONTEST_TYPE_ID + " INTEGER, " +
            COL_CONTEST_EFFECT_ID + " INTEGER, " +
            COL_SUPER_CONTEST_EFFECT_ID + " INTEGER, " +
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

    private static MovesDBHelper sInstance;

    public static synchronized MovesDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MovesDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private MovesDBHelper(Context context) {
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
        PokeDB pokeDB = PokeDB.getInstance(mContext);
        Cursor cursor = pokeDB.getReadableDatabase().query(
                PokeDB.Moves.TABLE_NAME,
                null,
                PokeDB.Moves.COL_ID + "< 10000",  // ignore shadow moves
                null, null, null, null);
        cursor.moveToFirst();
        db.beginTransaction();
        while (!cursor.isAfterLast()) {
            ContentValues values = new ContentValues();

            int moveId = cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_ID));
            values.put(COL_ID, moveId);

            // the identifier is not used thus ignored here

            values.put(COL_GENERATION_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_GENERATION_ID)));
            values.put(COL_TYPE_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_TYPE_ID)));

            values.put(COL_POWER, cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_POWER)));
            values.put(COL_PP, cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_PP)));
            values.put(COL_ACCURACY, cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_ACCURACY)));

            values.put(COL_PRIORITY,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_PRIORITY)));
            values.put(COL_TARGET_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_TARGET_ID)));
            values.put(COL_DAMAGE_CLASS_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_DAMAGE_CLASS_ID)));
            values.put(COL_EFFECT_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_EFFECT_ID)));
            values.put(COL_EFFECT_CHANCE,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_EFFECT_CHANCE)));

            values.put(COL_CONTEST_TYPE_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_CONTEST_TYPE_ID)));
            values.put(COL_CONTEST_EFFECT_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_CONTEST_EFFECT_ID)));
            values.put(COL_SUPER_CONTEST_EFFECT_ID,
                    cursor.getInt(cursor.getColumnIndex(PokeDB.Moves.COL_SUPER_CONTEST_EFFECT_ID)));

            putNameValues(values, moveId, pokeDB);

            db.insert(TABLE_NAME, null, values);

            cursor.moveToNext();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        pokeDB.close();
    }

    private void putNameValues(ContentValues values, int moveId, PokeDB pokeDB) {
        Cursor cursor = pokeDB.getReadableDatabase().query(
                PokeDB.MoveNames.TABLE_NAME,
                null,
                PokeDB.MoveNames.COL_MOVE_ID + "=?",
                new String[] {String.valueOf(moveId)},
                null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int languageId =
                    cursor.getInt(cursor.getColumnIndex(PokeDB.MoveNames.COL_LOCAL_LANGUAGE_ID));
            String name =
                    cursor.getString(cursor.getColumnIndex(PokeDB.MoveNames.COL_NAME));

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


    public ArrayList<MiniMove> getAllMiniMoves() {
        ArrayList<MiniMove> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                MiniMove.DB_COLUMNS,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            MiniMove miniMove = new MiniMove(id, name);
            list.add(miniMove);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
