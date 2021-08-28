package com.khalyd.cecyteapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.khalyd.cecyteapp.FragmentsToLocate.ToLocate_Nacional;
import com.khalyd.cecyteapp.FragmentsToLocate.ToLocate_State;


public class ToLocateAdapter extends FragmentStatePagerAdapter {

    private int numberTabs; //es el entero de las tabs

    public ToLocateAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ToLocate_State();
            case 1:
                return new ToLocate_Nacional();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberTabs;
    }

}
