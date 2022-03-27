package com.shaabin_m.Login_Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shaabin_m.login_main.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText username, password,flat,email;
    Spinner tower;
    FirebaseAuth fAuth;
    MaterialButton signup, signin;
    FirebaseFirestore fstore;
    String userID;
    //DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar action =getSupportActionBar();
        action.hide();
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        tower = (Spinner) findViewById(R.id.tower);
        flat = (EditText) findViewById(R.id.flat);
        email = (EditText)findViewById(R.id.email);
        signup = (MaterialButton)  findViewById(R.id.btnsignup);
        signin = (MaterialButton) findViewById(R.id.btnsignin);
        //DB = new DBHelper(this);
        //Spinner
        String[] items = new String[]{"A1","A2","A3","A4","A5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        tower.setAdapter(adapter);
        //

        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){

                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String fname = username.getText().toString();
                String tow = tower.getSelectedItem().toString();
                String flatno = flat.getText().toString();

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

                fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname",fname);
                            user.put("email",mail);
                            user.put("tower",tow);
                            user.put("flat",flatno);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: User profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: "+e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }

            });

//            public void onClick(View view) {
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//                String repass = repassword.getText().toString();
//                String tow = tower.getSelectedItem().toString();
//                String flatno = flat.getText().toString();
//                String mail = email.getText().toString();
//
//
//                if(user.equals("")||pass.equals("")||repass.equals(""))
//                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                else{
//                    if(pass.equals(repass)){
//                        Boolean checkuser = DB.checkusername(user);
//                        if(checkuser==false){
//                            Boolean insert = DB.insertData(user, pass, tow, flatno, mail);
//                            if(insert==true){
//                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                                startActivity(intent);
//                            }else{
//                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
//                    }
//                } }
//        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}