package com.example.dell.beekeeping.fragment;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.nfc.Tag;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ActionProvider;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView.OnQueryTextListener;
import com.example.dell.beekeeping.Activity.ApiaryActivity;
import com.example.dell.beekeeping.Activity.GalleryActivity;
import com.example.dell.beekeeping.Activity.MainActivity;
import com.example.dell.beekeeping.Activity.Profile;
import com.example.dell.beekeeping.Activity.Record;
import com.example.dell.beekeeping.Activity.Settings;
import com.example.dell.beekeeping.Adapter.Apiary_Adapter;
import com.example.dell.beekeeping.DataBase.RecordSql;
import com.example.dell.beekeeping.Models.Record_Model;
import com.example.dell.beekeeping.R;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.SENSOR_SERVICE;
import static com.example.dell.beekeeping.Activity.MainActivity.edit;
import static com.example.dell.beekeeping.Adapter.Apiary_Adapter.counter;
import static com.example.dell.beekeeping.Adapter.Apiary_Adapter.selectionList;


/**
 */
public class Apiaries extends Fragment implements View.OnLongClickListener {
    public static boolean is_in_contextualMode = false;
    public static Record_Model record_model;
    public static Apiary_Adapter apiary_adapter;
    private RecyclerView recyclerView;
    public static SearchView searchView;
    public static final int ADD_RECORD_REQUEST = 1;
    public static final int EDIT_RECORD_REQUEST = 2;

    MainActivity mainActivity;
    ArrayList<RecordSql> list = new ArrayList<>();

    public static Apiaries newInstance() {
        return  new Apiaries();}
    Menu menu;

    public Apiaries() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_apiaries, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        Apiaries apiaries = new Apiaries();
        apiary_adapter = new Apiary_Adapter(getContext(),list,apiaries);
        recyclerView.setAdapter(apiary_adapter);

        MainActivity.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                showSelectedItem(menuItem);
                return false;
            }
        });
