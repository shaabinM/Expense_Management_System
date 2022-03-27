package com.shaabin_m.Login_Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shaabin_m.login_main.R;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    MaterialButton btnlogin,btncreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (MaterialButton) findViewById(R.id.btnsignin1);
        btncreate = (MaterialButton) findViewById(R.id.btnsignup);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        ActionBar action =getSupportActionBar();
        action.hide();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(mail)){
                    email.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    email.setError("Password is Required.");
                    return;
                }
                if (pass.length()<6){
                    password.setError("Password must be greater than 6 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
//        btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//
//                if(user.equals("")||pass.equals(""))
//                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                else{
//                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
//                    if(checkuserpass==true){
//                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
//                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }
}