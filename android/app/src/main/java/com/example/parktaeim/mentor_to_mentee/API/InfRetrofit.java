package com.example.parktaeim.mentor_to_mentee.API;

import com.example.parktaeim.mentor_to_mentee.Model.JWTModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface InfRetrofit {
    @FormUrlEncoded
    @POST(Url.SIGN_IN_URL)
    Call<JWTModel> signIn(
            @Field("id") String id,
            @Field("pwd") String pwd
    );

    @FormUrlEncoded
    @POST(Url.SIGN_UP_URL)
    Call<Void> signUp(
            @Field("id") String id,
            @Field("pwd") String pwd,
            @Field("name") String name,
            @Field("age") int age,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST(Url.SIGN_UP_OVERLAP_URL)
    Call<Void> idOverlap(
            @Query("id") String id
    );

    @GET(Url.REQUEST_TOKEN)
    Call<JWTModel> refreshToken(
            @Field("refreshToken") String refreshToken
    );

    @FormUrlEncoded
    @POST(Url.CREATE_MENTORING)
    Call<Void> createMentoring(
            @Header("authorization") String authorization,
            @Field("title") String title,
            @Field("info") String info,
            @Field("category") String category,
            @Field("max_menber") int max_member,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date,
            @Field("questions") ArrayList<String> questions
    );

    @Multipart
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);
}
