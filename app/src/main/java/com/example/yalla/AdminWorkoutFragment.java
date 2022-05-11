package com.example.yalla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class AdminWorkoutFragment extends Fragment {
    EditText editName,editType,updateName,updateId,removeId,removeName,editYoutubeID;
    Button btnAdd,btnUpdate,btnRemove;
    Spinner type,level;
    String typeSelected, levelSelected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_crud, container, false);
        DAOTworkout dao = new DAOTworkout();
        editName = view.findViewById(R.id.workOutName);
        type = view.findViewById(R.id.spinnerAddType);
        level = view.findViewById(R.id.spinnerAddLevel);
        btnAdd = view.findViewById(R.id.btnAddWorkOut);

        editYoutubeID = view.findViewById(R.id.editYoutubeId);
        String youtubeID = editYoutubeID.getText().toString();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type.setAdapter(adapter);
         type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSelected = parent.getItemAtPosition(position).toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {
                 typeSelected = parent.getItemAtPosition(0).toString();
             }
         });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.levels, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        level.setAdapter(adapter1);
         level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 levelSelected  = parent.getItemAtPosition(position).toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {
                 levelSelected = parent.getItemAtPosition(0).toString();
             }
         });
         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Workout workout = new Workout(editName.getText().toString(),typeSelected,levelSelected,editYoutubeID.getText().toString());
                dao.add(workout).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "workout added", Toast.LENGTH_SHORT).show();
                    }
                });
             }
         });

         updateId = view.findViewById(R.id.editUpdateId);
         updateName = view.findViewById(R.id.updatedName);
         btnUpdate = view.findViewById(R.id.btnUpdateName);
         btnUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 HashMap<String,Object> hashMap = new HashMap<>();
                 hashMap.put("name",updateName.getText().toString());
                 dao.update(updateId.getText().toString(),hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                         Toast.makeText(getActivity(), "Name Updated", Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });

          removeId = view.findViewById(R.id.editRemoveId);
          btnRemove = view.findViewById(R.id.btnRemoveName);
          btnRemove.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dao.remove(removeId.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void unused) {
                          Toast.makeText(getActivity(), "Workout removed", Toast.LENGTH_SHORT).show();
                      }
                  });
              }
          });
         return view;
    }
}