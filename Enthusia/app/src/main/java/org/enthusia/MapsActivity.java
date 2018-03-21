package org.enthusia;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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


        double[] latlng = new double[]{19.023265, 72.856731,
                19.020181, 72.855480
                ,19.020504, 72.855400
                ,19.020453, 72.855684
                ,19.021377, 72.855745
                ,19.022308, 72.856187
                ,19.022474, 72.856240
                ,19.022440, 72.855625
                ,19.023303, 72.856889
                ,19.019150, 72.855335};
        String[] name = new String[]{"Gymkhana", "Cricket Ground", "Volleyball Court", "KhoKho ground"
        ,"Football Ground", "Quad/Basketball Court", "Canteen", "Main Gate", "Back Gate", "Hostel Gate"};
        LatLng vjti = new LatLng(19.022152, 72.855933);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(vjti)      // Sets the center of the map to Mountain View
                .zoom(19)                   // Sets the zoom
                .bearing(80)                // Sets the orientation of the camera to east
                .tilt(55)                   // Sets the tilt of the camera to 30 degrees
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        for(int i=0; i<name.length; i++){
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latlng[2*i], latlng[2*i+1]))
                    .title(name[i]));
            m.showInfoWindow();
        }
        mMap.setMinZoomPreference(18.5f);
        mMap.setMaxZoomPreference(19.5f);
        mMap.setBuildingsEnabled(true);
    }
}
