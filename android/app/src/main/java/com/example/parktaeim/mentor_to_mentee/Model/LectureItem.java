package com.example.parktaeim.mentor_to_mentee.Model;

/**
 * Created by parktaeim on 2018. 4. 25..
 */

public class LectureItem {
    private String lectureTitle;
    private String lectureDesc;
    private int lectureEntryCnt;
    private int lectureHeartCnt;
    private String lectureDeadLine;

    public LectureItem(String lectureTitle, String lectureDesc, int lectureEntryCnt, int lectureHeartCnt, String lectureDeadLine) {
        this.lectureTitle = lectureTitle;
        this.lectureDesc = lectureDesc;
        this.lectureEntryCnt = lectureEntryCnt;
        this.lectureHeartCnt = lectureHeartCnt;
        this.lectureDeadLine = lectureDeadLine;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getLectureDesc() {
        return lectureDesc;
    }

    public void setLectureDesc(String lectureDesc) {
        this.lectureDesc = lectureDesc;
    }

    public int getLectureEntryCnt() {
        return lectureEntryCnt;
    }

    public void setLectureEntryCnt(int lectureEntryCnt) {
        this.lectureEntryCnt = lectureEntryCnt;
    }

    public int getLectureHeartCnt() {
        return lectureHeartCnt;
    }

    public void setLectureHeartCnt(int lectureHeartCnt) {
        this.lectureHeartCnt = lectureHeartCnt;
    }

    public String getLectureDeadLine() {
        return lectureDeadLine;
    }

    public void setLectureDeadLine(String lectureDeadLine) {
        this.lectureDeadLine = lectureDeadLine;
    }
}
