package org.enthusia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.enthusia.R;
import org.enthusia.adapter.PagerAdapter;

public class DepartmentCupFragment extends android.support.v4.app.Fragment   {

    protected PagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public DepartmentCupFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cup, container, false);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        mSectionsPagerAdapter = new PagerAdapter(manager);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }
}