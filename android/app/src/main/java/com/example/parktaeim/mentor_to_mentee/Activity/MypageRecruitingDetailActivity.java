package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.parktaeim.mentor_to_mentee.Adapter.MypageRecruitingAdapter;
import com.example.parktaeim.mentor_to_mentee.Adapter.MypageRecruitingDetailAdapter;
import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 5. 9..
 */

public class MypageRecruitingDetailActivity extends AppCompatActivity {
    private ArrayList<String> personList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_recruiting_deatil);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recruitingPersonRecyclerView);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);

        for(int i=0;i<23;i++) {
            personList.add("dkf");
        }
        MypageRecruitingDetailAdapter adapter = new MypageRecruitingDetailAdapter(personList);
        recyclerView.setAdapter(adapter);

    }
}
