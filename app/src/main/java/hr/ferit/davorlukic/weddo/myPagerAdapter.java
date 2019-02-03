package hr.ferit.davorlukic.weddo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



public class myPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 5;

    public myPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PersonFragment.newInstance();

            case 1:
                return GroupFragment.newInstance("band");

            case 2:
                return GroupFragment.newInstance("flower");

            case 3:
                return GroupFragment.newInstance("hall");

            default:
                return GroupFragment.newInstance("photo");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Guests";
            case 1:
                return "Band";
            case 2:
                return "Flower";
            case 3:
                return "Hall";
            default:
                return "Photo";
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
