package com.padcmyanmar.burpple.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.adapters.BurppleFeaturedPagerAdapter;
import com.padcmyanmar.burpple.adapters.BurppleGuidesAdapter;
import com.padcmyanmar.burpple.adapters.BurpplePromotionsAdapter;
import com.padcmyanmar.burpple.components.EmptyViewPod;
import com.padcmyanmar.burpple.components.SmartRecyclerView;
import com.padcmyanmar.burpple.data.model.BurppleModel;
import com.padcmyanmar.burpple.data.vo.FeaturedVO;
import com.padcmyanmar.burpple.data.vo.GuidesVO;
import com.padcmyanmar.burpple.data.vo.PromotionsVO;
import com.padcmyanmar.burpple.events.RestApiEvents;
import com.padcmyanmar.burpple.persistence.BurppleContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BurppleFoodPlacesActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int FEATURED_LIST_LOADER_ID = 1001;
    private static final int PROMOTIONS_LIST_LOADER_ID = 1002;
    private static final int GUIDES_LIST_LOADER_ID = 1003;

    @BindView(R.id.rv_promotions)
    SmartRecyclerView rvPromotions;

    @BindView(R.id.rv_burpple_guides)
    SmartRecyclerView rvBurppleGuides;

    @BindView(R.id.vp_food_places)
    ViewPager vpFoodPlaces;

    @BindView(R.id.vp_empty_promotions)
    EmptyViewPod vpEmptyPromotions;

    @BindView(R.id.vp_empty_guides)
    EmptyViewPod vpEmptyGuides;

    private BurppleFeaturedPagerAdapter mFeaturedPagerAdapter;
    private BurpplePromotionsAdapter mPromotionsAdapter;
    private BurppleGuidesAdapter mGuidesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burpple_food_places);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this, this);

        BurppleModel.getInstance().startLoadingFeatured(getApplicationContext());
        BurppleModel.getInstance().startLoadingPromotions(getApplicationContext());
        BurppleModel.getInstance().startLoadingGuides(getApplicationContext());

        mFeaturedPagerAdapter = new BurppleFeaturedPagerAdapter(getApplicationContext());
        vpFoodPlaces.setAdapter(mFeaturedPagerAdapter);
        vpFoodPlaces.setOffscreenPageLimit(mFeaturedPagerAdapter.getCount());

        vpEmptyPromotions.setEmptyData("No Promotion to Display");
        rvPromotions.setEmptyView(vpEmptyPromotions);
        rvPromotions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mPromotionsAdapter = new BurpplePromotionsAdapter(getApplicationContext());
        rvPromotions.setAdapter(mPromotionsAdapter);

        vpEmptyGuides.setEmptyData("No Burpple Guide to Display");
        rvBurppleGuides.setEmptyView(vpEmptyGuides);
        rvBurppleGuides.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mGuidesAdapter = new BurppleGuidesAdapter(getApplicationContext());
        rvBurppleGuides.setAdapter(mGuidesAdapter);

        getSupportLoaderManager().initLoader(PROMOTIONS_LIST_LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        List<FeaturedVO> featuredList = BurppleModel.getInstance().getFeatured();
        if (!featuredList.isEmpty()) {
            mFeaturedPagerAdapter.setNewData(featuredList);
        }

        List<PromotionsVO> promotionsList = BurppleModel.getInstance().getPromotions();
        if (!featuredList.isEmpty()) {
            mPromotionsAdapter.setNewData(promotionsList);
        }

        List<GuidesVO> guidesList = BurppleModel.getInstance().getGuides();
        if (!featuredList.isEmpty()) {
            mGuidesAdapter.setNewData(guidesList);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_search_box)
    public void moveToBurppleSearch() {
        Intent intent = BurppleSearchActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        mFeaturedPagerAdapter.appendNewData(event.getLoadFeatured());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPromotionsDataLoaded(RestApiEvents.PromotionsDataLoadedEvent event) {
        mPromotionsAdapter.appendNewData(event.getLoadPromotions());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGuidesDataLoaded(RestApiEvents.GuidesDataLoadedEvent event) {
        mGuidesAdapter.appendNewData(event.getLoadGuides());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvPromotions, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                BurppleContract.PromotionsEntry.CONTENT_URI,
                null,
                null, null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<PromotionsVO> promotionsList = new ArrayList<>();

            do {
                PromotionsVO promotions = PromotionsVO.parseFromCursor(getApplicationContext(), data);
                promotionsList.add(promotions);
            } while (data.moveToNext());

            mPromotionsAdapter.setNewData(promotionsList);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
