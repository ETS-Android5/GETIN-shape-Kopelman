package com.example.yalla;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class workoutsFragment extends Fragment {

    private  ListView listView;
    private String[] workouts = {"ABS","BACK","BICEPS","CALF","CHEST","FOREARMS","LEGS","SHOULDERS","TRICEPS"};
    private int[] images = {R.drawable.covertrain,
            R.drawable.twitter};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_workouts, container, false);
        listView =(ListView) view.findViewById(R.id.list_item);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, workouts);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Toast.makeText(getActivity(), "ABS FOR YOU", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
