package cn.myasapp.main.ui;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cn.myasapp.main.R;
import cn.myasapp.main.adapter.ResumeAdapter;

/**
 * Created by Jerry on 2017/6/20.
 */

public class ResumeActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // ViewPager
        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        ResumeAdapter mPagerAdapter = new ResumeAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        // ViewPager切换时NestedScrollView滑动到顶部
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((NestedScrollView) findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}