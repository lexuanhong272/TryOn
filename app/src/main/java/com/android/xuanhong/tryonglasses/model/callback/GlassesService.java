package com.android.xuanhong.tryonglasses.model.callback;

import com.android.xuanhong.tryonglasses.model.Glasses;
import com.android.xuanhong.tryonglasses.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pinky on 25-May-17.
 */

public interface GlassesService {
    @GET("/api/glassesinfo")
    Call<List<Glasses>> getAllGlasses();



}
