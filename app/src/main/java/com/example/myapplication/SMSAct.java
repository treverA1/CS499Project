package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSAct extends AppCompatActivity {
    EditText phoneNumber;
    Button sendTxt;

    Button returnBtn;
    TextView statusText;

    private static final int SMSCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        phoneNumber = findViewById(R.id.phoneNumber);
        sendTxt = findViewById(R.id.sendSMSBtn);
        returnBtn = findViewById(R.id.returnBtn);
        statusText = findViewById(R.id.statusText);

        sendTxt.setOnClickListener(v -> sendSMS());

        returnBtn.setOnClickListener(v -> {
            Intent btnIntent = new Intent(SMSAct.this, InvManAct.class);
            startActivity(btnIntent);
        });
    }

    private void sendSMS(){
        //eliminate white space
        String number = phoneNumber.getText().toString().trim();

        if (number.isEmpty()){
            statusText.setText("Enter phone number");
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMSCode);
        }

        else {
            sendMessage(number);
        }
    }

    private void sendMessage(String number) {
        //Send message to user, if fails it is caught and notified
        try {
            SmsManager smsManager = getSystemService(SmsManager.class);

            smsManager.sendTextMessage(number, null, "Here is a test Message.", null, null);

            statusText.setText("SMS Sent!");
        }
        catch (Exception e) {
            statusText.setText("Failed to send SMS");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMSCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                statusText.setText("Permission granted. Click again to send SMS.");
            } else {
                statusText.setText("Permission denied. SMS disabled.");
            }
        }
    }
}
