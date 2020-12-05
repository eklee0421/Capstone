package org.techtown.capstone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    ImageView imageView;
    int imageRoad[] = {R.drawable.road0, R.drawable.road1, R.drawable.road2 };
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2,container,false);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.layout2);

        Bundle bundle2 = getArguments();
        if(bundle2 != null){
            final int nowRoad = bundle2.getInt("nowroad");
            if(nowRoad < 40){
                linearLayout.setBackgroundResource(imageRoad[2]);
            }
            else if(nowRoad >= 80){
                linearLayout.setBackgroundResource(imageRoad[0]);
            }
            else{
                linearLayout.setBackgroundResource(imageRoad[1]);
            }


        }

        return rootView;
    }
}