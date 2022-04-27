package com.example.yalla;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DAOUser {
    private DatabaseReference databaseReference;
    public DAOUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(User.class.getSimpleName());
    }
    public Task<Void> add(User user){
        if (user == null)
            return null;
        return databaseReference.push().setValue(user);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

}
