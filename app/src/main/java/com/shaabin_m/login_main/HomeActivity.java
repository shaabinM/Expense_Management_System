package com.shaabin_m.login_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView addCard, searchCard, feedbackCard, aboutCard, profileCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addCard = (CardView) findViewById(R.id.add_btn);
        searchCard = (CardView) findViewById(R.id.search_btn);
        feedbackCard = (CardView) findViewById(R.id.feedback_btn);
        aboutCard = (CardView) findViewById(R.id.about_btn);
        profileCard = (CardView) findViewById(R.id.profile_btn);
        addCard.setOnClickListener(this);
        searchCard.setOnClickListener(this);
        feedbackCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId())  {
            case R.id.add_btn: i = new Intent(this,add.class); startActivity(i); break;
            case R.id.search_btn: i = new Intent(this,search.class); startActivity(i); break;
            case R.id.feedback_btn: i = new Intent(this,feedback.class); startActivity(i); break;
            case R.id.profile_btn: i = new Intent(this,profile.class); startActivity(i); break;
            case R.id.about_btn: i = new Intent(this,about.class); startActivity(i); break;
            default:break;
        }
    }
}