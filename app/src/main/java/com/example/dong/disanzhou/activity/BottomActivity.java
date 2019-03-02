package com.example.dong.disanzhou.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.fragment.DingdanFragment;
import com.example.dong.disanzhou.fragment.GouwuFragment;
import com.example.dong.disanzhou.fragment.QuanziFragment;
import com.example.dong.disanzhou.fragment.ShouyeFragment;
import com.example.dong.disanzhou.fragment.WodeFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ViewPager pager;
    private List<Fragment> list;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(4);
                    return true;
                case R.id.navigation_dashboard:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notificationss:
                    pager.setCurrentItem(3);
                    return true;
                case R.id.navigation_notificationsss:
                    pager.setCurrentItem(0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        pager=findViewById(R.id.pager);
        mTextMessage = (TextView) findViewById(R.id.message);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        list=new ArrayList<>();
        list.add(new ShouyeFragment());
        list.add(new QuanziFragment());
        list.add(new GouwuFragment());
        list.add(new DingdanFragment());
        list.add(new WodeFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_notificationss);
                        break;
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_notificationsss);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
