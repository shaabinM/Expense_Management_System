package com.shaabin_m.Login_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class feedback extends AppCompatActivity {
    private EditText mEditTextSubject, mEditTextMessage, mEditTextTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mEditTextSubject = findViewById(R.id.edit_text_Subject);
        mEditTextMessage =findViewById(R.id.edit_text_message);
        mEditTextTo = findViewById(R.id.edit_text_to);
        MaterialButton buttonSend = findViewById(R.id.button_feedback);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail( );
            }
        });

    }
    private void sendMail(){


        String recipient = mEditTextTo.getText().toString();
        String[] recipients = recipient.split(",");
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"choose an Email client"));
    }
}