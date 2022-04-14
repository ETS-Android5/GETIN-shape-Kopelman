package com.example.yalla;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphView extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        double x,y;
        x = -5.0;
        com.jjoe64.graphview.GraphView graphView = (com.jjoe64.graphview.GraphView) findViewById(R.id.graphV);
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
    }
}