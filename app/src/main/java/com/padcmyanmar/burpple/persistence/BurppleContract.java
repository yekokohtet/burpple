package com.padcmyanmar.burpple.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padcmyanmar.burpple.BurppleApp;

/**
 * Created by yekokohtet on 1/19/18.
 */

public class BurppleContract {

    public static final String CONTENT_AUTHORITY = BurppleApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FEATURED = "featured";
    public static final String PATH_PROMOTIONS = "promotions";
    public static final String PATH_PROMOTION_SHOP = "burpple_promotion_shop";
    public static final String PATH_PROMOTION_TERMS = "burpple_promotion_terms";
    public static final String PATH_GUIDES = "guides";

    public static final class FeaturedEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURED).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String TABLE_NAME = PATH_FEATURED;

        public static final String COLUMN_BURPPLE_FEATURED_ID = "burpple_featured_id";
        public static final String COLUMN_BURPPLE_FEATURED_IMAGE = "burpple_featured_image";
        public static final String COLUMN_BURPPLE_FEATURED_TITLE = "burpple_featured_title";
        public static final String COLUMN_BURPPLE_FEATURED_DESC = "burpple_featured_desc";
        public static final String COLUMN_BURPPLE_FEATURED_TAG = "burpple_featured_tag";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class PromotionsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTIONS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String TABLE_NAME = PATH_PROMOTIONS;

        public static final String COLUMN_BURPPLE_PROMOTION_ID = "burpple_promotion_id";
        public static final String COLUMN_BURPPLE_PROMOTION_IMAGE = "burpple_promotion_image";
        public static final String COLUMN_BURPPLE_PROMOTION_TITLE = "burpple_promotion_title";
        public static final String COLUMN_BURPPLE_PROMOTION_UNTIL = "burpple_promotion_until";
        public static final String COLUMN_IS_BURPPLE_EXCLUSIVE = "is_burpple_exclusive";
        public static final String COLUMN_BURPPLE_SHOP_ID = "burpple_shop_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class BurpplePromotionShopEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION_SHOP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;

        public static final String TABLE_NAME = PATH_PROMOTION_SHOP;

        public static final String COLUMN_BURPPLE_SHOP_ID = "burpple_shop_id";
        public static final String COLUMN_BURPPLE_SHOP_NAME = "burpple_shop_name";
        public static final String COLUMN_BURPPLE_SHOP_AREA = "burpple_shop_area";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class BurpplePromotionTermsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION_TERMS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_TERMS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_TERMS;

        public static final String TABLE_NAME = PATH_PROMOTION_TERMS;

        public static final String COLUMN_BURPPLE_PROMOTION_TERM = "burpple_promotion_term";
        public static final String COLUMN_BURPPLE_PROMOTION_ID_IN_TERM = "burpple_promotion_id__in_term";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GuidesEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String TABLE_NAME = PATH_GUIDES;

        public static final String COLUMN_BURPPLE_GUIDE_ID = "burpple_guide_id";
        public static final String COLUMN_BURPPLE_GUIDE_IMAGE = "burpple_guide_image";
        public static final String COLUMN_BURPPLE_GUIDE_TITLE = "burpple_guide_title";
        public static final String COLUMN_BURPPLE_GUIDE_DESC = "burpple_guide_desc";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
