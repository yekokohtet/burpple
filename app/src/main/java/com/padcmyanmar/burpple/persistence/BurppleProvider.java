package com.padcmyanmar.burpple.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by yekokohtet on 1/19/18.
 */

public class BurppleProvider extends ContentProvider {

    public static final int FEATURED = 100;
    public static final int GUIDES = 200;
    public static final int PROMOTION_TERMS = 300;
    public static final int PROMOTION_SHOP = 400;
    public static final int PROMOTIONS = 500;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final SQLiteQueryBuilder sPromotionsWithPromotionShop_IJ;

    private static final SQLiteQueryBuilder sPromotionsWithPromotionTerms_IJ;

    static {

        sPromotionsWithPromotionShop_IJ = new SQLiteQueryBuilder();
        sPromotionsWithPromotionShop_IJ.setTables(
                BurppleContract.PromotionsEntry.TABLE_NAME + " INNER JOIN " +
                        BurppleContract.BurpplePromotionShopEntry.TABLE_NAME + " ON " +
                        BurppleContract.PromotionsEntry.TABLE_NAME + " . " + BurppleContract.PromotionsEntry.COLUMN_BURPPLE_SHOP_ID + " = " +
                        BurppleContract.BurpplePromotionShopEntry.TABLE_NAME + " . " + BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID
        );

        sPromotionsWithPromotionTerms_IJ = new SQLiteQueryBuilder();
        sPromotionsWithPromotionTerms_IJ.setTables(
                BurppleContract.PromotionsEntry.TABLE_NAME + " INNER JOIN " +
                        BurppleContract.BurpplePromotionTermsEntry.TABLE_NAME + " ON " +
                        BurppleContract.PromotionsEntry.TABLE_NAME + " . " + BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_ID + " = " +
                        BurppleContract.BurpplePromotionTermsEntry.TABLE_NAME + " . " + BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID_IN_TERM

        );
    }


    private BurppleDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_FEATURED, FEATURED);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_GUIDES, GUIDES);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTION_TERMS, PROMOTION_TERMS);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTION_SHOP, PROMOTION_SHOP);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTIONS, PROMOTIONS);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {

        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.TABLE_NAME;
            case GUIDES:
                return BurppleContract.GuidesEntry.TABLE_NAME;
            case PROMOTION_TERMS:
                return BurppleContract.BurpplePromotionTermsEntry.TABLE_NAME;
            case PROMOTION_SHOP:
                return BurppleContract.BurpplePromotionShopEntry.TABLE_NAME;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.TABLE_NAME;
        }
        return null;
    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.CONTENT_URI;
            case GUIDES:
                return BurppleContract.GuidesEntry.CONTENT_URI;
            case PROMOTION_TERMS:
                return BurppleContract.BurpplePromotionTermsEntry.CONTENT_URI;
            case PROMOTION_SHOP:
                return BurppleContract.BurpplePromotionShopEntry.CONTENT_URI;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.CONTENT_URI;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new BurppleDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor queryCursor;
        switch (sUriMatcher.match(uri)) {
            case PROMOTIONS:
                queryCursor = sPromotionsWithPromotionShop_IJ.query(mDBHelper.getReadableDatabase(),
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);
                break;

            case PROMOTION_TERMS:
                queryCursor = sPromotionsWithPromotionTerms_IJ.query(mDBHelper.getReadableDatabase(),
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);
                break;

            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);
        }

        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.DIR_TYPE;
            case GUIDES:
                return BurppleContract.GuidesEntry.DIR_TYPE;
            case PROMOTION_TERMS:
                return BurppleContract.BurpplePromotionTermsEntry.DIR_TYPE;
            case PROMOTION_SHOP:
                return BurppleContract.BurpplePromotionShopEntry.DIR_TYPE;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, s, strings);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, s, strings);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
