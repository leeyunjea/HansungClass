package org.androidtown.hansungclass.Adapter;

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
    private HomeFragment homeFragment = new HomeFragment();
    private EnrollmentFragment enrollmentFragment = new EnrollmentFragment();
    private TableFragment tableFragment = new TableFragment();
    private LocationFragment locationFragment = new LocationFragment();

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return enrollmentFragment;
            case 2:
                return tableFragment;
            case 3:
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
