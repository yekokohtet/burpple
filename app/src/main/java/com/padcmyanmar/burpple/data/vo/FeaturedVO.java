package com.padcmyanmar.burpple.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.burpple.persistence.BurppleContract;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class FeaturedVO {

    @SerializedName("burpple-featured-id")
    private String burppleFeaturedId;

    @SerializedName("burpple-featured-image")
    private String burppleFeaturedImage;

    @SerializedName("burpple-featured-title")
    private String burppleFeaturedImageTitle;

    @SerializedName("burpple-featured-desc")
    private String burppleFeaturedImageDesc;

    @SerializedName("burpple-featured-tag")
    private String burppleFeaturedImageTag;

    public String getBurppleFeaturedId() {
        return burppleFeaturedId;
    }

    public String getBurppleFeaturedImage() {
        return burppleFeaturedImage;
    }

    public String getBurppleFeaturedImageTitle() {
        return burppleFeaturedImageTitle;
    }

    public String getBurppleFeaturedImageDesc() {
        return burppleFeaturedImageDesc;
    }

    public String getBurppleFeaturedImageTag() {
        return burppleFeaturedImageTag;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID, burppleFeaturedId);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE, burppleFeaturedImage);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE, burppleFeaturedImageTitle);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC, burppleFeaturedImageDesc);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TAG, burppleFeaturedImageTag);

        return contentValues;
    }

    public static FeaturedVO parseFromCursor(Cursor cursor) {

        FeaturedVO featured = new FeaturedVO();

        featured.burppleFeaturedId = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID));
        featured.burppleFeaturedImage = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE));
        featured.burppleFeaturedImageTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE));
        featured.burppleFeaturedImageDesc = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC));
        featured.burppleFeaturedImageTag = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TAG));

        return featured;
    }
}
