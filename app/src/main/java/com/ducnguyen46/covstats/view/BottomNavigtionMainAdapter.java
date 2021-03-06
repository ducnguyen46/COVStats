package com.ducnguyen46.covstats.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ducnguyen46.covstats.view.countryfragment.CountryFragment;
import com.ducnguyen46.covstats.view.educationfragemnt.EducationFragment;
import com.ducnguyen46.covstats.view.homefragment.HomeFragment;
import com.ducnguyen46.covstats.view.newsfragment.NewsFragment;
import com.ducnguyen46.covstats.view.uschartfragment.ChartFragment;

public class BottomNavigtionMainAdapter extends FragmentStatePagerAdapter {
    public BottomNavigtionMainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new CountryFragment();
            case 2:
                return new EducationFragment();
            case 3:
                return new NewsFragment();
            case 4:
                return new ChartFragment();

            default:
                return new HomeFragment();
        }
    }



    @Override
    public int getCount() {
        return 5;
    }
}
