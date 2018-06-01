package com.app.toza.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.app.toza.R;
import com.app.toza.custom.MDToast;
import com.app.toza.custom.TfTextView;
import com.app.toza.fragments.BaseFragment;
import com.app.toza.helper.AppConstants;
import com.app.toza.helper.Functions;

import java.util.Stack;


/**
 * Created by chintans on 15-02-2018.
 */

public class BaseActivity extends AppCompatActivity {
    public int screenWidth, screenHeight;
    private Stack<Fragment> fragmentBackStack;
    private boolean showBackMessage = true;
    private boolean doubleBackToExitPressedOnce;
    private ProgressDialog dialog;
    public static int CALL_PERMISSION_REQUEST_CODE = 1225;
    public static int Location_PERMISSION_REQUEST_CODE = 1210;


    public void setShowBackMessage(boolean showBackMessage) {
        this.showBackMessage = showBackMessage;
    }


    public Stack<Fragment> getFragments() {
        return fragmentBackStack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentBackStack = new Stack<>();
        getWidthAndHeight();
    }

    public void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    /**
     * To add fragment to a tab. tag -> Tab identifier fragment -> Fragment to show, in tab identified by tag shouldAnimate ->
     * should animate transaction. false when we switch tabs, or adding first fragment to a tab true when when we are pushing more
     * fragment into navigation stack. shouldAdd -> Should add to fragment navigation stack (mStacks.get(tag)). false when we are
     * switching tabs (except for the first time) true in all other cases.
     *
     * @param fragment      the fragment
     * @param shouldAnimate the should animate
     * @param shouldAdd     the should add
     */
    public synchronized void pushAddFragments(Fragment fragment, boolean shouldAnimate, boolean shouldAdd) {
        try {
            if (fragment != null) {
                fragmentBackStack.push(fragment);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                //Fragment currentFragmentForAdd = fragmentBackStack.get(fragmentBackStack.size() - 1);
                ft.replace(R.id.contentFrame, fragment, String.valueOf(fragmentBackStack.size()));
                ft.commit();
                manager.executePendingTransactions();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select the second last fragment in current tab's stack.. which will be shown after the fragment transaction given below
     *
     * @param shouldAnimate the should animate
     */
    public synchronized void popFragments(boolean shouldAnimate) {
        Fragment fragment = null;
        if (fragmentBackStack.size() > 1) {
            fragment = fragmentBackStack.elementAt(fragmentBackStack.size() - 2);
        } else if (!fragmentBackStack.isEmpty()) {
            fragment = fragmentBackStack.elementAt(fragmentBackStack.size() - 1);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (fragment != null) {
            if (fragment.isAdded()) {
                ft.remove(fragmentBackStack.elementAt(fragmentBackStack.size() - 1));
                if (fragmentBackStack.size() > 1) {
                    ft.show(fragment).commit();
                }
            } else {
                ft.replace(R.id.contentFrame, fragment).commit();
            }
            fragmentBackStack.pop();
            manager.executePendingTransactions();
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentBackStack.size() <= 1) {
            if (showBackMessage) {
                doubleTapOnBackPress();
            } else {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } else {
            if (!((BaseFragment) fragmentBackStack.get(fragmentBackStack.size() - 1)).onFragmentBackPress()) {
                Fragment currentFragment = fragmentBackStack.get(fragmentBackStack.size() - 1);
                /*if (currentFragment instanceof SearchFragment) {
                    loadHomeFragment();
                } else if (currentFragment instanceof AccountFragment) {
                    loadHomeFragment();
                } else if (currentFragment instanceof CartFragment) {
                    loadHomeFragment();
                }else if (currentFragment instanceof HomeFragment) {
                    doubleTapOnBackPress();
                }else{
                    popFragments(true);
                }*/
            }
        }
    }

    private void loadHomeFragment() {
        /*setHeaderTitle(PrefUtils.getPreferredCity(BaseActivity.this));
        getFragments().clear();
        Fragment fragmentToPush = HomeFragment.getFragment(this);
        pushAddFragments(fragmentToPush, true, true);
        loadBottomUI(AppConstants.FOOTER_HOME);
        isDownArrow(true);*/
    }

    /**
     * method handle for show notification for close the application & Stop app to Close when there is no any remaining Fragment
     * in Stack and User press Back Press
     */
    void doubleTapOnBackPress() {
        if (doubleBackToExitPressedOnce) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        //Functions.showToast(this, context.getString(R.string.click_back_to_exit));
        Functions.showToast(this, getString(R.string.click_back_to_exit), MDToast.TYPE_INFO);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 10000);
    }

    public void showProgressDialog(boolean isCancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.msg_please_wait));
        dialog.setCancelable(isCancelable);
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * this method load dashboard fragment
     */
    /*public void loadHomeFragment() {
        ((TfTextView) findViewById(R.id.txtCurrentLocation)).setText(Functions.getLocationOfaddressHeader(context, Preferences.getInstance(context).getString(Preferences.KEY_LATITUDE),Preferences.getInstance(context).getString(Preferences.KEY_LONGITUDE)));
        isVisibleLocation(true);
        isVisibleChangePassword(false);
        isVisibleSearch(true);
        isVisibleHeaderImage(false);
        setHeaderFRomBaseActivity(getString(R.string.app_name));
        getFragments().clear();
        Fragment fragmentToPush = DashBoardFragment.getFragment(this);
        pushAddFragments(fragmentToPush, true, true);
    }*/
    /*public void loadBottomUI(int selectedPosition) {
        switch (selectedPosition) {
            case 1:
                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setImageResource(R.drawable.ic_home_selected);
                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setImageResource(R.drawable.ic_search);
                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setImageResource(R.drawable.ic_cart);
                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setImageResource(R.drawable.ic_user);

//                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
//                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setColorFilter(ContextCompat.getColor(context, R.color.selectedFooter), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);

                ((LinearLayout) findViewById(R.id.llFooterHome)).setEnabled(false);
                ((LinearLayout) findViewById(R.id.llFooterSearch)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterCart)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterUser)).setEnabled(true);
                break;
            case 2:
                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setImageResource(R.drawable.ic_home);
                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setImageResource(R.drawable.ic_search_selected);
                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setImageResource(R.drawable.ic_cart);
                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setImageResource(R.drawable.ic_user);

//                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setColorFilter(ContextCompat.getColor(context, R.color.selectedFooter), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);

                ((LinearLayout) findViewById(R.id.llFooterHome)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterSearch)).setEnabled(false);
                ((LinearLayout) findViewById(R.id.llFooterCart)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterUser)).setEnabled(true);
                break;
            case 3:
                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setImageResource(R.drawable.ic_home);
                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setImageResource(R.drawable.ic_search);
                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setImageResource(R.drawable.ic_cart_selected);
                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setImageResource(R.drawable.ic_user);


//                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setColorFilter(ContextCompat.getColor(context, R.color.selectedFooter), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);

                ((LinearLayout) findViewById(R.id.llFooterHome)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterSearch)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterCart)).setEnabled(false);
                ((LinearLayout) findViewById(R.id.llFooterUser)).setEnabled(true);
                break;
            case 4:
                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setImageResource(R.drawable.ic_home);
                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setImageResource(R.drawable.ic_search);
                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setImageResource(R.drawable.ic_cart);
                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setImageResource(R.drawable.ic_user_selected);


//                ((AppCompatImageView) findViewById(R.id.ivFooterHome)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterSearch)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterMenu)).setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
//                ((AppCompatImageView) findViewById(R.id.ivFooterUser)).setColorFilter(ContextCompat.getColor(context, R.color.selectedFooter), android.graphics.PorterDuff.Mode.SRC_IN);

                ((LinearLayout) findViewById(R.id.llFooterHome)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterSearch)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterCart)).setEnabled(true);
                ((LinearLayout) findViewById(R.id.llFooterUser)).setEnabled(false);
                break;

        }
    }*/

    public void setHeaderTitle(String title) {
        ((TfTextView) findViewById(R.id.txtTitleAppBar)).setText(title);
    }





    /**
     * Common method to make focus on keyboard
     *
     * @param editText focused edit text.
     */
    public void requestFocus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Common method to hide keyboard
     *
     * @param editText focused edit text.
     */
    public void hideKeyBoard(EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!getFragments().empty()) {
            Fragment fragment = getFragments().get(getFragments().size() - 1);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
