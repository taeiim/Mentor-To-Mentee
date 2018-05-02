package com.example.parktaeim.mentor_to_mentee.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.parktaeim.mentor_to_mentee.Adapter.AllMentoringAdapter;
import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 5. 2..
 */

public class AllMentoringRecyclerFragment extends Fragment {
    private View rootView;
    private ArrayList<LectureItem> lectureItemArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_allmentoring_recycler,container,false);
        setUpRecyclerView();


        return rootView;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.tab1_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<10;i++){
            lectureItemArrayList.add(new LectureItem("안드로이드 강의","ㄴ이ㅏ런이ㅏ러이나렁니ㅏ렁니ㅏ러",3,29));

        }

        AllMentoringAdapter adapter = new AllMentoringAdapter(lectureItemArrayList,getContext());
        recyclerView.setAdapter(adapter);
    }
}
