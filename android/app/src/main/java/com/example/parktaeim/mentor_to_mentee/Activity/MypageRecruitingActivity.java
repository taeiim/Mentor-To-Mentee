package com.example.parktaeim.mentor_to_mentee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.parktaeim.mentor_to_mentee.Adapter.MypageRecruitingAdapter;
import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;
import com.example.parktaeim.mentor_to_mentee.RecyclerViewClickListener;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 5. 2..
 */

public class MypageRecruitingActivity extends AppCompatActivity {
    private ArrayList<LectureItem> recruitingArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_recruiting);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recruiting_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<23;i++) {
            recruitingArrayList.add(new LectureItem("안드로이드 개발", "ㅇ니러ㅏㅇ니ㅓㄹ", 6, 49, "2018.02.34"));
        }

        MypageRecruitingAdapter adapter = new MypageRecruitingAdapter(recruitingArrayList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MypageRecruitingActivity.this,MypageRecruitingDetailActivity.class);
                intent.putExtra("LectureID",123);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}
