package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yalla.EmailList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class signUpActivity extends AppCompatActivity {
    private Button btnRegister;
    EditText inputEmail, inputPassword, fullName;
    String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    List<String> emailListList;
    EmailList emailList = (EmailList) this.getApplication();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.editName);

        inputEmail = findViewById(R.id.editEmail);
        inputPassword = findViewById(R.id.editPass);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        emailListList = emailList.getEmailList();

        DAOUser dao = new DAOUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

        DAOEmail daoEmail = new DAOEmail();
        DatabaseReference databaseReferenceEmail = FirebaseDatabase.getInstance().getReference("Email");

        btnRegister = findViewById(R.id.buttonAcount);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PerformAuth();
                ArrayList<String> list = new ArrayList<>();
                list.add("Not yet");
                if (!(inputEmail.getText().toString().matches(emailPattern))) {
                    inputEmail.setError("Enter proper email");
                } else if (inputPassword.getText().toString().length() < 6) {
                    inputPassword.setError("Password too short");
                } else {
                    String[] value = inputEmail.getText().toString().split("@");
                    String key = value[0];
                    databaseReferenceEmail.child("Email").child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(signUpActivity.this, "email already exists!", Toast.LENGTH_SHORT).show();
                            } else {
                                daoEmail.add(inputEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(signUpActivity.this, inputEmail.getText().toString() + " Added" , Toast.LENGTH_SHORT).show();
                                    }
                                });
                                User user = new User(fullName.getText().toString(), inputEmail.getText().toString(), inputPassword.getText().toString(), list, "");
                                dao.add(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(signUpActivity.this, "User created", Toast.LENGTH_SHORT).show();
                                        Intent it = new Intent(signUpActivity.this, loginActivity.class);
                                        startActivity(it);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            };

            private void PerformAuth() {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (!email.matches(emailPattern)) {
                    inputEmail.setError("Enterr current Email");
                } else if (password.isEmpty() || password.length() < 6) {
                    inputPassword.setError("Enter Proper Password");
                } else {
                    progressDialog.setMessage("Please Wait While Registration...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(signUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(signUpActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(getApplicationContext(), loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Name", fullName.getText().toString());
        startActivity(intent);
    }
}
