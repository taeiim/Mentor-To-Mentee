package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.example.parktaeim.mentor_to_mentee.Adapter.MyPagerAdapter;
import com.example.parktaeim.mentor_to_mentee.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    final String[] tabTitles = {};
    final Fragment[] fragments = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initTabPager();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //액션바로 설정, 또한 이는 기본 액션바가 존재하면 에러남. style.xml에서 노액션바해야됨

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정
        actionBar.setDisplayShowHomeEnabled(false); // 액션바 왼쪽의 <- 버튼

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.item_lecture, null);

        actionBar.setCustomView(actionbar);
    }
    private void initTabPager() {
        TabLayout tabs = findViewById(R.id.tabs);
//        for (int i = 0; i < days.length; i++) {
//            tabs.addTab(tabs.newTab().setText(days[i]));
//        }
        viewPager = findViewById(R.id.viewPager);
        // 탭 눌렀을때 해당 위치로 이동
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        MyPagerAdapter mpa = new MyPagerAdapter(getSupportFragmentManager()); // 뷰페이저 어댑터

        for(int i = 0 ; i < fragments.length ; i++) {
            mpa.addElement(tabTitles[i], fragments[i]);
        }
        viewPager.setAdapter(mpa);
        viewPager.setCurrentItem(0); // 첫탭을 기본으로 세팅
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs)); // 뷰페이저 이동했을때 해당 위치로 이동

        tabs.setupWithViewPager(viewPager);
    }
}
