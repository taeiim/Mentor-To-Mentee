package com.example.parktaeim.mentor_to_mentee.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parktaeim.mentor_to_mentee.R;

/**
 * Created by parktaeim on 2018. 4. 25..
 */

public class MentorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mentor,container,false);

        setUpCardView();

        return rootView;
    }

    private void setUpCardView() {

    }
}
