package com.android.xuanhong.tryonglasses.controller;

import com.android.xuanhong.tryonglasses.model.callback.GlassesService;
import com.android.xuanhong.tryonglasses.model.callback.UserService;
import com.android.xuanhong.tryonglasses.model.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pinky on 25-May-17.
 */

public class RestManager {
    private GlassesService mGlassesService;

    public GlassesService getGlassesService() {
        if (mGlassesService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mGlassesService = retrofit.create(GlassesService.class);
        }
        return mGlassesService;
    }

    private UserService mUserService;

    public UserService getUserService() {
        if (mUserService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mUserService = retrofit.create(UserService.class);
        }
        return mUserService;
    }



}
