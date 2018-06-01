package com.app.toza.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.toza.R;
import com.app.toza.ui.BaseActivity;


/**
 * Created by chintans on 15-02-2018.
 */

public class BaseFragment extends Fragment {
    private ProgressDialog dialog;
    /*protected UserDetails userDetails;*/
    protected View view;
    /**
     * The Activity.
     */
    private BaseActivity baseActivity;
    /**
     * Gets base activity.
     *
     * @return the base activity
     */
    public BaseActivity getBaseActivity()
    {
        return baseActivity;
    }
    /**
     * Sets base activity.
     *
     * @param baseActivity the base activity
     */
    public void setBaseActivity(BaseActivity baseActivity)
    {
        this.baseActivity = baseActivity;
    }
    /**
     * On fragment back press boolean.
     *
     * @return the boolean
     */
    public boolean onFragmentBackPress()
    {
        return false;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    public void showProgressDialog(boolean isCancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(baseActivity);
        dialog.setMessage(baseActivity.getString(R.string.msg_please_wait));
        dialog.setCancelable(isCancelable);
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
