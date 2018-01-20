package com.padcmyanmar.burpple.network;

import android.content.Context;

/**
 * Created by yekokohtet on 1/18/18.
 */

public interface BurppleDataAgent {
    void loadFeatured(String accessToken, int pageNo, Context context);
    void loadPromotions(String accessToken, int pageNo, Context context);
    void loadGuides(String accessToken, int pageNo, Context context);
}
