package org.enthusia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import org.enthusia.fragments.DHFragment;
import org.enthusia.fragments.LeaderBoardFragment;

/**
 * Created by Yogesh on 11/8/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new LeaderBoardFragment();
        return new DHFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Leaderboard";
            case 1:
                return "Department Managers";
            default:
                return null;
        }
    }
}
