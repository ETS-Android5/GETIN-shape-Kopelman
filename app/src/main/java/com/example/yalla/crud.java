package com.example.yalla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class crud extends Fragment {
    EditText editName,editType,updateName,updateId,removeId,removeName;
    Button btnAdd,btnUpdate,btnRemove;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =  inflater.inflate(R.layout.fragment_crud, container, false);
        DAOTworkout dao = new DAOTworkout();
        editName = view.findViewById(R.id.workOutName);
         editType = view.findViewById(R.id.workOutType);
         btnAdd = view.findViewById(R.id.btnAddWorkOut);
         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Workout workout = new Workout(editName.getText().toString(),editType.getText().toString(),0);
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