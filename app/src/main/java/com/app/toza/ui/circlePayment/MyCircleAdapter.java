package com.app.toza.ui.circlePayment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;
import com.app.toza.ui.HomeActivity;
import com.app.toza.ui.VirtualNewCardActivity;

public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.MyViewHolder> {

    public Context mContext;
    MyCircleAdapter(Context ctx){
        mContext = ctx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_circle_item, parent, false);

        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, MyCircleDetailedActivity.class));
            }
        });
    }



    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TfTextView txtView;
        public MyViewHolder(View view) {
            super(view);
            txtView = (TfTextView)view.findViewById(R.id.txtView);
        }
    }
}
