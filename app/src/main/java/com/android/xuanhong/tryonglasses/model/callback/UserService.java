package com.android.xuanhong.tryonglasses.model.callback;

import com.android.xuanhong.tryonglasses.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pinky on 01-Jun-17.
 */

public interface UserService {
    @GET("/api/user")
    Call<List<User>> getAllUser();

}
