package com.android.xuanhong.tryonglasses.model.callback;

import com.android.xuanhong.tryonglasses.model.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Pinky on 01-Jun-17.
 */

public interface UserService {
    @GET("/api/user")
    Call<List<User>> getAllUser();


}
