package org.techtown.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    //String packageName = getPackageName();
    //int res = getResources().getIdentifier("sunny","drawable",packageName);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), " "+ requestCode+ " "+resultCode, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //FragmentManager manager = getSupportFragmentManager();
        //fragment1 = (Fragment1) manager.findFragmentById(R.id.fragment1);

        String nowLocal ="";

        int nW = 0;
        int nR =0;
        String vU = "";
        double rr = 0;

        Intent intent = getIntent();
        if(intent != null) {
            nowLocal = intent.getStringExtra("local");
            nW = intent.getIntExtra("nowWeather",0);
            nR = intent.getIntExtra("nowroad", 0);
            vU = intent.getStringExtra("videoUrl");
            rr = intent.getDoubleExtra("rain",0);
        }

        final int nowWeather = nW;
        final int nowroad = nR;
        final double rain= rr;
        final String videoUrl = vU;

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(nowLocal);

        Bundle bundle1 = new Bundle();
        bundle1.putInt("nowWeather",nowWeather);
        bundle1.putDouble("rain",rain);
        fragment1.setArguments(bundle1);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("현재 날씨"));
        tabs.addTab(tabs.newTab().setText("도로 상황"));
        tabs.addTab(tabs.newTab().setText("실시간 날씨"));
        /*
        Toast toast = Toast.makeText(getApplicationContext(),""+nowWeather, Toast.LENGTH_SHORT);
        toast.show();

        Toast toast1 = Toast.makeText(getApplicationContext(),""+rain, Toast.LENGTH_SHORT);
        toast1.show();
*/
        //Toast toast2 = Toast.makeText(getApplicationContext(),""+nowroad, Toast.LENGTH_SHORT);
        //toast2.show();


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("WeatherActivity", "선택된 탭: " + position);
                Fragment selected = null;
                if(position == 0) {
                    selected = fragment1;
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("nowWeather",nowWeather);
                    bundle1.putDouble("rain",rain);
                    fragment1.setArguments(bundle1);
                    selected = fragment1;
                }
                else if(position == 1) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("nowroad",nowroad);
                    fragment2.setArguments(bundle2);
                    selected = fragment2;
                }
                else if(position == 2) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("videoUrl",videoUrl);
                    fragment3.setArguments(bundle3);
                    selected = fragment3;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}