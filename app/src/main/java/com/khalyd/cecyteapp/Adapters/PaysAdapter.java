package com.khalyd.cecyteapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.khalyd.cecyteapp.FragmentsSubjects.Description;
import com.khalyd.cecyteapp.FragmentsSubjects.Foro;
import com.khalyd.cecyteapp.FragmentsSubjects.Resources;


public class PaysAdapter extends FragmentStatePagerAdapter {

    private int tabsOnline;

    public PaysAdapter(FragmentManager fm, int tabsOnline) {
        super(fm);
        this.tabsOnline = tabsOnline;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Resources();
            case 1:
                return new Description();
            case 2:
                return new Foro();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsOnline;
    }
}
