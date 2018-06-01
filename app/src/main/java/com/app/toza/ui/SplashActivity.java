package com.app.toza.ui;

import android.os.Bundle;

import com.app.toza.R;
import com.app.toza.api.BaseResponse;
import com.app.toza.api.CallbackWrapper;
import com.app.toza.api.RestClient;
import com.app.toza.api.responsepojos.ResponseData;
import com.app.toza.custom.MDToast;
import com.app.toza.helper.Functions;

import java.util.List;

import retrofit2.Call;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Functions.showToast(SplashActivity.this, "Hello user", 1);
        if (Functions.isConnected(SplashActivity.this)) {
            callStateListApi();
        } else {
            Functions.showToast(SplashActivity.this, "Please check internet connection", MDToast.TYPE_ERROR);
        }

    }

    private void callStateListApi() {
        showProgressDialog(false);
        Call<BaseResponse<List<ResponseData>>> call = RestClient.get().getAllStateList();
        call.enqueue(new CallbackWrapper<BaseResponse<List<ResponseData>>>() {
            @Override
            public void onSuccess(BaseResponse<List<ResponseData>> response) {
                hideProgressDialog();
                Functions.showToast(SplashActivity.this, "Data Found", MDToast.TYPE_SUCCESS);
            }

            @Override
            public void onError(BaseResponse<List<ResponseData>> response, String error) {
                hideProgressDialog();
                Functions.showToast(SplashActivity.this, "Something went wrong", MDToast.TYPE_ERROR);
            }
        });
    }
}
