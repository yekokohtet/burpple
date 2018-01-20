package com.padcmyanmar.burpple.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.burpple.persistence.BurppleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class PromotionsVO {

    @SerializedName("burpple-promotion-id")
    private String burpplePromotionId;

    @SerializedName("burpple-promotion-image")
    private String burpplePromotionImage;

    @SerializedName("burpple-promotion-title")
    private String burpplePromotionTitle;

    @SerializedName("burpple-promotion-until")
    private String burpplePromotionUntil;

    @SerializedName("burpple-promotion-shop")
    private BurpplePromotionShopVO burpplePromotionShop;

    @SerializedName("is-burpple-exclusive")
    private Boolean isBurppleExclusive;

    @SerializedName("burpple-promotion-terms")
    private List<String> burpplePromotionTerms;

    public String getBurpplePromotionId() {
        return burpplePromotionId;
    }

    public String getBurpplePromotionImage() {
        return burpplePromotionImage;
    }

    public String getBurpplePromotionTitle() {
        return burpplePromotionTitle;
    }

    public String getBurpplePromotionUntil() {
        return burpplePromotionUntil;
    }

    public BurpplePromotionShopVO getBurpplePromotionShop() {
        return burpplePromotionShop;
    }

    public Boolean getBurppleExclusive() {
        return isBurppleExclusive;
    }

    public List<String> getBurpplePromotionTerms() {
        if (burpplePromotionTerms == null) {
            burpplePromotionTerms = new ArrayList<>();
        }
        return burpplePromotionTerms;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_ID, burpplePromotionId);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_IMAGE, burpplePromotionImage);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_TITLE, burpplePromotionTitle);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_UNTIL, burpplePromotionUntil);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_IS_BURPPLE_EXCLUSIVE, isBurppleExclusive);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_SHOP_ID, burpplePromotionShop.getBurppleShopId());

        return contentValues;
    }

    public static PromotionsVO parseFromCursor(Context context, Cursor cursor) {

        PromotionsVO promotions = new PromotionsVO();

        promotions.burpplePromotionId = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_ID));
        promotions.burpplePromotionImage = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_IMAGE));
        promotions.burpplePromotionTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_TITLE));
        promotions.burpplePromotionUntil = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_BURPPLE_PROMOTION_UNTIL));
//        promotions.isBurppleExclusive = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_IS_BURPPLE_EXCLUSIVE));

        promotions.burpplePromotionShop = BurpplePromotionShopVO.parseFromCursor(cursor);
        promotions.burpplePromotionTerms = loadPromotionTerms(context, promotions.burpplePromotionId);

        return promotions;
    }

    private static List<String> loadPromotionTerms(Context context, String promotionId) {
        Cursor promotionTermsCursor = context.getContentResolver().query(BurppleContract.BurpplePromotionTermsEntry.CONTENT_URI,
                null,
                BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID_IN_TERM + " = ?", new String[]{promotionId},
                null);

        if (promotionTermsCursor != null && promotionTermsCursor.moveToFirst()) {
            List<String> promotionTerms = new ArrayList<>();
            do {
                promotionTerms.add(
                        promotionTermsCursor.getString(
                                promotionTermsCursor.getColumnIndex(BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_TERM)));
            } while (promotionTermsCursor.moveToNext());
            promotionTermsCursor.close();
            return promotionTerms;
        }

        return null;
    }
}
