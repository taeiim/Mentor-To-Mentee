package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.parktaeim.mentor_to_mentee.R;

/**
 * Created by parktaeim on 2018. 5. 23..
 */

public class MentoringDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentoring_detail);

        ImageView backIcon = (ImageView) findViewById(R.id.mentoringDetail_backIcon);
        backIcon.setOnClickListener(v->finish());
    }
}
