package com.app.toza.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;

import com.app.toza.R;
import com.app.toza.custom.TfTextView;

public class MyRequestAdapter extends RecyclerView.Adapter<MyRequestAdapter.MyViewHolder> {

    public Context mContext;
    MyRequestAdapter(Context ctx){
        mContext = ctx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_req_item, parent, false);

        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
    }

    private void showAlert() {
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.my_req_item_alert);


        dialog.show();
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
