package com.example.yalla;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.HashMap;

public class UserRunningFragment extends Fragment {


    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;
    TextView distanceTxt;
    Button btnShow;
    double x,y;
    private TextView distanceTxt1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_running, container, false);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        String [] values = firebaseUser.getEmail().split("@");
        String key = values[0];

        com.jjoe64.graphview.GraphView graphView = (GraphView) view.findViewById(R.id.graphV1);
        distanceTxt = view.findViewById(R.id.editTextNumber);
        btnShow = view.findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphView.setVisibility(View.VISIBLE);
                String distance = distanceTxt.getText().toString();
                for (int i=0; i<100; i++) {
                    x = Integer.parseInt(distance);
                    y = 2*x+1;
                    series = new LineGraphSeries<DataPoint>();
                    series.appendData(new DataPoint(x, y), true, 100);
                    graphView.addSeries(series);
                }
                distanceTxt.setVisibility(View.GONE);
            }
        });



        return view;
    }
}