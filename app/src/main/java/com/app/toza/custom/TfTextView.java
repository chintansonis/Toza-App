package com.app.toza.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.app.toza.R;
import com.app.toza.helper.Functions;


public class TfTextView extends AppCompatTextView {

    private Context _ctx;
    private boolean isBold;

    public TfTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            this._ctx = context;
            init();
        }
    }

    public TfTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TfTextView, 0, 0);
            try {
                isBold = a.getBoolean(R.styleable.TfTextView_isBold, false);
            } finally {
                a.recycle();
            }

            this._ctx = context;
            init();
        }
    }

    public void setBold(boolean isBold) {
        this.isBold = isBold;
        if (isBold) {
            setTypeface(Functions.getBoldFont(_ctx));
        } else {
            setTypeface(Functions.getRegularFont(_ctx));
        }
    }

    private void init() {
        try {
            // setTypeface(Functions.getLatoFont(_ctx));
            if (isBold) {
                setTypeface(Functions.getBoldFont(_ctx));
            } else {
                setTypeface(Functions.getRegularFont(_ctx));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
