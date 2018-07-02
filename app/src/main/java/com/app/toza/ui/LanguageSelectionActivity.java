package com.app.toza.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.toza.R;
import com.app.toza.custom.MDToast;
import com.app.toza.custom.TfTextView;
import com.app.toza.helper.PrefUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class LanguageSelectionActivity extends BaseActivity implements View.OnClickListener{
    CircleImageView imgEnglish,imgFrance;
    TfTextView txtSubmit;
    String lang =  "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        init();
    }

    private void init() {
        imgEnglish = (CircleImageView)findViewById(R.id.imgEnglish);imgEnglish.setOnClickListener(this);
        imgFrance = (CircleImageView)findViewById(R.id.imgFrance);imgFrance.setOnClickListener(this);
        txtSubmit = (TfTextView)findViewById(R.id.txtSubmit);txtSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgEnglish:
                lang = "en";
                imgEnglish.setBorderColor(Color.WHITE);
                    imgEnglish.setBorderWidth(8);
                    imgFrance.setBorderWidth(0);
                break;
            case R.id.imgFrance:
                lang = "fr";
                imgFrance.setBorderColor(Color.WHITE);
                imgFrance.setBorderWidth(8);
                imgEnglish.setBorderWidth(0);
                break;

            case R.id.txtSubmit:

                if(lang.equalsIgnoreCase("")){
                    MDToast.makeText(LanguageSelectionActivity.this,"Please select language first").show();
                }else{
                    PrefUtils.setLanguage(LanguageSelectionActivity.this,lang);

                    if(getIntent().getStringExtra("type").equalsIgnoreCase("1")){
                        Intent intentRegister = new Intent(LanguageSelectionActivity.this, LoginActivity.class);
                        startActivity(intentRegister);
                        finish();
                    }else{
                        Intent intentRegister = new Intent(LanguageSelectionActivity.this, RegisterActivity.class);
                        startActivity(intentRegister);
                        finish();
                    }


                }


                break;
        }
    }
}
