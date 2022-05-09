package com.example.yalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkoutInfo extends AppCompatActivity {

    TextView txtName,txtType, txtLevel;
    Button btnBack,btnGo;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        setContentView(R.layout.activity_workout_info);
        Bundle b = getIntent().getExtras();
        String plan = b.getString("plan");
        txtName = findViewById(R.id.name);
        txtName.setText(b.getString("name"));
        txtType = findViewById(R.id.type);
        txtType.setText(b.getString("type"));
        txtLevel = findViewById(R.id.level);
        txtLevel.setText(b.getString("level"));
        btnBack = findViewById(R.id.backToMain2);

        DAOUser dao = new DAOUser();
        String caller = b.getString("caller");
        if (caller.equals("PlansActivty")){
            btnBack.setText("Choose New Plan");
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it;
                if (caller.equals("PlansActivty")) {
                    it = new Intent(getApplicationContext(), LastWorkoutDone.class);
                }else{
                    it = new Intent(getApplicationContext(), UserMenu.class);
                }
                startActivity(it);
            }
        });

        String[] value = firebaseUser.getEmail().split("@");
        String key = value[0];
        btnGo = findViewById(R.id.IwantToDo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> hashMap = new HashMap<>();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(txtName.getText().toString());
                hashMap.put("workoutUsed",arrayList);
                dao.update(key,hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent it = new Intent(getApplicationContext(), DoWorkout.class);
                        Bundle b = new Bundle();
                        b.putString("workoutName", txtName.getText().toString());
                        it.putExtras(b);
                        startActivity(it);
                    }
                });
                Intent it = new Intent(getApplicationContext(), DoWorkout.class);
                Bundle b = new Bundle();
                b.putString("workoutName", txtName.getText().toString());
                b.putString("plan",plan);
                it.putExtras(b);
                startActivity(it);
            }
        });
    }
}