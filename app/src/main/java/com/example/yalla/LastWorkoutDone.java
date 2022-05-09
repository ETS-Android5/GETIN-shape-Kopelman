package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LastWorkoutDone extends AppCompatActivity {
    private Button btnYes,btnNo;
    private TextView txtWorkout,txtDoAgain;
    String selectedPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_workout_done);
        getSupportActionBar().hide();
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        txtWorkout = findViewById(R.id.txtLastWorkout);
        txtDoAgain  = findViewById(R.id.textView9);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        String [] values = firebaseUser.getEmail().split("@");
        String key = values[0];

        databaseReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(User.class).getWorkoutUsed().get(0).equals("Not yet")) {
                    txtWorkout.setText("You Haven't Done Any Workout YET!");
                    txtDoAgain.setVisibility(View.GONE);
                    btnYes.setVisibility(View.GONE);
                    btnNo.setText("Do Your First One");
                }
                txtWorkout.setText(snapshot.getValue(User.class).getWorkoutUsed().get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DoWorkout.class);
                Bundle b = new Bundle();
                b.putString("workoutName", txtWorkout.getText().toString());
                it.putExtras(b);
                startActivity(it);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),UserMenu.class);
                startActivity(it);
            }
        });

        Spinner plans = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.workoutPlans, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        plans.setAdapter(adapter);


        plans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectedPlan = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnPlan = findViewById(R.id.button10);
        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), PlansActivty.class);
                Bundle b = new Bundle();
                b.putString("plan", selectedPlan);
                it.putExtras(b);
                startActivity(it);
            }
        });
        Button btn = findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),UserMenu.class);
                startActivity(it);
            }
        });

    }
}