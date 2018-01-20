package com.padcmyanmar.burpple.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.burpple.data.vo.FeaturedVO;
import com.padcmyanmar.burpple.network.BurppleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yekokohtet on 1/18/18.
 */

public class GetFeaturedResponse extends BurppleResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("featured")
    private List<FeaturedVO> featuredList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<FeaturedVO> getFeaturedList() {
        if (featuredList == null) {
            featuredList = new ArrayList<>();
        }
        return featuredList;
    }
}
