package com.padcmyanmar.burpple.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yekokohtet on 1/19/18.
 */

public class BurppleDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mmBurpple.db";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_FEATURED_TABLE = "CREATE TABLE " + BurppleContract.FeaturedEntry.TABLE_NAME + " (" +
            BurppleContract.FeaturedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID + " VARCHAR (256), " +
            BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TAG + " TEXT, " +

            " UNIQUE (" + BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_PROMOTIONS_TABLE = "CREATE TABLE " + BurppleContract.PromotionsEntry.TABLE_NAME + " (" +
            BurppleContract.PromotionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_ID + " VARCHAR (256), " +
            BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_IMAGE + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_TITLE + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_UNTIL + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_IS_BURPPLE_EXCLUSIVE + " INTEGER DEFAULT 0, " +
            BurppleContract.PromotionsEntry.COLUMN_BURPPLE_SHOP_ID + " VARCHAR (256), " +

            " UNIQUE (" + BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_PROMOTION_SHOP_TABLE = "CREATE TABLE " + BurppleContract.BurpplePromotionShopEntry.TABLE_NAME + " (" +
            BurppleContract.BurpplePromotionShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID + " VARCHAR (256), " +
            BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME + " TEXT, " +
            BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA + " TEXT, " +

            " UNIQUE (" + BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_PROMOTION_TERMS_TABLE = "CREATE TABLE " + BurppleContract.BurpplePromotionTermsEntry.TABLE_NAME + " (" +
            BurppleContract.BurpplePromotionTermsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID_IN_TERM + " VARCHAR (256), " +
            BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_TERM + " TEXT, " +

            " UNIQUE (" + BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID_IN_TERM + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_GUIDES_TABLE = "CREATE TABLE " + BurppleContract.GuidesEntry.TABLE_NAME + " (" +
            BurppleContract.GuidesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.GuidesEntry.COLUMN_BURPPLE_GUIDE_ID + " VARCHAR (256), " +
            BurppleContract.GuidesEntry.COLUMN_BURPPLE_GUIDE_IMAGE + " TEXT, " +
            BurppleContract.GuidesEntry.COLUMN_BURPPLE_GUIDE_TITLE + " TEXT, " +
            BurppleContract.GuidesEntry.COLUMN_BURPPLE_GUIDE_DESC + " TEXT, " +

            " UNIQUE (" + BurppleContract.GuidesEntry.COLUMN_BURPPLE_GUIDE_ID + ") ON CONFLICT REPLACE" +
            " );";


    public BurppleDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FEATURED_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GUIDES_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_PROMOTION_TERMS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PROMOTION_SHOP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PROMOTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BurppleContract.PromotionsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BurppleContract.BurpplePromotionShopEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BurppleContract.BurpplePromotionTermsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BurppleContract.GuidesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BurppleContract.FeaturedEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
