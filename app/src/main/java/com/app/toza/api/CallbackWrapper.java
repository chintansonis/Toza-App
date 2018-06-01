package com.app.toza.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chintan soni.
 */
public abstract class CallbackWrapper<T extends BaseResponse> implements Callback<T> {
    public abstract void onSuccess(T response);

    public abstract void onError(T response, String error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatus().equalsIgnoreCase("1")) {
                onSuccess(response.body());
            } else {
                onError(response.body(), response.body().getMessage());
            }
        } else {
            onError(response.body(), response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(null, t.getMessage());
    }
}
