package com.app.toza.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.app.toza.R;
import com.app.toza.helper.Functions;


public class MyEditTextWithCloseBtn extends AppCompatEditText {
    private final Context context;
    private final Drawable imgCloseButton = getResources().getDrawable(R.drawable.ic_cancel);
    private int tintColor;

    public MyEditTextWithCloseBtn(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyEditTextWithCloseBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        if (!isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TfEditTextClose, 0, 0);
            try {
                tintColor = a.getColor(R.styleable.TfEditTextClose_tintColor, ContextCompat.getColor(context, R.color.white));
            } finally {
                a.recycle();
            }
        }
        init();

    }

    public MyEditTextWithCloseBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TfEditTextClose, 0, 0);
            try {
                tintColor = a.getColor(R.styleable.TfEditTextClose_tintColor, ContextCompat.getColor(context, R.color.white));
            } finally {
                a.recycle();
            }
        }
        init();
    }

    void init() {
        setPadding(18, 0, 10, 0);
        imgCloseButton.setBounds(0, 0, imgCloseButton.getIntrinsicWidth(),
                imgCloseButton.getIntrinsicHeight());
        imgCloseButton.setColorFilter(tintColor, android.graphics.PorterDuff.Mode.SRC_IN);
        //setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Dosis-Regular.otf")/*, -1*/);
        handleClearButton();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                MyEditTextWithCloseBtn et = MyEditTextWithCloseBtn.this;

                if (et.getCompoundDrawables()[2] == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (event.getX() > et.getWidth() - et.getPaddingRight()
                        - imgCloseButton.getIntrinsicWidth()) {
                    et.setText("");
                    MyEditTextWithCloseBtn.this.handleClearButton();
                }
                return false;
            }
        });

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                MyEditTextWithCloseBtn.this.handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
        });
        setTypeface(Functions.getRegularFont(context));
    }

    void handleClearButton() {
        if (this.getText().toString().equals("")) {
            this.setCompoundDrawables(this.getCompoundDrawables()[0],
                    this.getCompoundDrawables()[1], null,
                    this.getCompoundDrawables()[3]);
        } else {
            this.setCompoundDrawables(this.getCompoundDrawables()[0],
                    this.getCompoundDrawables()[1], imgCloseButton,
                    this.getCompoundDrawables()[3]);

        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (this.getText().toString().equals("")) {
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
        } else {
            if (focused) {
                this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCloseButton, this.getCompoundDrawables()[3]);
            } else {
                this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
            }

        }

    }
}