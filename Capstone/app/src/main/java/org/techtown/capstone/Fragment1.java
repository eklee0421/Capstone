package org.techtown.capstone;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    ImageView imageView;
    int imageWeather[] = {R.drawable.weather0, R.drawable.weather1, R.drawable.weather2,R.drawable.weather3,R.drawable.weather4, R.drawable.weather5,R.drawable.weather6};
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1,container,false);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.layout1);
        //imageView = rootView.findViewById(R.id.imageView9);

        //int nowWeather = 0;
        Bundle bundle1 = getArguments();
        if(bundle1 != null){
            final int nowWeather = bundle1.getInt("nowWeather");
            final double rain = bundle1.getDouble("rain");
            linearLayout.setBackgroundResource(imageWeather[nowWeather]);
            TextView textView = rootView.findViewById(R.id.textView6);
            if(nowWeather==3 || nowWeather==4){
                textView.setText("현재 강수량 : " + rain+"mm");
                textView.setTextColor(Color.parseColor("#000000"));
            }
            else if(nowWeather == 5 || nowWeather==6){
                textView.setText("현재 강수량 : " + rain+"mm");
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            }

            //imageView.setImageResource(imageWeather[nowWeather]);
            Log.d("=============", ""+nowWeather);
        }

        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.detach(this).attach(this).commit();

        //setImage(nowWeather);
        return rootView;
        //return inflater.inflate(R.layout.fragment1,container,false);
    }
}