package com.gk.recording.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gk.karaoke.R;
import com.gk.recording.utils.adapter_models.Record;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class RecordsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private RecordsAdapter adapter = null;
    private ListView listView = null;
    private ArrayList<Record> records = new ArrayList<Record>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecordsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecordsFragment newInstance(int columnCount) {
        RecordsFragment fragment = new RecordsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generateDummyRecords();
        logDummyRecords();
        adapter = new RecordsAdapter(this.getContext(),records);
        listView = view.findViewById(R.id.popup_list_view);
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }

    private void showRecordsList(){

    }

    private void generateDummyRecords(){
        records.add(new Record(1,"First",32423423,"Audio"));
        records.add(new Record(2,"Second",4243423,"Audio"));
        records.add(new Record(3,"Third",2444242,"Video"));
        records.add(new Record(4,"Fourth",3242342,"Audio"));
        records.add(new Record(5,"Fifth",2342342,"Video"));
    }

    private void logDummyRecords(){
        int i;
        for(i=0;i<records.size();i++) {
            Log.e("Record","Name "+ records.get(i).getId());
        }
    }
}