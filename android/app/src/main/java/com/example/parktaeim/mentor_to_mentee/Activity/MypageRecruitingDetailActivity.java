package com.example.parktaeim.mentor_to_mentee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.parktaeim.mentor_to_mentee.Adapter.MypageRecruitingDetailAdapter;
import com.example.parktaeim.mentor_to_mentee.R;
import com.example.parktaeim.mentor_to_mentee.RecyclerViewClickListener;

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

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MypageRecruitingDetailActivity.this, ShowApplyDocumentActivity.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}
