package com.example.parktaeim.mentor_to_mentee;

public class ServerCode {
    //회원가입 체크
    public static final int SIGNUP_RES_SUCCESS = 201;
    public static final int SIGNUP_RES_OVERLAP = 204; //중복

    //로그인 체크
    public static final int SIGNIN_RES_SUCCESS = 200;
    public static final int SIGNIN_RES_NO_ACCOUNT = 204;

    //중복 체크
    public static final int OVERLAP_SUCCESS = 200; //중복 안됨
    public static final int OVERLAP_FAILED = 409; //중복됨
    public static final int OVERLAP_SERER_ERROR = 500; //서버 에러

    //멘토링생성
    public static final int CREATE_MENTORING_SUCCESS = 201; // 성공
    public static final int CREATE_MENTORING_FAILED = 400; //멘토링 생성 실패
    public static final int CREATE_MENTORING_SERER_ERROR = 500; // 서버 에러
}
