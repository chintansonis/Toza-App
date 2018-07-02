package com.app.toza.ui;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;

public class TozaRequestMoneyActivity extends BaseActivity implements View.OnClickListener{
    Toolbar toolbar;
    private TfTextView txtSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toza_requestmoney);
        initToolbar();
        init();
    }

    private void init() {

        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit);
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TfTextView txtTitle = (TfTextView) toolbar.findViewById(R.id.txtTitleAppBar);
        txtTitle.setTextSize(getResources().getDimension(R.dimen.padding_8));
        txtTitle.setText("REQUEST MONEY");
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }


}
