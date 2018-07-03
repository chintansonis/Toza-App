package com.app.toza.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.toza.R;
import com.app.toza.ui.circlePayment.CircleActivity;
import com.app.toza.ui.circlePayment.CreateNewCircleActivity;
import com.joaquimley.faboptions.FabOptions;

import ru.whalemare.sheetmenu.SheetMenu;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    private ImageView moneyBag;
    private LinearLayout liToza,liCircle,liWallet,liVirtualCard,liInternationalTransfer;
    private String[] topWalletItems = {"Topup by Card","Request Topup"};
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
                        switch (item.getItemId()){
                            case R.id.sendMoney:
                                startActivity(new Intent(HomeActivity.this,TozaSendMoneyActivity.class));
                                break;
                            case R.id.requestmoney:
                                startActivity(new Intent(HomeActivity.this,TozaRequestMoneyActivity.class));
                                break;
                            case R.id.myrequest:
                                startActivity(new Intent(HomeActivity.this,TozaRequestActivity.class));
                                break;
                        }
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
                        switch (item.getItemId()) {
                            case R.id.my_circle:
                                startActivity(new Intent(HomeActivity.this, CircleActivity.class));
                                break;
                            case R.id.create_new_circle:
                                startActivity(new Intent(HomeActivity.this, CreateNewCircleActivity.class));
                                break;

                        }
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
                        switch (item.getItemId()) {
                            case R.id.mycard:
                                startActivity(new Intent(HomeActivity.this, VirtualCardViewActivity.class));
                                break;
                            case R.id.newcard:
                                startActivity(new Intent(HomeActivity.this, VirtualNewCardActivity.class));
                                break;

                        }
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
                        switch (item.getItemId()) {
                            case R.id.top_wallet:
                                    showPopup();
                                break;
                            case R.id.withdrawal:
                                startActivity(new Intent(HomeActivity.this, WalletWithdrawActivity.class));
                                break;

                        }
                        return false;
                    }
                }).show();

    }

    private void showPopup() {
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(topWalletItems, 0, null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                           if(selectedPosition == 0){
                               startActivity(new Intent(HomeActivity.this, TopByCardActivity.class));
                           }else{
                               startActivity(new Intent(HomeActivity.this, RequestTopUpActivity.class));
                           }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
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
        customizingFabOptions();
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

    private void customizingFabOptions() {
        FabOptions fabOptions = findViewById(R.id.fab_options);
        fabOptions.setButtonsMenu(R.menu.home_fab_menu);
       // fabOptions.setBackgroundColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
       // fabOptions.setFabColor(R.color.colorAccent);
        fabOptions.setOnClickListener(this);
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
                startActivity(new Intent(this,InterNationalTansferActivity.class));
                break;
            case R.id.myProfile:
                    startActivity(new Intent(this,ProfileActivity.class));
                break;

            case R.id.kyc:
                Toast.makeText(this, "KYC", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
        }
    }
}
