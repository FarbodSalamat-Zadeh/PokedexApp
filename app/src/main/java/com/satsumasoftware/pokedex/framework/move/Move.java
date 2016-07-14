package com.satsumasoftware.pokedex.framework.move;

import android.content.Context;
import android.database.Cursor;

import com.satsumasoftware.pokedex.db.MovesDBHelper;
import com.satsumasoftware.pokedex.db.PokeDB;
import com.satsumasoftware.pokedex.util.DataUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move extends BaseMove {

    private int mGenerationId, mTypeId, mPower, mPp, mAccuracy, mPriority, mTargetId,
            mDamageClassId, mEffectId, mEffectChance, mContestTypeId, mContestEffectId,
            mSuperContestEffectId;

    private String mNameJa, mNameKo, mNameFr, mNameDe, mNameEs, mNameIt;


    public Move(int id, int generationId, int typeId, int power, int pp, int accuracy, int priority,
                int targetId, int damageClassId, int effectId, int effectChance, int contestTypeId,
                int contestEffectId, int superContestEffectId, String name, String nameJa,
                String nameKo, String nameFr, String nameDe, String nameEs, String nameIt) {
        mId = id;
        mGenerationId = generationId;
        mTypeId = typeId;
        mPower = power;
        mPp = pp;
        mAccuracy = accuracy;
        mPriority = priority;
        mTargetId = targetId;
        mDamageClassId = damageClassId;
        mEffectId = effectId;
        mEffectChance = effectChance;
        mContestTypeId = contestTypeId;
        mContestEffectId = contestEffectId;
        mSuperContestEffectId = superContestEffectId;

        mName = name;
        mNameJa = nameJa;
        mNameKo = nameKo;
        mNameFr = nameFr;
        mNameDe = nameDe;
        mNameEs = nameEs;
        mNameIt = nameIt;
    }

    public Move(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_ID));
        mGenerationId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_GENERATION_ID));
        mTypeId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_TYPE_ID));
        mPower = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_POWER));
        mPp = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_PP));
        mAccuracy = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_ACCURACY));
        mPriority = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_PRIORITY));
        mTargetId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_TARGET_ID));
        mDamageClassId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_DAMAGE_CLASS_ID));
        mEffectId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_EFFECT_ID));
        mEffectChance = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_EFFECT_CHANCE));
        mContestTypeId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_CONTEST_TYPE_ID));
        mContestEffectId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_CONTEST_EFFECT_ID));
        mSuperContestEffectId = cursor.getInt(cursor.getColumnIndex(MovesDBHelper.COL_SUPER_CONTEST_EFFECT_ID));

        mName = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME));
        mNameJa = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_JAPANESE));
        mNameKo = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_KOREAN));
        mNameFr = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_FRENCH));
        mNameDe = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_GERMAN));
        mNameEs = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_SPANISH));
        mNameIt = cursor.getString(cursor.getColumnIndex(MovesDBHelper.COL_NAME_ITALIAN));
    }

    public int getGenerationId() {
        return mGenerationId;
    }

    public int getTypeId() {
        return mTypeId;
    }

    public boolean hasPower() {
        return mPower != DataUtils.NULL_INT;
    }

    public int getPower() {
        return mPower;
    }

    public int getPp() {
        return mPp;
    }

    public boolean hasAccuracy() {
        return mAccuracy != DataUtils.NULL_INT;
    }

    public int getAccuracy() {
        return mAccuracy;
    }

    public int getPriority() {
        return mPriority;
    }

    public int getTargetId() {
        return mTargetId;
    }

    public int getDamageClassId() {
        return mDamageClassId;
    }

    public int getEffectId() {
        return mEffectId;
    }

    public boolean hasEffectChance() {
        return mEffectChance != DataUtils.NULL_INT;
    }

    public int getEffectChance() {
        return mEffectChance;
    }

    public boolean hasContestType() {
        return mContestTypeId != DataUtils.NULL_INT;
    }

    public int getContestTypeId() {
        return mContestTypeId;
    }

    public boolean hasContestEffect() {
        return mContestEffectId != DataUtils.NULL_INT;
    }

    public int getContestEffectId() {
        return mContestEffectId;
    }

    public boolean hasSuperContestEffect() {
        return mSuperContestEffectId != DataUtils.NULL_INT;
    }

    public int getSuperContestEffectId() {
        return mSuperContestEffectId;
    }

    public String getNameJapanese() {
        return mNameJa;
    }

    public String getNameKorean() {
        return mNameKo;
    }

    public String getNameFrench() {
        return mNameFr;
    }

    public String getNameGerman() {
        return mNameDe;
    }

    public String getNameSpanish() {
        return mNameEs;
    }

    public String getNameItalian() {
        return mNameIt;
    }


    public String getEffectProse(Context context, boolean shortEffect) {
        return getEffectProse(context, 9, shortEffect);  // English language
    }

    public String getEffectProse(Context context, int langId, boolean shortEffect) {
        PokeDB pokeDB = new PokeDB(context);
        Cursor cursor = pokeDB.getReadableDatabase().query(
                PokeDB.MoveEffectProse.TABLE_NAME,
                null,
                PokeDB.MoveEffectProse.COL_MOVE_EFFECT_ID + "=? AND " +
                        PokeDB.MoveEffectProse.COL_LOCAL_LANGUAGE_ID + "=?",
                new String[] {String.valueOf(mEffectId), String.valueOf(langId)},
                null, null, null);
        cursor.moveToFirst();
        String effectText = shortEffect ?
                cursor.getString(cursor.getColumnIndex(PokeDB.MoveEffectProse.COL_SHORT_EFFECT)) :
                cursor.getString(cursor.getColumnIndex(PokeDB.MoveEffectProse.COL_EFFECT));
        cursor.close();

        effectText = effectText.replace("$effect_chance", String.valueOf(mEffectChance));

        // matching the pattern: [label]{category:target}
        Pattern pattern = Pattern.compile("\\[(.*?)\\]\\{(.*?):(.*?)\\}");

        Matcher matcher = pattern.matcher(effectText);
        while (matcher.find()) {
            String entirePattern = matcher.group(0);
            String label = matcher.group(1);
            String category = matcher.group(2);
            String target = matcher.group(3);

            String displayedText = label.equals("") ? target : label;

            effectText = effectText.replace(entirePattern, displayedText);
        }

        return effectText;
    }

}
