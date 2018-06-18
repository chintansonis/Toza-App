package com.app.toza.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.toza.R;

import ru.whalemare.sheetmenu.SheetMenu;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    private ImageView moneyBag;
    private LinearLayout liToza,liCircle,liWallet,liVirtualCard,liInternationalTransfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


    }

    private void TozaMoneySheet() {
        SheetMenu.with(this)
                .setTitle("TOZA Money")
                .setMenu(R.menu.toza_menu)
                .setAutoCancel(false)
                .setLayoutManager(new GridLayoutManager(this,2))
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                }).show();

    }

    private void CircleSheet() {
        SheetMenu.with(this)
                .setTitle("Circle Payment")
                .setMenu(R.menu.circle_menu)
                .setAutoCancel(false)
                .setLayoutManager(new GridLayoutManager(this,2))
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                }).show();

    }

    private void VirtualCardSheet() {
        SheetMenu.with(this)
                .setTitle("Virtual Card")
                .setMenu(R.menu.virtual_card_menu)
                .setAutoCancel(false)
                .setLayoutManager(new GridLayoutManager(this,2))
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                }).show();

    }

    private void WalletSheet() {
        SheetMenu.with(this)
                .setTitle("Wallet Management")
                .setMenu(R.menu.wallet_menu)
                .setAutoCancel(false)
                .setLayoutManager(new GridLayoutManager(this,2))
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                }).show();

    }



    private void init() {
        liToza = (LinearLayout)findViewById(R.id.liToza);liToza.setOnClickListener(this);
        liCircle = (LinearLayout)findViewById(R.id.liCircle);liCircle.setOnClickListener(this);
        liWallet = (LinearLayout)findViewById(R.id.liWallet);liWallet.setOnClickListener(this);
        liVirtualCard = (LinearLayout)findViewById(R.id.liVirtualCard);liVirtualCard.setOnClickListener(this);
        liInternationalTransfer = (LinearLayout)findViewById(R.id.liInternationalTransfer);liInternationalTransfer.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.liToza:
                TozaMoneySheet();
                break;
            case R.id.liCircle:
                CircleSheet();
                break;
            case R.id.liWallet:
                WalletSheet();
                break;
            case R.id.liVirtualCard:
                VirtualCardSheet();
                break;
            case R.id.liInternationalTransfer:

                break;
        }
    }
}
