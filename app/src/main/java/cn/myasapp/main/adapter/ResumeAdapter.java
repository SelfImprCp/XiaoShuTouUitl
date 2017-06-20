package cn.myasapp.main.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.myasapp.main.fragment.TestViewPageFragment;
import cn.myasapp.main.fragment.TestViewPageFragment2;
import cn.myasapp.main.fragment.TestViewPageFragment3;


/**
 * Created by Jerry on 2017/6/20.
 */

public class ResumeAdapter extends FragmentPagerAdapter {

    public ResumeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (0 == position) {
            fragment = new TestViewPageFragment();
        } else if (1 == position) {
            fragment = new TestViewPageFragment2();
        } else if (2 == position) {
            fragment = new TestViewPageFragment3();
        } else if (3 == position) {
            fragment = new TestViewPageFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TAB 1";
            case 1:
                return "TAB 2";
            case 2:
                return "TAB 3";
            case 3:
                return "TAB 4";
        }
        return null;
    }
}