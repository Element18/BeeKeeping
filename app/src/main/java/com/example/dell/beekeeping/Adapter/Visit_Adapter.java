package com.example.dell.beekeeping.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.beekeeping.DataBase.RecordSql;
import com.example.dell.beekeeping.R;
import com.example.dell.beekeeping.fragment.Apiaries;
import com.example.dell.beekeeping.fragment.Visit;

import java.util.ArrayList;
import java.util.List;

public class Visit_Adapter extends RecyclerView.Adapter<Visit_Adapter.VisitHolder> {
    VisitOnClickListener listener;
    Context context;
    List<RecordSql> list = new ArrayList<>();
    Visit visit;
    private int getAdapterPosition;

    public Visit_Adapter (Context context, List<RecordSql> list, Visit visit) {
        this.context = context;
        this.list = list;
        this.visit = visit;
    }

    public void  setOnClickListener(VisitOnClickListener listener){
        this.listener = listener;

    }

    public class VisitHolder extends RecyclerView.ViewHolder{

        public Visit visit;
        private TextView address;
        private TextView apiary;
        private TextView town;
        private TextView Latitude;
        private TextView Longitude;
        private CheckBox checkBox;
        private CardView cardView;

        public VisitHolder(View itemView,final Visit visit ){
            super(itemView);
            address = itemView.findViewById(R.id.addressVisit);
            apiary = itemView.findViewById(R.id.apiaryVisit);
            town = itemView.findViewById(R.id.townVisit);
            Longitude = itemView.findViewById(R.id.longitudeVisit);
            Latitude = itemView.findViewById(R.id.latitudeVisit);
            checkBox = itemView.findViewById(R.id.checkboxVisit);
            checkBox.setVisibility(View.GONE);
            cardView = itemView.findViewById(R.id.CardviewVisit);
        }


    }

    @NonNull
    @Override
    public VisitHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.visit_custom_layout_view, viewGroup, false);
        final VisitHolder visitHolder = new VisitHolder(itemview, visit);



        itemview.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick (View v) {
                int position = getAdapterPosition;

                listener.OnVisitItemclick(list.get(position));
            }
        });

        return visitHolder;
    }



    @Override
    public void onBindViewHolder (@NonNull VisitHolder visitHolder, int i) {

        RecordSql recordSql = list.get(i);
        visitHolder.address.setText(recordSql.getAddress());
        visitHolder.apiary.setText(recordSql.getApiaryNo());
        visitHolder.Latitude.setText(recordSql.getLatitude());
        visitHolder.Longitude.setText(recordSql.getLongitude());
        visitHolder.town.setText(recordSql.getTown());
    }

    public void setVisitRecord(List<RecordSql> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount () {
        return list.size();
    }

    public  interface VisitOnClickListener {
        void OnVisitItemclick(RecordSql recordSql);
    }

}
