package org.androidtown.hansungclass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.androidtown.hansungclass.ViewPager.EnrollmentFragment;
import org.androidtown.hansungclass.ViewPager.HomeFragment;
import org.androidtown.hansungclass.ViewPager.LocationFragment;
import org.androidtown.hansungclass.ViewPager.TableFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                EnrollmentFragment enrollmentFragment = new EnrollmentFragment();
                return enrollmentFragment;
            case 2:
                TableFragment tableFragment = new TableFragment();
                return tableFragment;
            case 3:
                LocationFragment locationFragment = new LocationFragment();
                return locationFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
