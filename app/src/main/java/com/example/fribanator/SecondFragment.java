package com.example.fribanator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements LocationListener {
    View view;
    LocationManager locationManager;
    Button location_button;
    TextView distaceTV;
    Location lastLocation;
    Location finalLocation;
    Boolean counting = false;
    boolean buttonPressed;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_second, container, false);
        location_button = view.findViewById(R.id.locationButton);
        distaceTV = view.findViewById(R.id.distanceInM);
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        getLocation();
        Log.d("juu", "onCreateView: ");
        location_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("juu", "onClick: ");
                buttonPressed = true;
                getLocation();
                if (counting){
                    counting=false;
                    location_button.setText("Aloita laskeminen");


                }else {

                    counting = true;
                    location_button.setText("Lopeta laskeminen");

                }



            }
        });
        // Inflate the layout for this fragment
        return view;

    }



    @SuppressLint("MissingPermission")
    private void getLocation(){
        Log.d("juu", "getLocation: ");
        try {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("juu",""+ location.getLatitude()+ ", "+location.getLongitude()+ " " );
        if (counting==true){

            lastLocation = location;
            locationManager.removeUpdates(this);
        }else{
            finalLocation = location;

            if (finalLocation != null && lastLocation != null && buttonPressed == true){countDistance(lastLocation,finalLocation);}

        }




    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void countDistance(Location location, Location location2) {
        Log.d("juu", "countDistance: "+ location.distanceTo(location2));
        Log.d("juu", "1 " + location +"2" + location2);
        int distace = (int) location.distanceTo(location2);
        distaceTV.setText(""+distace+"M");
        buttonPressed = false;
        locationManager.removeUpdates(this);

    }


}