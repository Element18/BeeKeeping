package com.example.dell.beekeeping.Adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.beekeeping.Activity.ApiaryActivity;
import com.example.dell.beekeeping.Activity.MainActivity;
import com.example.dell.beekeeping.Activity.Record;
import com.example.dell.beekeeping.DataBase.RecordSql;
import com.example.dell.beekeeping.Models.Record_Model;
import com.example.dell.beekeeping.R;
import com.example.dell.beekeeping.fragment.Apiaries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.dell.beekeeping.Activity.MainActivity.edit;
import static com.example.dell.beekeeping.fragment.Apiaries.EDIT_RECORD_REQUEST;

public class Apiary_Adapter extends RecyclerView.Adapter<Apiary_Adapter.ApiaryHolder> {

    private List<RecordSql> list;
    private List<RecordSql> listsearch;
    public Record_Model record_model = Apiaries.record_model;
    Apiaries apiaries;
    Context  context;
    RecordOnClickListener listener;
    public static ArrayList<RecordSql> selectionList =new ArrayList<>();
    public static int counter = 0;
    public static RecordSql recordSql;

    public static RecordSql getRecordSql() {
        return recordSql;
    }

    public Apiary_Adapter(Context context, List<RecordSql> list, Apiaries apiaries) {
        listsearch = new ArrayList<>(list);
        this.list = list;
        this.apiaries = apiaries;
        this.context = context;
    }

