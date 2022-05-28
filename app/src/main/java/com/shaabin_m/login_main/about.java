package com.shaabin_m.login_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firestore.v1.MapValue;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class about extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActionBar action =getSupportActionBar();
        action.hide();
        ImageSlider mainslider = (ImageSlider) findViewById(R.id.image_slider);


        final List<SlideModel> remoteimages=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Slider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                    remoteimages.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                mainslider.setImageList(remoteimages,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng = new LatLng(12.774155901129, 77.69615706834736);
       // LatLng society = new LatLng(12.77, 77.69);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Palm Groves");
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}