package com.mangnaik.yogesh.enthusia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private int currentPart = R.id.nav_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("homepart", "home");
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    public void openBrowser(View view){
        String url = "http://www.google.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        System.out.println(currentPart);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                super.onBackPressed();
            }
        }
        System.out.println(R.id.nav_home);
        if(currentPart!=R.id.nav_home){
            HomeFragment fragment = new HomeFragment();
            Bundle bundle = new Bundle();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            bundle.putString("homepart", "home");
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            setTitle("Home");
            currentPart=R.id.nav_home;
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sign_up) {
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);
        }

        if(id == R.id.action_login){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(int id){
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (id){
            case R.id.nav_events:
                currentPart = R.id.nav_events;
                bundle.putString("homepart", "event");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Events");
                break;
            case R.id.nav_commitee:
                currentPart = R.id.nav_commitee;
                bundle.putString("homepart", "commitee");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Home");
                break;
            case R.id.nav_gallery:
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(i);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        currentPart = item.getItemId();
        switch (id){
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.whatsapp&hl=en");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.nav_events:
                bundle.putString("homepart", "event");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Events");
                break;

            case R.id.nav_home:
                bundle.putString("homepart", "home");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Home");
                break;
            case R.id.nav_commitee:
                bundle.putString("homepart", "commitee");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Home");
                break;
            case R.id.nav_gallery:
//                bundle.putString("homepart", "gallery");
//                fragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//                setTitle("Gallery");
//                break;
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(i);
                break;
            case R.id.nav_sponsors:
                bundle.putString("homepart", "sponsors");
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                setTitle("Sponsors");
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void launchDialer(View v){
        TextView textView = (TextView) v;
        String number = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
}