    public  void setOnClickListener(RecordOnClickListener listener){
        this.listener = listener;

    }
    @NonNull
    @Override
    public ApiaryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View itemview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customlayout, viewGroup, false);
        final ApiaryHolder apiaryHolder = new ApiaryHolder(itemview, apiaries);

        return apiaryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ApiaryHolder apiaryHolder, final int i) {
        RecordSql currentRecord = list.get(i);
        apiaryHolder.address.setText(currentRecord.getAddress());
        apiaryHolder.apiary.setText(currentRecord.getApiaryNo());
        apiaryHolder.town.setText(currentRecord.getTown());
        apiaryHolder.Latitude.setText(currentRecord.getLatitude());
        apiaryHolder.Longitude.setText(currentRecord.getLongitude());
        apiaryHolder.hiveNo.setText(currentRecord.getHiveNo());
        apiaryHolder.noOfComb.setText(currentRecord.getNoOfCombs());
        apiaryHolder.dateOfHarvest.setText(currentRecord.getDateOfHarvest());
        apiaryHolder.dateOfColonisation.setText(currentRecord.getDateOfColonisation());
        apiaryHolder.waxHarvested.setText(currentRecord.getNoOfWaxHarvested());
        apiaryHolder.quantityOfhoney.setText(currentRecord.getQuantityOfHoneyHarvested());
        apiaryHolder.noOfPropolis.setText(currentRecord.getNoOfPropolis());



        if (apiaries.is_in_contextualMode) {
            apiaryHolder.checkBox.setVisibility(View.VISIBLE);
            apiaryHolder.cardView.setOnClickListener(null);

        } else if (!apiaries.is_in_contextualMode) {
            apiaryHolder.checkBox.setVisibility(View.GONE);
            apiaryHolder.checkBox.setChecked(false);

        }
    }

    public void setRecord(List<RecordSql> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount () {
        return list.size();
    }

//
//    @Override
//    public Filter getFilter () {
//        return listFilter;
//    }

//    private Filter listFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering (CharSequence constraint) {
//            List<RecordSql> filteredList = new ArrayList<>();
//
//            if (constraint == null ||constraint.length() == 0){
//                filteredList.addAll(listsearch);
//            }else{
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (RecordSql record : listsearch){
//                    if(record.getAddress().toLowerCase().contains(filterPattern)){
//                        filteredList.add(record);
//                    }
//                }
//            }
//            FilterResults results =new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults (CharSequence constraint, FilterResults results) {
//
//            list.clear();
//            list.addAll((List)results.values);
//            notifyDataSetChanged();
//        }
//    };

    class ApiaryHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView apiary;
        public  TextView town;
        public  TextView Latitude;
        public  TextView Longitude;
        public  CheckBox checkBox;
        Apiaries apiaries;
        private CardView cardView;
        public  TextView date;
        private TextView hiveNo;
        private TextView noOfComb;
        private TextView dateOfHarvest;
        private TextView dateOfColonisation;
        private TextView waxHarvested;
        private TextView quantityOfhoney;
        private TextView noOfPropolis;


        public ApiaryHolder(@NonNull final View itemView, final Apiaries Apiaries) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            apiary = itemView.findViewById(R.id.apiary);
            town = itemView.findViewById(R.id.town);
            Longitude = itemView.findViewById(R.id.longitude);
            Latitude = itemView.findViewById(R.id.latitude);
            checkBox = itemView.findViewById(R.id.checkbox);
            hiveNo = itemView.findViewById(R.id.hiveNo);
            noOfComb = itemView.findViewById(R.id.combNo);
            dateOfHarvest = itemView.findViewById(R.id.dateOfHarvest);
            dateOfColonisation = itemView.findViewById(R.id.dateOfColonisation);
            waxHarvested = itemView.findViewById(R.id.noOfWaxHarveted);
            quantityOfhoney = itemView.findViewById(R.id.quantityOfHoney);
            noOfPropolis = itemView.findViewById(R.id.noOfPropolis);
            date = itemView.findViewById(R.id.time);
            checkBox.setVisibility(View.GONE);
            this.apiaries = Apiaries;
            cardView = itemView.findViewById(R.id.Cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context , ApiaryActivity.class);
                    intent.putExtra("town",list.get(getAdapterPosition()).getTown());
                    intent.putExtra("address", list.get(getAdapterPosition()).getAddress());
                    intent.putExtra("apiaryno",list.get(getAdapterPosition()).getApiaryNo());
                    intent.putExtra("longitude",list.get(getAdapterPosition()).getLongitude());
                    intent.putExtra("latitude",list.get(getAdapterPosition()).getLatitude());
                    intent.putExtra("hiveNo",list.get(getAdapterPosition()).getHiveNo());
                    intent.putExtra("noOfCombs",list.get(getAdapterPosition()).getNoOfCombs());
                    intent.putExtra("dateOfHarvest",list.get(getAdapterPosition()).getDateOfHarvest());
                    intent.putExtra("dateOfColonisation",list.get(getAdapterPosition()).getDateOfColonisation());
                    intent.putExtra("noOfWaxHarvested",list.get(getAdapterPosition()).getNoOfWaxHarvested());
                    intent.putExtra("quantityOfHoney",list.get(getAdapterPosition()).getQuantityOfHoneyHarvested());
                    intent.putExtra("noOfPropolis",list.get(getAdapterPosition()).getNoOfPropolis());
                    context.startActivity(intent);
                }
            });

            cardView.setOnLongClickListener(apiaries);


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    if (Apiary_Adapter.counter == 0){
                        Toast.makeText(context, "please select item to Edit", Toast.LENGTH_SHORT).show();
                    }else {
                        int Position = getAdapterPosition();
                        if (listener != null && Position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(list.get(getAdapterPosition()));
                    }
                    }
                }
            });



            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Do_Selection(v,getAdapterPosition(),list);
                }
            });

        }


        public  void  Do_Selection(View view, int position,List<RecordSql> list){

            if (((CheckBox)view).isChecked()){
                selectionList.add(list.get(position));
                counter = counter+1;
                upDateCOunter(counter);
            }else {
                selectionList.remove(list.get(position));
                counter = counter-1;
                upDateCOunter(counter);
            }
        }

        public   void upDateCOunter(int counter){


            if (counter == 0 ) {

                MainActivity.textView.setText("0 item selected");
                MainActivity.edit.setVisibility(View.INVISIBLE);

            }else  if (counter > 1 ){
                MainActivity.textView.setText(counter + " items selected");
                MainActivity.edit.setVisibility(View.INVISIBLE);

            }else if (counter ==  1){
                MainActivity.textView.setText(counter + " items selected");
                edit.setVisibility(View.VISIBLE);

            }else{
                MainActivity.textView.setText(counter + " items selected");
            }
        }

    }



    public  interface  RecordOnClickListener{
        void OnItemClick(RecordSql recordSql);

    }


    public void  deleteRecord(ArrayList<RecordSql> list){

        for (RecordSql recordSql : list){
//            getRecordAt(position);
            record_model.delete(recordSql);
        }
        notifyDataSetChanged();
    }

    public void setListFilter (List<RecordSql> newList){
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void setTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String time = format.format(calendar.getTime());
//        .setText(time);
    }
}
