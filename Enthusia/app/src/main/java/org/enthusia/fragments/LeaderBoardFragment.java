package org.enthusia.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;



import org.enthusia.Constants;
import org.enthusia.R;
import org.enthusia.adapter.LeaderboardAdapter;
import org.enthusia.adapter.NewsFeedAdapter;
import org.enthusia.model.Department;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderBoardFragment extends android.support.v4.app.Fragment {

//    private int[] ids = new int[]{R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
//            R.id.tv6, R.id.tv7,R.id.tv8,R.id.tv9,R.id.tv10,R.id.tv11};
//    private int[] nameIds = new int[]{R.id.name1,R.id.name2,R.id.name3,R.id.name4,R.id.name5,R.id.name6,R.id.name7,R.id.name8,R.id.name9
//            ,R.id.name10,R.id.name11};

    public LeaderBoardFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String ans = "";

                if(!isNetworkAvailable()){
                    try{
                        Snackbar.make(view.findViewById(R.id.cont), "No Internet Connection", Snackbar.LENGTH_SHORT).show();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    return;
                }

                if(Constants.leaderboard.equals("")){
                    try {
                        URL url = new URL("http://www.enthusia.org/leaderboard.txt");
                        String str;
                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        while ((str = in.readLine()) != null) {
                            ans += str;
                        }
                        in.close();
                        Constants.leaderboard = ans;
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                else{
                    ans = Constants.leaderboard;
                }
                final String a = ans;
                ans.trim();
                final String[] p = ans.split("%");
                if(ans.length() == 0)
                    return;
                final List<Department> list = new ArrayList<>();
                for(int i=0; i<p.length; i++){
                    String q = p[i].split("#")[0];
                    String x = p[i].split("#")[1].trim();
                    list.add(new Department(Integer.parseInt(q), x));
                }
                Collections.sort(list, new Comparator<Department>() {
                    @Override
                    public int compare(Department o1, Department o2) {
                        return o2.points - o1.points;
                    }
                });
                final LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(list);
                try{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(a.length() == 0)
                                return;
                            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
                            recyclerView.setHasFixedSize(true);
                            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(llm);

                            recyclerView.setAdapter(leaderboardAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
                            progressBar.setIndeterminate(false);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String ans = "";
                        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
                        final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                                progressBar.setIndeterminate(true);
                                recyclerView.setVisibility(View.GONE);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        if(!isNetworkAvailable()){
                            Snackbar.make(view.findViewById(R.id.cont), "No Internet Connection", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            URL url = new URL("http://www.enthusia.org/leaderboard.txt");
                            String str;
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            while ((str = in.readLine()) != null) {
                                ans += str;
                            }
                            in.close();
                            Constants.leaderboard = ans;
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        final String a = ans;
                        final String[] p = ans.split("%");
                        if(ans.length() == 0)
                            return;
                        final List<Department> list = new ArrayList<>();
                        System.out.println(p.length);
                        for(int i=0; i<p.length; i++){
                            String q = p[i].split("#")[0];
                            String x = p[i].split("#")[1].trim();
                            list.add(new Department(Integer.parseInt(q), x));
                        }
                        Collections.sort(list, new Comparator<Department>() {
                            @Override
                            public int compare(Department o1, Department o2) {
                                return o2.points - o1.points;
                            }
                        });
                        final LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(list);
                        try{
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(a.length() == 0)
                                        return;
                                    recyclerView.setHasFixedSize(true);
                                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(llm);

                                    recyclerView.setAdapter(leaderboardAdapter);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    progressBar.setIndeterminate(false);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}