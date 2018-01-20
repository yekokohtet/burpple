package com.padcmyanmar.burpple.network;

import android.content.Context;

import com.google.gson.Gson;
import com.padcmyanmar.burpple.events.RestApiEvents;
import com.padcmyanmar.burpple.network.responses.GetFeaturedResponse;
import com.padcmyanmar.burpple.network.responses.GetGuidesResponse;
import com.padcmyanmar.burpple.network.responses.GetPromotionsResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class BurppleDataAgentImpl implements BurppleDataAgent {

    private static BurppleDataAgentImpl objInstance;

    private BurppleFoodPlacesAPI theAPI;

    private BurppleDataAgentImpl() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/burpple-food-places/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(BurppleFoodPlacesAPI.class);
    }

    public static BurppleDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new BurppleDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadFeatured(String accessToken, int pageNo, final Context context) {
        Call<GetFeaturedResponse> loadFeaturedCall = theAPI.loadFeatured(pageNo, accessToken);
        loadFeaturedCall.enqueue(new BurppleCallback<GetFeaturedResponse>() {
            @Override
            public void onResponse(Call<GetFeaturedResponse> call, Response<GetFeaturedResponse> response) {
                super.onResponse(call, response);
                GetFeaturedResponse getFeaturedResponse = response.body();
                if (getFeaturedResponse != null && getFeaturedResponse.getFeaturedList().size() > 0) {
                    RestApiEvents.FeaturedDataLoadedEvent event = new RestApiEvents.FeaturedDataLoadedEvent(
                            getFeaturedResponse.getPageNo(), getFeaturedResponse.getFeaturedList(), context
                    );
                    EventBus.getDefault().post(event);
                }
            }

        });
    }

    @Override
    public void loadPromotions(String accessToken, int pageNo, final Context context) {
        Call<GetPromotionsResponse> loadPromotionsCall = theAPI.loadPromotions(pageNo, accessToken);
        loadPromotionsCall.enqueue(new BurppleCallback<GetPromotionsResponse>() {
            @Override
            public void onResponse(Call<GetPromotionsResponse> call, Response<GetPromotionsResponse> response) {
                super.onResponse(call, response);
                GetPromotionsResponse getPromotionsResponse = response.body();
                if (getPromotionsResponse != null && getPromotionsResponse.getPromotionsList().size() > 0) {
                    RestApiEvents.PromotionsDataLoadedEvent event = new RestApiEvents.PromotionsDataLoadedEvent(
                            getPromotionsResponse.getPageNo(), getPromotionsResponse.getPromotionsList(), context
                    );
                    EventBus.getDefault().post(event);
                }
            }

        });
    }

    @Override
    public void loadGuides(String accessToken, int pageNo, final Context context) {
        Call<GetGuidesResponse> loadGuidesCall = theAPI.loadGuides(pageNo, accessToken);
        loadGuidesCall.enqueue(new BurppleCallback<GetGuidesResponse>() {
            @Override
            public void onResponse(Call<GetGuidesResponse> call, Response<GetGuidesResponse> response) {
                super.onResponse(call, response);
                GetGuidesResponse getGuidesResponse = response.body();
                if (getGuidesResponse != null && getGuidesResponse.getGuidesList().size() > 0) {
                    RestApiEvents.GuidesDataLoadedEvent event = new RestApiEvents.GuidesDataLoadedEvent(
                            getGuidesResponse.getPageNo(), getGuidesResponse.getGuidesList(), context
                    );
                    EventBus.getDefault().post(event);
                }
            }

        });
    }
}
