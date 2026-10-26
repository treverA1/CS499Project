package com.example.myapplication;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAct extends AppCompatActivity {

    EditText nameText;
    EditText passText;
    Button loginBtn;
    Button createBtn;
    AppDB loginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        nameText = findViewById(R.id.nameText);
        passText = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.buttonLogin);
        createBtn = findViewById(R.id.createLogin);

        loginDB = AppDB.getDatabase(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                //username is trimmed to remove any leading white space, same with password
                String userName = nameText.getText().toString().trim();
                String userPass = passText.getText().toString().trim();

                if(userName.isEmpty() || userPass.isEmpty()){
                    //Popup bubble text to let user know of misinputs
                    Toast.makeText(LoginAct.this, "Missing Username or Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserDB PreExistUser = loginDB.userDao().getUserByUserName(userName);

                //make sure usernames are unique, when using the create account button
                if(PreExistUser != null){
                    Toast.makeText(LoginAct.this, "Username already exists", Toast.LENGTH_SHORT).show();

                    //Causes user to go to inventory screen
                    Intent btnIntent = new Intent(LoginAct.this, InvAct.class);
                    startActivity(btnIntent);

                    finish();
                }
                else {
                    loginDB.userDao().insertUser(new UserDB(userName, userPass));
                    Toast.makeText(LoginAct.this, "Account created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
