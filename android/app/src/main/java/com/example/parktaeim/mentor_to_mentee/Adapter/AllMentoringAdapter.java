package com.example.parktaeim.mentor_to_mentee.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.parktaeim.mentor_to_mentee.Model.LectureItem;
import com.example.parktaeim.mentor_to_mentee.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 4. 25..
 */

public class AllMentoringAdapter extends RecyclerView.Adapter<AllMentoringAdapter.ViewHolder> {
    private ArrayList<LectureItem> lectureItemArrayList = new ArrayList<>();
    private Context context;
    private ScaleAnimation scaleAnimation;

    public AllMentoringAdapter(ArrayList<LectureItem> lectureItemArrayList, Context context) {
        this.lectureItemArrayList = lectureItemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allmentoring_lecture,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.cardView.setPreventCornerOverlap(false);
        holder.heartLayout.setOnClickListener(v-> holder.heartBtn.startAnimation(heartBtnAnimation()));
        holder.heartBtn.setOnClickListener(v-> holder.heartBtn.startAnimation(heartBtnAnimation()));
    }

    @Override
    public int getItemCount() {
        return lectureItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgView;
        TextView titleTv;
        TextView descTv;
        TextView entryPeoCntTv;
        TextView heartCntTv;
        ToggleButton heartBtn;
        LinearLayout heartLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.lecture_cardView);
            imgView = (ImageView) itemView.findViewById(R.id.lecture_imgView);
            titleTv = (TextView) itemView.findViewById(R.id.lecture_titleTv);
            descTv = (TextView) itemView.findViewById(R.id.lecture_descTv);
            entryPeoCntTv = (TextView) itemView.findViewById(R.id.lecture_entryPeoCntTv);
            heartCntTv = (TextView) itemView.findViewById(R.id.lecture_heartCnt);
            heartBtn = (ToggleButton) itemView.findViewById(R.id.lecture_heartBtn);
            heartLayout = (LinearLayout) itemView.findViewById(R.id.lecture_heartBtnLayout);

        }
    }

    public ScaleAnimation heartBtnAnimation(){
        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        return scaleAnimation;
    }
}
