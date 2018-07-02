package com.app.toza.ui;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;

public class InterNationalTansferActivity extends BaseActivity implements View.OnClickListener{

    Toolbar toolbar;
    private CardView cardDetail;
    private TfTextView txtSubmit,txtNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_tansfer);
        initToolbar();
        init();
    }

    private void init() {
        cardDetail = (CardView)findViewById(R.id.cardDetail);
        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit);
        txtNext = (TfTextView)findViewById(R.id.txtNext);txtNext.setOnClickListener(this);
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TfTextView txtTitle = (TfTextView) toolbar.findViewById(R.id.txtTitleAppBar);
        txtTitle.setTextSize(getResources().getDimension(R.dimen.padding_8));
        txtTitle.setText("INTERNATIONAL TRANSFER");
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
            case R.id.txtNext: playAnimation();
                break;
        }
    }

    private void playAnimation(){
        cardDetail.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(cardDetail, "translationX", 0, 50, 0);
        animator.setInterpolator(new BounceInterpolator());
        animator.setStartDelay(100);
        animator.setDuration(1000);
        animator.start();

        txtSubmit.setVisibility(View.VISIBLE);

    }
}
