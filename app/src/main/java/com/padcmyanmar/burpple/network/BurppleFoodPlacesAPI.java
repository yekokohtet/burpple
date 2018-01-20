package com.padcmyanmar.burpple.network;

import com.padcmyanmar.burpple.network.responses.GetFeaturedResponse;
import com.padcmyanmar.burpple.network.responses.GetGuidesResponse;
import com.padcmyanmar.burpple.network.responses.GetPromotionsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yekokohtet on 1/18/18.
 */

public interface BurppleFoodPlacesAPI {

    @FormUrlEncoded
    @POST("v1/getGuides.php")
    Call<GetFeaturedResponse> loadFeatured(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken
    );

    @FormUrlEncoded
    @POST("v1/getPromotions.php")
    Call<GetPromotionsResponse> loadPromotions(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken
    );

    @FormUrlEncoded
    @POST("v1/getGuides.php")
    Call<GetGuidesResponse> loadGuides(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken
    );
}
