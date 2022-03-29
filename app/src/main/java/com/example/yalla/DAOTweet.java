package com.example.yalla;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOTweet
{
    private DatabaseReference databaseReference;
    public DAOTweet(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Tweet.class.getSimpleName());
    }
    public Task<Void> add(Tweet tweet){
        if (tweet == null)
            return null;
        return databaseReference.push().setValue(tweet);
    }
}
