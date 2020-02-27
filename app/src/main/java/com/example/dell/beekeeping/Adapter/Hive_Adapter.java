package com.example.dell.beekeeping.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class Hive_Adapter extends RecyclerView.Adapter<Hive_Adapter.Hive_Holder> {

    public class Hive_Holder extends RecyclerView.ViewHolder {
        public Hive_Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public Hive_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Hive_Holder hive_holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
