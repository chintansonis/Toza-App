package com.app.toza.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.app.toza.R;
public class HomeActivity extends BaseActivity {
    private ImageView moneyBag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        moneyBag = (ImageView)findViewById(R.id.moneyBag);
        playAnimation();

    }

    private void playAnimation(){
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotate.setDuration(2000);
        rotate.setRepeatCount(Animation.INFINITE);
        moneyBag.startAnimation(rotate);


    }
}
