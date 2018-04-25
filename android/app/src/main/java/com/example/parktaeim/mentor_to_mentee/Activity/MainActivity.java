package com.example.parktaeim.mentor_to_mentee.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.parktaeim.mentor_to_mentee.Adapter.MyPagerAdapter;
import com.example.parktaeim.mentor_to_mentee.Fragment.AllMentoringFragment;
import com.example.parktaeim.mentor_to_mentee.Fragment.MenteeFragment;
import com.example.parktaeim.mentor_to_mentee.Fragment.MentorFragment;
import com.example.parktaeim.mentor_to_mentee.Fragment.MypageFragment;
import com.example.parktaeim.mentor_to_mentee.R;

public class MainActivity extends AppCompatActivity {
    public static AHBottomNavigation bottomTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabLayout();
    }

    private void setTabLayout() {
        bottomTabLayout = (AHBottomNavigation) findViewById(R.id.main_tabLayout);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("멘토링찾기", R.drawable.ic_list);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("내가 멘토", R.drawable.ic_mentor);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("내가 멘티", R.drawable.ic_book);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("마이페이지", R.drawable.ic_mypage);

        bottomTabLayout.addItem(item1);
        bottomTabLayout.addItem(item2);
        bottomTabLayout.addItem(item3);
        bottomTabLayout.addItem(item4);

        bottomTabLayout.setAccentColor(Color.parseColor("#FF8C00"));
        bottomTabLayout.setInactiveColor(Color.parseColor("#c4c4c4"));
        bottomTabLayout.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomTabLayout.setCurrentItem(0);
        bottomTabLayout.setTitleTextSizeInSp(9,8);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllMentoringFragment()).commit();
        bottomTabLayout.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllMentoringFragment()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MentorFragment()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenteeFragment()).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MypageFragment()).commit();
                        break;
                }
                return true;
            }
        });
        bottomTabLayout.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

}
