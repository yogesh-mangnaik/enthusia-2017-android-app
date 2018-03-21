package org.enthusia.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.enthusia.Constants;
import org.enthusia.GalleryActivity;
import org.enthusia.MainActivity;
import org.enthusia.MapsActivity;
import org.enthusia.R;

public class AboutUsFragment extends android.support.v4.app.Fragment   {
    public AboutUsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.about_us, container, false);
        final CardView galleryCard = (CardView) view.findViewById(R.id.gallery_card);
        final CardView campusMapCard = (CardView) view.findViewById(R.id.campus_map_card);
        final CardView reachCampusCard = (CardView) view.findViewById(R.id.reach_campus_card);
        final CardView committeeCard = (CardView) view.findViewById(R.id.committe_card);
        galleryCard.setScaleX(0.0f);
        galleryCard.setScaleY(0.0f);
        campusMapCard.setScaleX(0.0f);
        campusMapCard.setScaleY(0.0f);
        reachCampusCard.setScaleX(0.0f);
        reachCampusCard.setScaleY(0.0f);

        Handler handler = new Handler();

        reachCampusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=19.022380, 72.855647"));
                startActivity(intent);
            }
        });

        campusMapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
            }
        });

        galleryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkAvailable()){
                    Snackbar.make(view.findViewById(R.id.container), "No Internet Connection", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getActivity(), GalleryActivity.class);
                startActivity(i);
            }
        });

        committeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFragment(Constants.COMMITTEE_FRAGMENT);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reachCampusCard.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(200)
                        .setInterpolator(new OvershootInterpolator(0.2f))
                        .start();
            }
        }, 200);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                campusMapCard.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(200)
                        .setInterpolator(new OvershootInterpolator(0.2f))
                        .start();
            }
        }, 400);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                galleryCard.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(200)
                        .setInterpolator(new OvershootInterpolator(0.2f))
                        .start();
            }
        }, 600);
        ImageView imageView = (ImageView) view.findViewById(R.id.reach_campus_image);
        Glide.with(this)
                .load(R.drawable.compass)
                .fitCenter()
                .crossFade()
                .into(imageView);
        ImageView campusImageView = (ImageView) view.findViewById(R.id.campus_image);
        Glide.with(this)
                .load(R.drawable.location)
                .fitCenter()
                .crossFade()
                .into(campusImageView);
        ImageView galleryImage = (ImageView) view.findViewById(R.id.gallery_image);
        Glide.with(this)
                .load(R.drawable.gallery_icon)
                .fitCenter()
                .crossFade()
                .into(galleryImage);
        ImageView committeImage = (ImageView) view.findViewById(R.id.committe_image);
        Glide.with(this)
                .load(R.drawable.committee)
                .fitCenter()
                .crossFade()
                .into(committeImage);
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
