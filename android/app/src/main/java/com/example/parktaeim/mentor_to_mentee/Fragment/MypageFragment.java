package com.example.parktaeim.mentor_to_mentee.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.parktaeim.mentor_to_mentee.Activity.MypageRecruitingActivity;
import com.example.parktaeim.mentor_to_mentee.R;

/**
 * Created by parktaeim on 2018. 4. 25..
 */

public class MypageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypage,container,false);

        RelativeLayout goRecruitingLayout = (RelativeLayout) rootView.findViewById(R.id.mypage_intentRecruitingLayout);
        Intent recruitingIntent = new Intent(getActivity(),MypageRecruitingActivity.class);
        goRecruitingLayout.setOnClickListener(v->startActivity(recruitingIntent));

        return rootView;
    }
}
