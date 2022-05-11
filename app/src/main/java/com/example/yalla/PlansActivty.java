package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlansActivty extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String level;
    String headline1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_activty);
        getSupportActionBar().hide();
        Spinner levels = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        levels.setAdapter(adapter);
        listView = findViewById(R.id.list_itemPlan);
        Bundle b = getIntent().getExtras();
        String plan = b.getString("plan");
        Toast.makeText(this,plan, Toast.LENGTH_SHORT).show();
        TextView headline = findViewById(R.id.textView8);
        String[] types = plan.split(",");
        for (String type : types){
            headline1 += type + " ";
        }
        headline.setText(headline1 + "Workout");
        List<String> listTypes = Arrays.asList(types);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Workout");

        levels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayList.clear();
                level = adapter.getItem(position).toString();
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (listTypes.contains(snapshot.getValue(Workout.class).getType()) && snapshot.getValue(Workout.class).getLevel().equals(level)) {
                            String value = snapshot.getValue(Workout.class).getName() + "-" + snapshot.getValue(Workout.class).getType()
                                    + "-" + snapshot.getValue(Workout.class).getLevel();
                            listView.setAdapter(arrayAdapter);
                            arrayList.add(value);
                            arrayList.stream().sorted();
                            arrayAdapter.notifyDataSetChanged();
                        }else{
                            listView.setAdapter(arrayAdapter);
                            arrayList.stream().sorted();
                            arrayAdapter.notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), WorkoutInfo.class);
                Bundle b = new Bundle();
                String value = arrayList.get(position);
                String [] arr = value.split("-");
                b.putString("name", arr[0]); //name
                b.putString("type",arr[1]); //type
                b.putString("level",arr[2]);
                b.putString("caller","PlansActivty");
                b.putString("plan",plan);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

}