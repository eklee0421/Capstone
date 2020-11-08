package org.techtown.capstone;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    SupportMapFragment mapFragment;
    GoogleMap map;

    MarkerOptions myLocationMarker;
    int val = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;

            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);

                if(val == -1) mes();
                else {startActivityForResult(intent, val);}
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        final ArrayAdapter<CharSequence> adspin1;
        adspin1 = ArrayAdapter.createFromResource(this, R.array.my_city, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adspin1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(adspin1.getItem(position).equals("서울")){
                    final ArrayAdapter<CharSequence> adspin2;
                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.spinner_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setAdapter(adspin2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(adspin2.getItem(position).equals("강서구")){
                                final ArrayAdapter<CharSequence> adspin3;
                                adspin3 = ArrayAdapter.createFromResource(MainActivity.this, R.array.spinner_gangsu, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
                                spinner3.setAdapter(adspin3);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if(adspin3.getItem(position).equals("마곡역")){
                                            gogoMap(37.560166, 126.825423, "서울", "강서구"); val = 1;
                                        }
                                        else if(adspin3.getItem(position).equals("화곡역")){
                                            gogoMap(37.498228, 127.027708, "서울", "강남구"); val = 2;
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //스피너 선택시
                if(position == 0) {
                    startLocationService(); val = -1;
                }
                else if(position == 1)  {gogoMap(37.560166, 126.825423, "서울", "강서구"); val = 1;}
                else if(position == 2)  {gogoMap(37.498228, 127.027708, "서울", "강남구"); val = 2;}
                else if(position == 3)  {gogoMap(37.526110, 126.864807, "서울", "양천구"); val = 3;}
                else if(position == 4)  {gogoMap(37.611042, 126.929507, "서울", "은평구"); val = 4;}
                else if(position == 5)  {gogoMap(37.638056, 127.025605, "서울", "강북구"); val = 5;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //선택되지 않으면
                startLocationService();
            }
        });*/

    }

    public void gogoMap(double latitude, double longitude, String lo, String cation) {

        // 맵 위치를 이동하기
        CameraUpdate update = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        // 위도,경도
        map.moveCamera(update);

        // 보기 좋게 확대 zoom 하기
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.animateCamera(zoom);

        // 마커 표시 하기 : 위치지정, 풍선말 설정
        MarkerOptions markerOptions = new MarkerOptions()
                // 마커 위치
                .position(new LatLng(latitude, longitude))
                .title(lo) // 말풍선 주 내용
                .snippet(cation); // 말풍선 보조내용


        // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
        map.addMarker(markerOptions).showInfoWindow();

    }

    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                Log.d("Map", message);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 1000000000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치 -> Latitude : "+ latitude + "\nLongitude:"+ longitude;
            Log.d("Map", message);

            showCurrentLocation(latitude, longitude);
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(curPoint);
    }

    private void showMyLocationMarker(LatLng curPoint) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            //myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(curPoint);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        //Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        //Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    public void mes() {
        Toast.makeText(this, "위치를 선택하세요", Toast.LENGTH_LONG).show();
    }

}
