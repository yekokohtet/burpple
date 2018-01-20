package com.padcmyanmar.burpple.network;

import com.padcmyanmar.burpple.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yekokohtet on 1/19/18.
 */

public abstract class BurppleCallback<T extends BurppleResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        BurppleResponse burppleResponse = response.body();
        if (burppleResponse == null) {
            RestApiEvents.ErrorInvokingAPIEvent errorEvent =
                    new RestApiEvents.ErrorInvokingAPIEvent("No data could not load for now.");
            EventBus.getDefault().post(errorEvent);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestApiEvents.ErrorInvokingAPIEvent errorEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
        EventBus.getDefault().post(errorEvent);
    }
}
