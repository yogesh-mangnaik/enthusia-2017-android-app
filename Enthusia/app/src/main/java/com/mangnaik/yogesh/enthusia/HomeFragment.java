package com.mangnaik.yogesh.enthusia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mangnaik.yogesh.enthusia.model.Image;
import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;

public class HomeFragment extends android.support.v4.app.Fragment  {

    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String string = getArguments().getString("homepart");
        System.out.println(string);
        assert string != null;
        View view;
        switch (string){
            case "home":
                view =  inflater.inflate(R.layout.sample, container, false);
                CircleLayout circleLayout = (CircleLayout) view.findViewById(R.id.circle_layout);
                HomeFragment fragment = new HomeFragment();
                Bundle bundle = new Bundle();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                circleLayout.setOnItemClickListener(new CircleLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        String name = null;
                        if (view instanceof CircleImageView) {
                            name = ((CircleImageView) view).getName();
                        }
                        assert name != null;
                        if(name.equals("Events")){
                            ((MainActivity)getActivity()).changeFragment(R.id.nav_events);
                        }
                        else if(name.equals("Gallery")){
                            ((MainActivity)getActivity()).changeFragment(R.id.nav_gallery);
                        }
                        else if(name.equals("Info")){
                            ((MainActivity)getActivity()).changeFragment(R.id.nav_commitee);
                        }
                        System.out.println(name);
                    }
                });
                return view;

            case "event":
                view = inflater.inflate(R.layout.fragment_event, container, false);
                int[] id = new int[]{R.id.cricket_card, R.id.athletics_card, R.id.badminton_card
                        , R.id.basketball_card
                        , R.id.carrom_card, R.id.chess_card, R.id.football_card, R.id.kabaddi_card,
                        R.id.kho_kho_card,
                        R.id.rink_football_card, R.id.swimming_card, R.id.table_tennis_card};
                for(int i=0; i<id.length; i++){
                    View v = view.findViewById(id[i]);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("Pressed");
                            Intent i = new Intent(getActivity(), EventActivity.class);
                            getActivity().startActivity(i);
                        }
                    });
                }
                return view;
            case "commitee":
                view = inflater.inflate(R.layout.fragment_commitee, container, false);
                System.out.println("Commitee");
                return view;
            case "sponsors":
                int[] ids = new int[]{R.drawable.bharatcycle, R.drawable.envi, R.drawable.powerhouse, R.drawable.maharashtratimes
                ,R.drawable.jam, R.drawable.rio};
                int[] imageIds = new int[]{R.id.bharatcycle, R.id.envi, R.id.powerhouse, R.id.maharashtratimes,
                R.id.jam, R.id.rio};
                view = inflater.inflate(R.layout.fragment_sponsors, container, false);
                for(int i=0; i<ids.length; i++){
                    ImageView imageView = (ImageView) view.findViewById(imageIds[i]);
                    Glide.with(this)
                            .load(ids[i])
                            .fitCenter()
                            .crossFade()
                            .into(imageView);
                }
                return view;
        }

        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
