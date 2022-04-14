package com.example.yalla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class FoodFragment extends Fragment {


    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_food, container, false);
        double x,y;
        x = -5.0;
        GraphView graphView = (GraphView) view.findViewById(R.id.graphV1);
        double x1,y1;
        x1 = 0;
        series = new LineGraphSeries<DataPoint>();
        series1 = new LineGraphSeries<DataPoint>();
        for (int i=0; i< 500; i++){
            x +=1;
            y=  5*x+1;
            x1+=2;
            y1 = 6*x+2;
            series.appendData(new DataPoint(x,y),true,500);
            series1.appendData(new DataPoint(x1,y1),true,500);
        }
        graphView.addSeries(series);
        graphView.addSeries(series1);



        return view;

    }
}