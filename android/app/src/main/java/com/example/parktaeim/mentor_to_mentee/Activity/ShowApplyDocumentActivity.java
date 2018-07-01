package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.parktaeim.mentor_to_mentee.R;

/**
 * Created by parktaeim on 2018. 6. 19..
 */

public class ShowApplyDocumentActivity extends AppCompatActivity {
    private Button refuseBtn;
    private Button okBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_apply_document);

        refuseBtn.setOnClickListener(v->{

        });

        okBtn.setOnClickListener(v->{

        });
    }
}
