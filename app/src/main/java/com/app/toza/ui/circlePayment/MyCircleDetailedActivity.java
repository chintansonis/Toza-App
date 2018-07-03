package com.app.toza.ui.circlePayment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;
import com.app.toza.ui.BaseActivity;

public class MyCircleDetailedActivity extends BaseActivity implements View.OnClickListener{
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_circle_detailed);

        initToolbar();
        init();
    }

    private void init() {
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TfTextView txtTitle = (TfTextView) toolbar.findViewById(R.id.txtTitleAppBar);
        txtTitle.setTextSize(getResources().getDimension(R.dimen.padding_8));
        txtTitle.setText("MY CIRCLE");
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
