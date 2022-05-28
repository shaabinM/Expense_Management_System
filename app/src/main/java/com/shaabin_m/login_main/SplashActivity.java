package com.shaabin_m.login_main;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.TextView;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.auth.AuthResult;

public class SplashActivity extends AppCompatActivity {

    Animation left_anim, right_anim;
    TextView furniture, store;
    FirebaseAuth fAuth;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FirebaseDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar action =getSupportActionBar();
        action.hide();
        fAuth = FirebaseAuth.getInstance();
        left_anim = AnimationUtils.loadAnimation(this, R.anim.left_anim);
        right_anim = AnimationUtils.loadAnimation(this, R.anim.right_anim);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        furniture = findViewById(R.id.apartfin);


        furniture.setAnimation(left_anim);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);

                if(fAuth.getCurrentUser() !=null)
                {
                    myIntent = new Intent(SplashActivity.this, MainActivity.class);
                }
                else
                {
                    myIntent = new Intent(SplashActivity.this, HomeActivity.class);
                }

                SplashActivity.this.startActivity(myIntent);

            }
        }, 2000);



    }
}