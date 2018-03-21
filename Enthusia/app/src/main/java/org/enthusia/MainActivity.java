package org.enthusia;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ramotion.foldingcell.FoldingCell;

import org.enthusia.fragments.AboutUsFragment;
import org.enthusia.fragments.CommitteeFragment;
import org.enthusia.fragments.DHFragment;
import org.enthusia.fragments.DepartmentCupFragment;
import org.enthusia.fragments.EventFragment;
import org.enthusia.fragments.NewsFeedFragment;
import org.enthusia.fragments.SponsorsFragment;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.enthusia.Constants.ABOUT_US_FRAGMENT;
import static org.enthusia.Constants.COMMITTEE_FRAGMENT;
import static org.enthusia.Constants.CUP_FRAGMENT;
import static org.enthusia.Constants.DH_FRAGMENT;
import static org.enthusia.Constants.EVENTS_FRAGMENT;
import static org.enthusia.Constants.FEED_FRAGMENT;
import static org.enthusia.Constants.SPONSORS_FRAGMENT;


public class MainActivity extends AppCompatActivity{


    boolean aboutUsBack = false;

    boolean yes = false;
    Handler hander = new Handler();

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#111111"));
        bottomNavigation.setAccentColor(Color.parseColor("#000099"));
        bottomNavigation.setInactiveColor(Color.parseColor("#999999"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(!wasSelected){
                    changeFragment(position);
                    bottomNavigation.setCurrentItem(position, false);
                }
                return false;
            }
        });
        AHBottomNavigationItem events =
                new AHBottomNavigationItem("Events", R.drawable.soccer);
        AHBottomNavigationItem aboutUs =
                new AHBottomNavigationItem("About Us", R.drawable.info);
        AHBottomNavigationItem departmentCup =
                new AHBottomNavigationItem("Cup", R.drawable.trophy);
        AHBottomNavigationItem sponsors =
                new AHBottomNavigationItem("Sponsors", R.drawable.deal);
        AHBottomNavigationItem feed =
                new AHBottomNavigationItem("Feed", R.drawable.rss);

        bottomNavigation.addItem(events);
        bottomNavigation.addItem(departmentCup);
        bottomNavigation.addItem(aboutUs);
        bottomNavigation.addItem(feed);
        bottomNavigation.addItem(sponsors);
        if (savedInstanceState == null) {
            changeFragment(EVENTS_FRAGMENT);
        }
    }
    @Override
    public void onBackPressed() {
        if(aboutUsBack){
            changeFragment(ABOUT_US_FRAGMENT);
            aboutUsBack = false;
            return;
        }
        if(yes){
            super.onBackPressed();
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }
        else{
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            hander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    yes = false;
                }
            }, 1500);
            yes = true;
        }
    }
    public void changeFragment(int id) {
        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit);
        switch (id){
            case EVENTS_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new EventFragment());
                break;
            case ABOUT_US_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new AboutUsFragment());
                break;
            case COMMITTEE_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new CommitteeFragment());
                aboutUsBack = true;
                break;
            case SPONSORS_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new SponsorsFragment());
                break;
            case DH_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new DHFragment());
                aboutUsBack = true;
                break;
            case CUP_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new DepartmentCupFragment());
                break;
            case FEED_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container, new NewsFeedFragment());
                break;
        }
        fragmentTransaction.commit();
    }
    public void launchDialer(View v){
        TextView textView = (TextView) v;
        String number = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
    public void toggle(View v){
        FoldingCell foldingCell = (FoldingCell)v;
        foldingCell.toggle(false);
    }
    public void register(View v){
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSfhSAn2V0okNI7yz2ij_JjgPhTmdmof2NaH1359e8rvt7ZnsA/viewform";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void openLink(View v){
        try{
            TextView textView = (TextView)v;
            String a = textView.getText().toString();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(a));
            startActivity(i);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void openTwitter(View view) {
        String url = "https://twitter.com/enthusiavjti";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void openInsta(View view) {
        String url = "https://www.instagram.com/enthusiavjti/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void openFb(View view) {
        String url = "https://facebook.com/enthusia.vjti";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