;
        apiary_adapter.setOnClickListener(new Apiary_Adapter.RecordOnClickListener() {
            @Override
            public void OnItemClick(RecordSql recordSql) {
                recordSql = Apiary_Adapter.recordSql;
                Intent intent = new Intent (getContext(), Record.class);
                intent .putExtra(Record.EXTRA_ID,recordSql.getId());
                intent .putExtra(Record.EXTRA_ADDRESS,recordSql.getAddress());
                intent .putExtra(Record.EXTRA_APIARYNAME,recordSql.getApiaryNo());
                intent .putExtra(Record.EXTRA_TOWN,recordSql.getTown());
                intent .putExtra(Record.EXTRA_LATITUDE,recordSql.getLatitude());
                intent .putExtra(Record.EXTRA_LONGITUDE,recordSql.getLongitude());
                startActivityForResult(intent,EDIT_RECORD_REQUEST);
                Apiaries.is_in_contextualMode = false;
                MainActivity.clearActionMode();

            }
        });
        return view;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        record_model = ViewModelProviders.of(getActivity()).get(Record_Model.class);
        record_model.getAllRecord().observe(this, new Observer<List<RecordSql>>() {
            @Override
            public void onChanged(@Nullable List<RecordSql> recordSqls) {
                apiary_adapter.setRecord(recordSqls);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RECORD_REQUEST && resultCode == RESULT_OK){
            String Address = data.getStringExtra(Record.EXTRA_ADDRESS);
            String ApiaryName = data.getStringExtra(Record.EXTRA_APIARYNAME);
            String Town = data.getStringExtra(Record.EXTRA_TOWN);
            String Longitude = data.getStringExtra(Record.EXTRA_LONGITUDE);
            String Latitude = data.getStringExtra(Record.EXTRA_LATITUDE);
            String Hiveno = data.getStringExtra(Record.EXTRA_HIVENO);
            String NoofCombs = data.getStringExtra(Record.EXTRA_NOOFCOMBS);
            String QuantityofHoney = data.getStringExtra(Record.EXTRA_QUANTITYOFHONEY);
            String NoofPropolis = data.getStringExtra(Record.EXTRA_NOOFPROPOLIS);
            String DateOfHarvest = data.getStringExtra(Record.EXTRA_DATEOFHARVEST);
            String DateOfColonisation = data.getStringExtra(Record.EXTRA_DATEOFCOLONISATION);
            String WaxHarvested = data.getStringExtra(Record.EXTRA_WAXHARVESTED);
            String Date = data.getStringExtra(Record.EXTRA_DATE);

            RecordSql record = new RecordSql(Address,ApiaryName,Town,Longitude,Latitude,DateOfHarvest,DateOfColonisation,WaxHarvested,QuantityofHoney,NoofPropolis,NoofCombs,Hiveno );
            record_model.insert(record);
//            startActivityForResult(data,ADD_RECORD_REQUEST);
            Toast.makeText(getContext(), "Record Saved", Toast.LENGTH_LONG).show();

        }else if (requestCode == EDIT_RECORD_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(Record.EXTRA_ID,-1);

            if (id == -1){
                Toast.makeText(mainActivity, "Record cannot be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String Address = data.getStringExtra(Record.EXTRA_ADDRESS);
            String ApiaryName = data.getStringExtra(Record.EXTRA_APIARYNAME);
            String Town = data.getStringExtra(Record.EXTRA_TOWN);
            String Longitude = data.getStringExtra(Record.EXTRA_LONGITUDE);
            String Latitude = data.getStringExtra(Record.EXTRA_LATITUDE);
            String Hiveno = data.getStringExtra(Record.EXTRA_HIVENO);
            String NoofCombs = data.getStringExtra(Record.EXTRA_NOOFCOMBS);
            String QuantityofHoney = data.getStringExtra(Record.EXTRA_QUANTITYOFHONEY);
            String NoofPropolis = data.getStringExtra(Record.EXTRA_NOOFPROPOLIS);
            String DateOfHarvest = data.getStringExtra(Record.EXTRA_DATEOFHARVEST);
            String DateOfColonisation = data.getStringExtra(Record.EXTRA_DATEOFCOLONISATION);
            String WaxHarvested = data.getStringExtra(Record.EXTRA_WAXHARVESTED);
            String Date = data.getStringExtra(Record.EXTRA_DATE);

            RecordSql recordSql = new RecordSql(Address,ApiaryName,Town,Longitude,Latitude,DateOfHarvest,DateOfColonisation,WaxHarvested,QuantityofHoney,NoofPropolis,NoofCombs,Hiveno);
            recordSql.setId(id);
            record_model.update(recordSql);
            Toast.makeText(getContext(), "Record updated", Toast.LENGTH_SHORT).show();
        }

        else {

            Toast.makeText(getContext(), "Record not Saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {

        MainActivity.toolbar.getMenu().clear();
        MainActivity.toolbar.inflateMenu(R.menu.menu_record);
        MainActivity.textView1.setVisibility(View.GONE);
        MainActivity.textView.setVisibility(View.VISIBLE);
        MainActivity.edit.setVisibility(View.VISIBLE);
        is_in_contextualMode = true;
        apiary_adapter.notifyDataSetChanged();
        return true;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
     if  (is_in_contextualMode = true){
         List<RecordSql> list = new ArrayList<>();
         inflater.inflate(R.menu.menu_record,menu);
         this.menu = menu;


     }else {
         inflater.inflate(R.menu.menu_main,menu);
         MenuItem searchItem = menu.findItem(R.id.search);
         SearchView searchView = (SearchView)
                 searchItem.getActionView();

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit (String s) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange (String s) {
                 String userText = s.toLowerCase();
                 List<RecordSql>newList  = new ArrayList<>();

                 for (RecordSql name : list){
                     if (name.getAddress().toLowerCase().contains(userText)){
                         newList.add(name);
                     }
                 }
                 apiary_adapter.setListFilter(newList);
                 return true;
             }
         });
     }
        super.onCreateOptionsMenu(menu, inflater);
        }


    public void showSelectedItem(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.Feedback){
            Toast.makeText(mainActivity, "clicked", Toast.LENGTH_SHORT).show();

        }else if (id ==R.id.AddToApiary){
            Intent intent = new Intent(getContext(),Record.class);
            startActivityForResult(intent,ADD_RECORD_REQUEST);

        }else if(id == R.id. profile){
            startActivity(new Intent(getContext(), Profile.class));

        }else if(id == R.id.deleteAll){
            showDialogueBoxForDeleteAll();


        }else if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(), Settings.class));

        }else if (id == R.id.Add_to_Gallery){
            startActivity(new Intent(getContext(), GalleryActivity.class));
        }
    }

    public  void showDialogueBoxForDeleteAll(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.delete_alert_layout,null);
        builder.setView(view);
        TextView textView = view.findViewById(R.id.confirmation);
        TextView textView1 = view.findViewById(R.id.question);
        Button btnOk = view.findViewById(R.id.ok);
        Button btnCancel = view.findViewById(R.id.cancel);
        final AlertDialog alertDialog = builder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record_model.deleteAllRecord();
                Toast.makeText(getContext(), "All Record deleted ", Toast.LENGTH_SHORT).show();
              alertDialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alertDialog.dismiss();

            }
        });

        alertDialog.show();
    }
}
