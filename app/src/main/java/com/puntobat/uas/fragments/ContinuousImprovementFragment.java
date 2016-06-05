package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puntobat.uas.R;
import com.puntobat.uas.components.CoursesComponent;
import com.puntobat.uas.components.ImprovementPlansComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.ImprovementPlan;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class ContinuousImprovementFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_cont_improv, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.cont_improv_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.cont_improv_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return rootView;

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new ImprovementPlansFragment();
                case 1: return new SuggestionsFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.uas_cont_improv_sugg_tab);
                case 1:
                    return getResources().getString(R.string.uas_cont_improv_plans_tab);
            }
            return null;
        }
    }

    public static class ImprovementPlansFragment extends Fragment {

        private LinearLayout auxiliar;

        public ImprovementPlansFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_improvement_plans, container, false);

            auxiliar= (LinearLayout) rootView.findViewById(R.id.fragment_improvement_plans_linear);

            ArrayList<ImprovementPlan> listCoursesAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).IMPROVEMENTPLANS;

            for (ImprovementPlan improvementPlan : listCoursesAux) {

                ImprovementPlansComponent impPlansComponent = new ImprovementPlansComponent(getActivity(), improvementPlan);
                auxiliar.addView(impPlansComponent);

            }

            return rootView;
        }
    }

    public static class SuggestionsFragment extends Fragment {

        public SuggestionsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_suggestions, container, false);

            return rootView;
        }
    }
}
