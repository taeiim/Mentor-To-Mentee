package com.example.parktaeim.mentor_to_mentee.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 6. 15..
 */

public class MypageRecruitingDetailAdapter extends RecyclerView.Adapter<MypageRecruitingDetailAdapter.ViewHolder> {
    private ArrayList<String> personList = new ArrayList<>();

    public MypageRecruitingDetailAdapter(ArrayList<String> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MypageRecruitingDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recruitingdetail_personlist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MypageRecruitingDetailAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
