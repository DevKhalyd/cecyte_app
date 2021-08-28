package com.khalyd.cecyteapp.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.khalyd.cecyteapp.FragmentsSignUpViewPagerRegistro.SignUpDirectivoFragmentVP;
import com.khalyd.cecyteapp.FragmentsSignUpViewPagerRegistro.SignUpParentsFragmentVP;
import com.khalyd.cecyteapp.FragmentsSignUpViewPagerRegistro.SignUpStudentsFragmentVP;
import com.khalyd.cecyteapp.R;

public class AllSignUpViewPagerRegistro extends AppCompatActivity {

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
        setContentView(R.layout.activity_all_sign_up_view_pager_registro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager =  findViewById(R.id.containerSignUp_ALL_Registro);

        mViewPager.setAdapter(mSectionsPagerAdapter);



    }




    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {


        }


        public static Fragment newInstance(int sectionNumber) {

            Fragment fragment = null;

            switch (sectionNumber) {

                case 1:
                    fragment = new SignUpStudentsFragmentVP();
                    break;
                case 2:
                    fragment = new SignUpParentsFragmentVP();
                    break;
                case 3:
                    fragment = new SignUpDirectivoFragmentVP();
                    break;

            }

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_all_sign_up_view_pager_registro, container, false);

            return rootView;
        }
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
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
