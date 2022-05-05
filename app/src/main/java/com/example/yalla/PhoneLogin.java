package com.example.yalla;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PhoneLogin extends AppCompatActivity {

    private EditText textPhone;
    private Button btnPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        getSupportActionBar().hide();

        textPhone = (EditText) findViewById(R.id.editPhone);
        btnPhone = (Button) findViewById(R.id.btnNext);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        sendSMS();
                    }else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });
    }
    private void sendSMS(){
        String number = textPhone.getText().toString().trim();
        final Random myRandom = new Random();
        String SMStxt = String.valueOf(myRandom.nextInt(10000));
        try{

            SmsManager  smsManager  = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,SMStxt,null,null);
            Toast.makeText(this,"Message is sent",Toast.LENGTH_SHORT).show();
            Intent it = new Intent(getApplicationContext(), VerifyCode.class);
            it.putExtra("code",SMStxt);
            startActivity(it);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Faild to send  message", Toast.LENGTH_SHORT).show();
        }
    }

}