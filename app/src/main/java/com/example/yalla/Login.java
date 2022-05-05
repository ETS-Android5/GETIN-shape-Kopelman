package com.example.yalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private Button btnlogin;
    EditText inputEmail, inputPassword;
    String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private TextView TosignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        inputEmail = findViewById(R.id.etemail);
        inputPassword = findViewById(R.id.mypass);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        DAOUser dao = new DAOUser();

        TosignUp = findViewById(R.id.createnewac);

        TosignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, SignUp.class);
                startActivity(it);
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }

    private void performLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String managerEmail = "kopelman050@gmail.com";
        String managerEmail1 = "Kopelman050@gmail.com";
        String managerPassword = "Kopelman1";

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter current Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Please Wait While Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if ((email.equals(managerEmail) || email.equals(managerEmail1)) && password.equals(managerPassword)) {
                            progressDialog.dismiss();
                            sendManagerToNextActivity();
                            Toast.makeText(Login.this, "Manager Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

            private void sendUserToNextActivity() {
                Intent intent = new Intent(Login.this, UserMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            private void sendManagerToNextActivity() {
                Intent intent = new Intent(Login.this, AdminMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
