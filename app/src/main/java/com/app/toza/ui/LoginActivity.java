package com.app.toza.ui;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.transition.Slide;
import android.support.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.toza.R;
import com.app.toza.custom.MDToast;
import com.app.toza.custom.TfTextView;
import com.app.toza.helper.Functions;
import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Direction;
import com.fujiyuu75.sequent.Sequent;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    RelativeLayout mainRelative;
    TfTextView txtLogin,txtSignup,txtForgotPassword;
    SimpleArcDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        mainRelative = (RelativeLayout)findViewById(R.id.mainRelative);
        txtLogin = (TfTextView)findViewById(R.id.txtLogin);txtLogin.setOnClickListener(this);
        txtSignup = (TfTextView)findViewById(R.id.txtSignup);txtSignup.setOnClickListener(this);
        txtForgotPassword = (TfTextView)findViewById(R.id.txtForgotPassword);txtForgotPassword.setOnClickListener(this);
        Sequent
                .origin(mainRelative)
                .delay(10)
                .flow(Direction.FORWARD)
                .anim(this, Animation.FADE_IN_UP)
                  .start();

        /*
         SimpleArcDialog mDialog = new SimpleArcDialog(this);
                mDialog.setConfiguration(new ArcConfiguration(this));
                mDialog.setTitle("Loading...");
                mDialog.show();



                 String deviceID = getResources().getString(R.string.device_id)+" "+Functions.getDeviceId(this);
                    Functions.showAlertDialogWithOKOption(this, "OK", deviceID, new Functions.DialogOptionsSelectedListener() {
                        @Override
                        public void onSelect(boolean isYes) {
                            if(isYes) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText(getResources().getString(R.string.device_id), Functions.getDeviceId(LoginActivity.this));
                                clipboard.setPrimaryClip(clip);
                                MDToast.makeText(LoginActivity.this, getResources().getString(R.string.device_id_copy)).show();
                            }
                        }
                    });
         */
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtSignup:
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.txtForgotPassword:
                    MDToast.makeText(LoginActivity.this,"Development In Process",Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtLogin:
                        mDialog = new SimpleArcDialog(this);
                        ArcConfiguration configuration = new ArcConfiguration(this);
                        configuration.setText("Loading");
                        mDialog.setConfiguration(configuration);
                        mDialog.show();
                    new CountDownTimer(1000,1000){

                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            mDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);

                        }
                    }.start();




                break;
        }
    }

}
