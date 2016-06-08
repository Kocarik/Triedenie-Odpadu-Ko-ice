package com.pmm.triedenieodpadukosice;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latlng1 = new LatLng(48.734132, 21.283426); //Lidické nám.16 - Kosice
        LatLng latLng2 = new LatLng(48.702963, 21.254920); //Krakovska 4 - Kosice
        LatLng latLng3 = new LatLng(48.715944, 21.218213); //Wuppertálska 21 - Kosice
        LatLng latLng4 = new LatLng(48.718280, 21.217435); //Titogradská 17 - Kosice
        LatLng latLng5 = new LatLng(48.697621, 21.281691); //Bukovecká 1 - Kosice
        LatLng latLng6 = new LatLng(48.756893, 21.265934); //Bruselská 4 - 5 - Kosice


        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(latlng1, 10);
        mMap.moveCamera(camera);

        MarkerOptions options1 = new MarkerOptions()
                .position(latlng1)
                .title("29.4.- 6.5 - Jaltská 23")
                .snippet("Kontajner zo zadnejstrany bloku");

        MarkerOptions options2 = new MarkerOptions()
                .position(latLng2)
                .title("8.7.-15.7 - Krakovská 4")
                .snippet("Garáže");

        MarkerOptions options3 = new MarkerOptions()
                .position(latLng3)
                .title("15.7.- 22.7 - Wuppertálska 21");

        MarkerOptions options4 = new MarkerOptions()
                .position(latLng4)
                .title("12.8.- 19.8 - Titogradská 17");

        MarkerOptions options5 = new MarkerOptions()
                .position(latLng5)
                .title("1.7.- 4.7 - Bukovecká 1");

        MarkerOptions options6 = new MarkerOptions()
                .position(latLng6)
                .title("5.7.- 12.7 - Bruselská 4 - 5");


        mMap.addMarker(options1).hideInfoWindow();
        mMap.addMarker(options2).hideInfoWindow();
        mMap.addMarker(options3).hideInfoWindow();
        mMap.addMarker(options4).hideInfoWindow();
        mMap.addMarker(options5).hideInfoWindow();
        mMap.addMarker(options6).hideInfoWindow();



       /* // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/



    }


}
