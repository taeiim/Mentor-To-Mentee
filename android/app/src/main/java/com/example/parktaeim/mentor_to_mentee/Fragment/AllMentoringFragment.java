package com.example.parktaeim.mentor_to_mentee.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.parktaeim.mentor_to_mentee.Adapter.MyPagerAdapter;
import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 4. 25..
 */

public class AllMentoringFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_allmentoring, container, false);
        setUpTabLayout();
        setUpSpinner();
        return rootView;
    }

    private void setUpSpinner() {
        Spinner spinner = (Spinner) rootView.findViewById(R.id.tab1_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setUpTabLayout() {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.allmentoring_viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addElement("전체", new AllMentoringRecyclerFragment());
        adapter.addElement("모바일", new AllMentoringRecyclerFragment());
        adapter.addElement("웹", new AllMentoringRecyclerFragment());
        adapter.addElement("데이터베이스", new AllMentoringRecyclerFragment());
        adapter.addElement("서버", new AllMentoringRecyclerFragment());
        adapter.addElement("보안", new AllMentoringRecyclerFragment());
        adapter.addElement("머신러닝", new AllMentoringRecyclerFragment());
        adapter.addElement("게임", new AllMentoringRecyclerFragment());
        adapter.addElement("네트워크", new AllMentoringRecyclerFragment());

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.allmentoring_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
