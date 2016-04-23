package com.android.contactmap.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.contactmap.ui.ContactsFragment;
import com.android.contactmap.ui.MapsFragment;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{


    private String tabTitles[] = new String[] { "ALL CONTACTS", "MAP"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = null;
        switch (index){
            case 0:
                fragment =  new ContactsFragment();
                break;
            case 1:
                fragment =  new MapsFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }



}
