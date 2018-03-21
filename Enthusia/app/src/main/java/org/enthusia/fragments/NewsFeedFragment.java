package org.enthusia.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.enthusia.Constants;
import org.enthusia.R;
import org.enthusia.adapter.GalleryAdapter;
import org.enthusia.adapter.ImageAdapter;
import org.enthusia.adapter.NewsFeedAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NewsFeedFragment extends android.support.v4.app.Fragment {

    public NewsFeedFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(final LayoutInflater inflater, final @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        final RelativeLayout layout = (RelativeLayout)view.findViewById(R.id.container);
        final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String ans = "";
                if(!isNetworkAvailable()){
                    try{
                        Snackbar.make(view.findViewById(R.id.main_container), "No Internet Connection", Snackbar.LENGTH_SHORT).show();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    return;
                }
                if(Constants.news.equals("")){
                    try {
                        URL url = new URL("http://www.enthusia.org/news.txt");

                        String str;
                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        while ((str = in.readLine()) != null) {
                            ans += str;
                        }
                        in.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    Constants.news = ans;
                }
                ans = Constants.news;
                final String[] p = ans.split("%");
                final String[] news = new String[p.length];
                final String[] links = new String[p.length];
                for(int i=0; i<p.length; i++){
                    String aP = p[i];
                    String n,l;
                    if(aP.contains("#")){
                        n = aP.split("#")[0].trim();
                        l = aP.split("#")[1].trim();
                        news[i] = n;
                        links[i] = l;
                    }
                    else{
                        news[i] = aP.trim();
                        links[i] = "";
                    }
                }
                try{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
                            recyclerView.setHasFixedSize(true);
                            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(llm);

                            NewsFeedAdapter myAdapter = new NewsFeedAdapter(news, links, getActivity());
                            recyclerView.setAdapter(myAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setIndeterminate(false);
                            progressBar.setVisibility(View.GONE);
                            RelativeLayout linearLayout = (RelativeLayout)view.findViewById(R.id.main_container);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        t.start();
        return view;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
