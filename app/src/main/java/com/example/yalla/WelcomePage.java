package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomePage extends AppCompatActivity {

    private TextView textWelcome;
    private String value;
   // DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //DAOTweet daoTweet = new DAOTweet();
        // btn = findViewById(R.id.btnTweet);
       // textTweet = findViewById(R.id.editContent);


       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tweet tweet = new Tweet("Me",textTweet.getText().toString());
                daoTweet.add(tweet).addOnSuccessListener(suc -> {
                    Toast.makeText(WelcomePage.this,"did it",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(WelcomePage.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });

                textWelcome.setText(tweet.getContent());
            }
        });*/

        btn = findViewById(R.id.btnStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });

    }
}