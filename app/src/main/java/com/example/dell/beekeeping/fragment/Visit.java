package com.example.dell.beekeeping.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.beekeeping.Activity.MainActivity;
import com.example.dell.beekeeping.Activity.VisitReport;
import com.example.dell.beekeeping.Adapter.Apiary_Adapter;
import com.example.dell.beekeeping.Adapter.Visit_Adapter;
import com.example.dell.beekeeping.DataBase.RecordSql;
import com.example.dell.beekeeping.Models.Record_Model;
import com.example.dell.beekeeping.R;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Visit extends Fragment {

    public static Record_Model record_model;
    public static Visit_Adapter visit_adapter;
    private RecyclerView recyclerView;
    ArrayList<RecordSql>list = new ArrayList<>();


    public Visit() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_visit, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewVisit);
        Visit visit = new Visit();
        visit_adapter = new Visit_Adapter(getContext(),list,visit);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(visit_adapter);
        recyclerView.setHasFixedSize(true);



        visit_adapter.setOnClickListener(new Visit_Adapter.VisitOnClickListener() {
            @Override
            public void OnVisitItemclick (RecordSql recordSql) {
                startActivity(new Intent(getContext(), VisitReport.class ));

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        record_model = ViewModelProviders.of(getActivity()).get(Record_Model.class);
        record_model.getAllRecord().observe(this, new Observer<List<RecordSql>>() {
            @Override
            public void onChanged (@Nullable List<RecordSql> list) {
                visit_adapter.setVisitRecord(list);
            }
        });
    }
}

