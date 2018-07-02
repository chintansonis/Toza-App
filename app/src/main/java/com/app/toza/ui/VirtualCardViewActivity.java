package com.app.toza.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class VirtualCardViewActivity extends BaseActivity implements View.OnClickListener{
    Toolbar toolbar;
    LinearLayout cardContainer;
    TfTextView txtSubmit,txtAction;
    boolean isFrontShown = true;
    CreditCardView sampleCreditCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_card_view);

        initToolbar();
        init();
    }

    private void init() {
        cardContainer = (LinearLayout) findViewById(R.id.card_container);
        txtAction = (TfTextView)findViewById(R.id.txtAction); txtAction.setOnClickListener(this);
        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit); txtSubmit.setOnClickListener(this);
        populate();
    }
    private void populate() {
        sampleCreditCardView = new CreditCardView(this);

        String name = "Glarence Zhao";
        String cvv = "420";
        String expiry = "01/18";
        String cardNumber = "5635678900000000";

        sampleCreditCardView.setCVV(cvv);
        sampleCreditCardView.setCardHolderName(name);
        sampleCreditCardView.setCardExpiry(expiry);
        sampleCreditCardView.setCardNumber(cardNumber);

        cardContainer.addView(sampleCreditCardView);
        int index = cardContainer.getChildCount() - 1;
       // addCardListener(index, sampleCreditCardView);


    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TfTextView txtTitle = (TfTextView) toolbar.findViewById(R.id.txtTitleAppBar);
        txtTitle.setTextSize(getResources().getDimension(R.dimen.padding_8));
        txtTitle.setText("CARD");
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
            case R.id.txtAction:
                    if(isFrontShown){
                        isFrontShown = false;
                        sampleCreditCardView.showBack();
                        txtAction.setText(R.string.show_front);

                    }else{
                        isFrontShown = true;
                        sampleCreditCardView.showFront();
                        txtAction.setText(R.string.show_back);
                    }
                break;
        }
    }

}
