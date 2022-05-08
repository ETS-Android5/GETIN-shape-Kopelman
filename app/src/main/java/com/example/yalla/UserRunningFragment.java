package com.example.yalla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class UserRunningFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_running, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView5);
        Switch switch1 = (Switch) view.findViewById(R.id.switch1);
        String value = textView.getText().toString();
        String value1 = switch1.getText().toString();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch1.setText(value1 + " YES");
                    textView.setText("Good to know!");
                }
                else {
                    switch1.setText(value1 + " NO");
                    textView.setText(value);
                }
            }
        });
        return view;
    }
}