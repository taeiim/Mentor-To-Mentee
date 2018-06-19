package com.example.parktaeim.mentor_to_mentee.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 5. 9..
 */

public class MypageRecruitingAdapter extends RecyclerView.Adapter<MypageRecruitingAdapter.ViewHolder> {
    private ArrayList<LectureItem> recruitingArrayList = new ArrayList<>();

    public MypageRecruitingAdapter(ArrayList<LectureItem> recruitingArrayList) {
        this.recruitingArrayList = recruitingArrayList;
    }

    @NonNull
    @Override
    public MypageRecruitingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recruiting_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MypageRecruitingAdapter.ViewHolder holder, int position) {
        holder.titleTv.setText(recruitingArrayList.get(position).getLectureTitle());
        holder.descTv.setText(recruitingArrayList.get(position).getLectureDesc());
        holder.enrollNumTv.setText(String.valueOf(recruitingArrayList.get(position).getLectureEntryCnt())+"명");
        holder.deadLineTv.setText(recruitingArrayList.get(position).getLectureDeadLine());
    }

    @Override
    public int getItemCount() {
        return recruitingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView descTv;
        private TextView deadLineTv;
        private TextView enrollNumTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.recruitingLecture_titleTv);
            descTv = (TextView) itemView.findViewById(R.id.recruitingLecture_descTv);
            deadLineTv = (TextView) itemView.findViewById(R.id.recruitingLecture_deadLineTv);
            enrollNumTv = (TextView) itemView.findViewById(R.id.recruitingLecture_enrollNumTv);

        }
    }
}
