package com.pmm.triedenieodpadukosice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Monika on 06-Apr-16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag=new PapierFragment();
                break;
            case 1:
                frag=new SkloFragment();
                break;
            case 2:
                frag=new PlastKovFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Papier";
                break;
            case 1:
                title="Sklo";
                break;
            case 2:
                title="Plast + kov";
                break;
        }

        return title;
    }
}
