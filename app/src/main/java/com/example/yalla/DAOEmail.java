package com.example.yalla;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOEmail {
    private DatabaseReference databaseReference;
    public DAOEmail(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Email");
    }
    public Task<Void> add(String email){
        String[] pre = email.split("@");
        databaseReference.child("Email").child(pre[0]).push();
        return databaseReference.child("Email").child(pre[0]).setValue(email);
    }
}
