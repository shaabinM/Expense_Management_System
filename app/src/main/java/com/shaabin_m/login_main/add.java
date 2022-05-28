package com.shaabin_m.login_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class add extends AppCompatActivity implements PaymentResultListener {
    //initialize variable
    Button btpay;
    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar action =getSupportActionBar();
        action.hide();
        //assign variable
        btpay = findViewById(R.id.bt_pay);
        amount= findViewById(R.id.AMT);
        //initialize amount





        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money = amount.getText().toString();
                int amounts =Math.round(Float.parseFloat(money)*100);
                //initialize razorpay checkout
                Checkout checkout = new Checkout();
                //set key
                checkout.setKeyID("rzp_test_0Ufpwxx3EFPgcQ");
                //set image
                checkout.setImage(com.razorpay.R.drawable.rzp_logo);
                // initialize json object
                JSONObject object = new JSONObject();

                try {
                    //put name
                    object.put("name","Society Project");
                    //put description
                    object.put("description","Test Payment");
                    //put theme color
                    object.put("theme.color","#0093DD");
                    //put currency
                    object.put("currency","INR");
                    //put amount
                    object.put("amount",amounts);
                    //put mobile no.
                    object.put("prefill.contact","9810663153");
                    //put email
                    object.put("prefill.email","m.shaabin@gmail.com");
                    //open rzp checkout
                    checkout.open(add.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //initialize alert dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle("Payemnt ID");
        //set message
        builder.setMessage(s);
        //show alert dialogue
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        //display toast
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}