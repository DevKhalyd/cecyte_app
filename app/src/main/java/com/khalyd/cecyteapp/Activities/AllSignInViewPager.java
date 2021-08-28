package com.khalyd.cecyteapp.Activities;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.khalyd.cecyteapp.FragmentsSignInViewPager.DirectivoSignInFragmen;
import com.khalyd.cecyteapp.FragmentsSignInViewPager.ParentSignInFragment;
import com.khalyd.cecyteapp.FragmentsSignInViewPager.StudentSignInFragment;
import com.khalyd.cecyteapp.R;


public class AllSignInViewPager extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_all_sign_in_view_pager);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager =  findViewById(R.id.containerViewPager);


        mViewPager.setAdapter(mSectionsPagerAdapter);



    }




    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {

            Fragment fragment  = null;

            switch (sectionNumber){

                case 1:
                    fragment = new StudentSignInFragment();
                    break;
                case 2:
                    fragment = new ParentSignInFragment();
                    break;
                case 3:
                    fragment = new DirectivoSignInFragmen();
                    break;

            }
            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_all_sign_in_view_pager, container, false);

            return rootView;

        }

        //Al paracer se puede meter un fragment dentro de un actvity

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);

            //Aqui llama a los fragmentos
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
