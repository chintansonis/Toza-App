package com.app.toza.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.toza.R;
import com.app.toza.api.BaseResponse;
import com.app.toza.api.CallbackWrapper;
import com.app.toza.api.RestClient;
import com.app.toza.api.responsepojos.ResponseData;
import com.app.toza.custom.MDToast;
import com.app.toza.custom.TfTextView;
import com.app.toza.helper.Functions;
import com.app.toza.helper.PrefUtils;

import java.util.List;

import retrofit2.Call;

public class SplashActivity extends BaseActivity implements View.OnClickListener{
    public LinearLayout liLoginLayout;
    public TfTextView txtRegister,txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();


        if (PrefUtils.isLogin(this)) {
            liLoginLayout.setVisibility(View.GONE);
            new CountDownTimer(1000,1000){

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {

            new CountDownTimer(700,700){

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    liLoginLayout.setVisibility(View.VISIBLE);
                    animateButtons();
                }
            }.start();
        }


    }

    private void animateButtons() {
        Animation slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.push_down_in);
        liLoginLayout.startAnimation(slideUpAnimation);
        //txtRegister.startAnimation(slideUpAnimation);
    }

    private void init() {
        txtLogin = (TfTextView)findViewById(R.id.txtLogin);txtLogin.setOnClickListener(this);
        txtRegister = (TfTextView)findViewById(R.id.txtRegister);txtRegister.setOnClickListener(this);
        liLoginLayout = (LinearLayout)findViewById(R.id.liLoginLayout);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtLogin:
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                break;
            case R.id.txtRegister:
                    Intent intentRegister = new Intent(SplashActivity.this,RegisterActivity.class);
                    startActivity(intentRegister);
                    finish();
                break;
        }
    }
}
