package com.example.yalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutInfo extends AppCompatActivity {

    TextView txtName,txtType, txtLevel;
    Button btnBack,btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_info);
        Bundle b = getIntent().getExtras();
        txtName = findViewById(R.id.name);
        txtName.setText(b.getString("name"));
        txtType = findViewById(R.id.type);
        txtType.setText(b.getString("type"));
        txtLevel = findViewById(R.id.level);
        txtLevel.setText(b.getString("level"));
        btnBack = findViewById(R.id.backToMain2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(it);
            }
        });
        btnGo = findViewById(R.id.IwantToDo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),DoWorkout.class);
                startActivity(it);
            }
        });
    }
}