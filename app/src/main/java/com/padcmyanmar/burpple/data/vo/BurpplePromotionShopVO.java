package com.padcmyanmar.burpple.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.burpple.persistence.BurppleContract;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class BurpplePromotionShopVO {

    @SerializedName("burpple-shop-id")
    private String burppleShopId;

    @SerializedName("burpple-shop-name")
    private String burppleShopName;

    @SerializedName("burpple-shop-area")
    private String burppleShopArea;

    public String getBurppleShopId() {
        return burppleShopId;
    }

    public String getBurppleShopName() {
        return burppleShopName;
    }

    public String getBurppleShopArea() {
        return burppleShopArea;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID, burppleShopId);
        contentValues.put(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME, burppleShopName);
        contentValues.put(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA, burppleShopArea);

        return contentValues;
    }

    public static BurpplePromotionShopVO parseFromCursor(Cursor cursor) {

        BurpplePromotionShopVO promotionShop = new BurpplePromotionShopVO();

        promotionShop.burppleShopId = cursor.getString(cursor.getColumnIndex(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID));
        promotionShop.burppleShopName = cursor.getString(cursor.getColumnIndex(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME));
        promotionShop.burppleShopArea = cursor.getString(cursor.getColumnIndex(BurppleContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA));

        return promotionShop;
    }
}
