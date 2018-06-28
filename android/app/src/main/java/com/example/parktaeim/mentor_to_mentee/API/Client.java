package com.example.parktaeim.mentor_to_mentee.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if(retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // 서버에서 날리는 json 응답을 변환
                    .build();
        }

        return retrofit;
    }
}
