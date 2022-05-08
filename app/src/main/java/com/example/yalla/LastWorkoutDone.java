package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView txtWorkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_workout_done);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        txtWorkout = findViewById(R.id.txtLastWorkout);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        String [] values = firebaseUser.getEmail().split("@");
        String key = values[0];

        databaseReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
    }
}