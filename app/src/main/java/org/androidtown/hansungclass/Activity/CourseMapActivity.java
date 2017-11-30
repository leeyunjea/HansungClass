package org.androidtown.hansungclass.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidtown.hansungclass.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CourseMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    ImageButton backBtn;
    String take_location;
    private GoogleMap gmap;
    private Double latitude;
    private Double longitude;
    private Marker mPerth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_map);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        take_location = intent.getStringExtra("location");
        Toast.makeText(this, take_location, Toast.LENGTH_SHORT).show();

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toAddress();
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        // Add a marker in Sydney and move the camera
        gmap = gMap;
        Log.i("yunjae", "onMapReady");
        LatLng sydney = new LatLng(-34, 151);
        gMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // move the camera
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        updateCamera();
    }

    public void toAddress() {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(take_location, 1);
            if (addresses.size() > 0) {
                Log.i("yunjae", "address.size>0");
                Address bestResult = (Address) addresses.get(0);
                latitude = bestResult.getLatitude();
                longitude = bestResult.getLongitude();
            }
        } catch (IOException e) {
            Log.e(getClass().toString(), "Failed in using Geocoder.", e);
            return;
        }
    }

    public void updateCamera() {
        Log.i("yunjae", "updateCamera(): " +  + latitude + " " + longitude);
        LatLng LOC = new LatLng(latitude, longitude);
        gmap.clear();
        gmap.addMarker(new MarkerOptions().position(LOC).title(take_location));
        Log.i("yunjae", "addMarker");
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOC, 20));
        gmap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(mPerth)) {

        }
        return false;
    }
}
