package com.app.toza.ui;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.toza.R;
import com.app.toza.custom.MDToast;
import com.app.toza.custom.TfTextView;
import com.app.toza.helper.Functions;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    public RelativeLayout bottomSheetLayout;
    BottomSheetBehavior sheetBehavior;
    ImageView topImage;
    TfTextView txtLogin,txtForgotPassword,txtClose,txtSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void init() {
        topImage = (ImageView) findViewById(R.id.topImage);topImage.setOnClickListener(this);
        txtForgotPassword = (TfTextView)findViewById(R.id.txtForgotPassword);txtForgotPassword.setOnClickListener(this);
        txtLogin = (TfTextView)findViewById(R.id.txtLogin);txtLogin.setOnClickListener(this);
        txtClose = (TfTextView)findViewById(R.id.txtClose);txtClose.setOnClickListener(this);
        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit);txtSubmit.setOnClickListener(this);
        bottomSheetLayout = (RelativeLayout)findViewById(R.id.linear_layout_bottom_sheet);

        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        //btnBottomSheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                         sheetBehavior.setPeekHeight(0);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtLogin:
                SimpleArcDialog mDialog = new SimpleArcDialog(this);
                mDialog.setConfiguration(new ArcConfiguration(this));
                mDialog.setTitle("Loading...");
                mDialog.show();
                break;
            case R.id.txtForgotPassword:
                    toggleBottomSheet();
                break;
            case R.id.txtClose:
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txtSubmit:
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.topImage:
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
                break;
        }
    }

    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            //btnBottomSheet.setText("Expand sheet");
        }
    }
}
