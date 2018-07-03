package com.app.toza.ui.circlePayment;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;
import com.app.toza.ui.BaseActivity;

public class CreateNewCircleActivity extends BaseActivity implements View.OnClickListener{
    Toolbar toolbar;
    ImageView imgInfo;
    private CardView cardDetail;
    private TfTextView txtSubmit,txtNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_circle);

        initToolbar();
        init();
    }

    private void init() {
        imgInfo = (ImageView)findViewById(R.id.imgInfo);imgInfo.setOnClickListener(this);
        cardDetail = (CardView)findViewById(R.id.cardDetail);
        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit);
        txtNext = (TfTextView)findViewById(R.id.txtNext);txtNext.setOnClickListener(this);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TfTextView txtTitle = (TfTextView) toolbar.findViewById(R.id.txtTitleAppBar);
        txtTitle.setTextSize(getResources().getDimension(R.dimen.padding_8));
        txtTitle.setText("NEW CIRCLE");
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
            case R.id.imgInfo:
                    showAlert();
                break;
            case R.id.txtNext: playAnimation();
                break;
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = builder = new AlertDialog.Builder(this);
        builder.setMessage("Participants need to accept invitation within this period.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      dialog.dismiss();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
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
