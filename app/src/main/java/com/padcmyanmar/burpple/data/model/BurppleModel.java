package com.padcmyanmar.burpple.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.burpple.BurppleApp;
import com.padcmyanmar.burpple.data.vo.BurpplePromotionShopVO;
import com.padcmyanmar.burpple.data.vo.FeaturedVO;
import com.padcmyanmar.burpple.data.vo.GuidesVO;
import com.padcmyanmar.burpple.data.vo.PromotionsVO;
import com.padcmyanmar.burpple.events.RestApiEvents;
import com.padcmyanmar.burpple.network.BurppleDataAgentImpl;
import com.padcmyanmar.burpple.persistence.BurppleContract;
import com.padcmyanmar.burpple.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class BurppleModel {

    private static BurppleModel objInstance;

    private List<FeaturedVO> mFeatured;
    private List<PromotionsVO> mPromotions;
    private List<GuidesVO> mGuides;

    private int mFeaturedPageIndex = 1;
    private int mPromotionsPageIndex = 1;
    private int mGuidesPageIndex = 1;

    private BurppleModel() {
        EventBus.getDefault().register(this);
        mFeatured = new ArrayList<>();
        mPromotions = new ArrayList<>();
        mGuides = new ArrayList<>();
    }

    public static BurppleModel getInstance() {
        if (objInstance == null) {
            objInstance = new BurppleModel();
        }
        return objInstance;
    }

    public void startLoadingFeatured(Context context) {
        BurppleDataAgentImpl.getInstance().loadFeatured(AppConstants.FEATURED_ACCESS_TOKEN, mFeaturedPageIndex, context);
    }

    public List<FeaturedVO> getFeatured() {
        return mFeatured;
    }

    public void loadMoreFeatured(Context context) {
        BurppleDataAgentImpl.getInstance().loadFeatured(AppConstants.FEATURED_ACCESS_TOKEN, mFeaturedPageIndex, context);
    }

    public void startLoadingPromotions(Context context) {
        BurppleDataAgentImpl.getInstance().loadPromotions(AppConstants.PROMOTIONS_ACCESS_TOKEN, mPromotionsPageIndex, context);
    }

    public List<PromotionsVO> getPromotions() {
        return mPromotions;
    }

    public void loadMorePromotions(Context context) {
        BurppleDataAgentImpl.getInstance().loadPromotions(AppConstants.PROMOTIONS_ACCESS_TOKEN, mPromotionsPageIndex, context);
    }

    public void startLoadingGuides(Context context) {
        BurppleDataAgentImpl.getInstance().loadGuides(AppConstants.GUIDES_ACCESS_TOKEN, mGuidesPageIndex, context);
    }

    public List<GuidesVO> getGuides() {
        return mGuides;
    }

    public void loadMoreGuides(Context context) {
        BurppleDataAgentImpl.getInstance().loadGuides(AppConstants.GUIDES_ACCESS_TOKEN, mGuidesPageIndex, context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        mFeatured.addAll(event.getLoadFeatured());
        mFeaturedPageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] featuredCVs = new ContentValues[event.getLoadFeatured().size()];

        for (int index = 0; index < featuredCVs.length; index++ ) {
            featuredCVs[index] = event.getLoadFeatured().get(index).parseToContentValues();
        }

        int insertedFeatured = event.getContext().getContentResolver().bulkInsert(BurppleContract.FeaturedEntry.CONTENT_URI, featuredCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Featured : " + insertedFeatured);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionsDataLoaded(RestApiEvents.PromotionsDataLoadedEvent event) {
        mPromotions.addAll(event.getLoadPromotions());
        mPromotionsPageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] promotionsCVs = new ContentValues[event.getLoadPromotions().size()];

        List<ContentValues> promotionShopCVList = new ArrayList<>();

        List<ContentValues> promotionTermsCVList = new ArrayList<>();

        for (int index = 0; index < promotionsCVs.length; index++ ) {

            PromotionsVO promotionsVO = event.getLoadPromotions().get(index);
            promotionsCVs[index] = event.getLoadPromotions().get(index).parseToContentValues();

            BurpplePromotionShopVO promotionShopVO = promotionsVO.getBurpplePromotionShop();
            promotionShopCVList.add(promotionShopVO.parseToContentValues());

            for (String promotionTerm : promotionsVO.getBurpplePromotionTerms()) {
                ContentValues promotionTermCV = new ContentValues();
                promotionTermCV.put(BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID_IN_TERM, promotionsVO.getBurpplePromotionId());
                promotionTermCV.put(BurppleContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_TERM, promotionTerm);
                promotionTermsCVList.add(promotionTermCV);
            }
        }

        int insertedPromotionShop = event.getContext().getContentResolver().bulkInsert(BurppleContract.BurpplePromotionShopEntry.CONTENT_URI, promotionShopCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotion Shop : " + insertedPromotionShop);

        int insertedPromotionTerms = event.getContext().getContentResolver().bulkInsert(BurppleContract.BurpplePromotionTermsEntry.CONTENT_URI, promotionTermsCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotion Terms : " + insertedPromotionTerms);

        int insertedPromotions = event.getContext().getContentResolver().bulkInsert(BurppleContract.PromotionsEntry.CONTENT_URI, promotionsCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotions : " + insertedPromotions);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuidesDataLoaded(RestApiEvents.GuidesDataLoadedEvent event) {
        mGuides.addAll(event.getLoadGuides());
        mGuidesPageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] guidesCVs = new ContentValues[event.getLoadGuides().size()];

        for (int index = 0; index < guidesCVs.length; index++ ) {
            guidesCVs[index] = event.getLoadGuides().get(index).parseToContentValues();
        }

        int insertedGuides = event.getContext().getContentResolver().bulkInsert(BurppleContract.GuidesEntry.CONTENT_URI, guidesCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Guides : " + insertedGuides);
    }
}
