package com.shaabin_m.Login_Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.shaabin_m.login_main.R;

public class profile extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextView fname, mail, address;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID, tower,flatno,address1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fname = findViewById(R.id.fullname);
        mail= findViewById(R.id.mail);
        address = findViewById(R.id.address);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot.exists()){
                    fname.setText(documentSnapshot.getString("fname"));
                    mail.setText(documentSnapshot.getString("email"));
                    tower = documentSnapshot.getString("tower");
                    flatno = documentSnapshot.getString("flat");
                    address1 = tower.concat("-").concat(flatno);
                    address.setText(address1);
                }else
                    Log.d(TAG, "onEvent: Document does not exists");
            }
        });
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
}