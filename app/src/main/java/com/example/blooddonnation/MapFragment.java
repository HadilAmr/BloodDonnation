package com.example.blooddonnation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng location1 = new LatLng(61.212121, -149.877228); // American Red Cross
                googleMap.addMarker(new MarkerOptions().position(location1).title("American Red Cross")
                        .snippet("235 E 8th Ave Ste 200, Anchorage, AK 99501\nhttps://www.redcross.org/local/alaska.html\n(907) 646-5401"));

                LatLng location2 = new LatLng(61.138015, -149.866180); // Blood Bank of Alaska 1
                googleMap.addMarker(new MarkerOptions().position(location2).title("Blood Bank of Alaska")
                        .snippet("8920 Old Seward Hwy C, Anchorage, AK 99515\nhttp://www.bloodbankofalaska.org/\n(907) 222-5630"));

                LatLng location3 = new LatLng(61.204923, -149.815736); // Blood Bank of Alaska 2
                googleMap.addMarker(new MarkerOptions().position(location3).title("Blood Bank of Alaska")
                        .snippet("1215 Airport Heights Dr, Anchorage, AK 99508\nhttp://www.bloodbankofalaska.org/\n(907) 222-5600"));

                LatLng location4 = new LatLng(64.858547, -147.711792); // Blood Bank of Alaska - Fairbanks
                googleMap.addMarker(new MarkerOptions().position(location4).title("Blood Bank of Alaska - Fairbanks")
                        .snippet("3010 Airport Way, Fairbanks, AK 99709\nhttp://www.bloodbankofalaska.org/\n(907) 456-5645"));

                LatLng location5 = new LatLng(32.639586, -85.384421); // Talecris Plasma Resources
                googleMap.addMarker(new MarkerOptions().position(location5).title("Talecris Plasma Resources")
                        .snippet("2540 Pepperell Pkwy, Opelika, AL 36801\nhttps://www.grifolsplasma.com/en/-/opelika-al\n(334) 737-0090"));

                LatLng location6 = new LatLng(32.647194, -85.392876); // Lifesouth Community Blood Center 1
                googleMap.addMarker(new MarkerOptions().position(location6).title("Lifesouth Community Blood Center")
                        .snippet("505 E Thomason Cir #5431, Opelika, AL 36801\nhttp://www.lifesouth.org/\n(334) 705-0884"));

                LatLng location7 = new LatLng(32.578678, -85.489844); // CSL Plasma
                googleMap.addMarker(new MarkerOptions().position(location7).title("CSL Plasma")
                        .snippet("1655 S College St Suite C, Auburn, AL 36832\nhttps://www.cslplasma.com/center/183\n(334) 539-3333"));

                LatLng location8 = new LatLng(32.369073, -86.258362); // Lifesouth Community Blood Center 2
                googleMap.addMarker(new MarkerOptions().position(location8).title("Lifesouth Community Blood Center")
                        .snippet("4139 Carmichael Rd, Montgomery, AL 36106\nhttp://www.lifesouth.org/\n(334) 260-0803"));

                LatLng location9 = new LatLng(31.225585, -85.398566); // Grifols
                googleMap.addMarker(new MarkerOptions().position(location9).title("Grifols")
                        .snippet("2721 W Main St, Dothan, AL 36301\nhttps://www.grifolsplasma.com/en/-/dothan-al\n(334) 651-8000"));

                LatLng location10 = new LatLng(31.206091, -85.393429); // CSL Plasma 2
                googleMap.addMarker(new MarkerOptions().position(location10).title("CSL Plasma")
                        .snippet("3341 S Oates St Suite 114, Dothan, AL 36301\nhttps://www.cslplasma.com/center/504\n(334) 500-3646"));
                LatLng location11 = new LatLng(33.69796, -7.38421);
                googleMap.addMarker(new MarkerOptions().position(location11).title("Mohammedia Blood Bank"));
                LatLng customLocation = new LatLng(34.1279621752393, -4.942705100421216);
                googleMap.addMarker(new MarkerOptions().position(customLocation));
                LatLng casablancaLocation = new LatLng(33.7986962376806, -7.577496654654715);
                googleMap.addMarker(new MarkerOptions().position(casablancaLocation).title("Casablanca"));
                LatLng customLocation1 = new LatLng(33.74390107214726, -7.689196808564321);
                googleMap.addMarker(new MarkerOptions().position(customLocation1).title("Casa"));
                LatLng meknesLocation = new LatLng(34.07756322115989, -5.548137364531693);
                googleMap.addMarker(new MarkerOptions().position(meknesLocation).title("Meknes"));
                LatLng rabatLocation = new LatLng(34.12584330120063, -6.909990450022524);
                googleMap.addMarker(new MarkerOptions().position(rabatLocation).title("Rabat"));
                LatLng RabatLocation = new LatLng(34.20765473321747, -6.826268024325084);
                googleMap.addMarker(new MarkerOptions().position(RabatLocation).title("Rabat"));
                LatLng berrchidLocation = new LatLng(33.432734291223525, -7.538976520018183);
                googleMap.addMarker(new MarkerOptions().position(berrchidLocation).title("Berrchid"));
                LatLng marrakeshLocation = new LatLng(32.14583641036301, -8.011302342706328);
                googleMap.addMarker(new MarkerOptions().position(marrakeshLocation).title("Marrakesh"));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                    }
                });
            }
        });
        return view;
    }
}