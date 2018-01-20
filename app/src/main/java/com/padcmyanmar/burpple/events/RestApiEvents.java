package com.padcmyanmar.burpple.events;

import android.content.Context;

import com.padcmyanmar.burpple.data.vo.FeaturedVO;
import com.padcmyanmar.burpple.data.vo.GuidesVO;
import com.padcmyanmar.burpple.data.vo.PromotionsVO;

import java.util.List;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {

        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class FeaturedDataLoadedEvent {

        private int loadedPageIndex;
        private List<FeaturedVO> loadFeatured;
        private Context context;

        public FeaturedDataLoadedEvent(int loadedPageIndex, List<FeaturedVO> loadFeatured, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadFeatured = loadFeatured;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<FeaturedVO> getLoadFeatured() {
            return loadFeatured;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class PromotionsDataLoadedEvent {

        private int loadedPageIndex;
        private List<PromotionsVO> loadPromotions;
        private Context context;

        public PromotionsDataLoadedEvent(int loadedPageIndex, List<PromotionsVO> loadPromotions, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadPromotions = loadPromotions;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PromotionsVO> getLoadPromotions() {
            return loadPromotions;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class GuidesDataLoadedEvent {

        private int loadedPageIndex;
        private List<GuidesVO> loadGuides;
        private Context context;

        public GuidesDataLoadedEvent(int loadedPageIndex, List<GuidesVO> loadGuides, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadGuides = loadGuides;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<GuidesVO> getLoadGuides() {
            return loadGuides;
        }

        public Context getContext() {
            return context;
        }
    }
}
