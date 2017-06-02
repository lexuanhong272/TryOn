package com.android.xuanhong.tryonglasses.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.MainActivity;
import com.android.xuanhong.tryonglasses.MainScreen;
import com.android.xuanhong.tryonglasses.R;
import com.android.xuanhong.tryonglasses.controller.RestManager;
import com.android.xuanhong.tryonglasses.model.Glasses;
import com.android.xuanhong.tryonglasses.model.User;
import com.android.xuanhong.tryonglasses.model.callback.UserService;
import com.android.xuanhong.tryonglasses.util.GlassesGallery2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginEmail extends Activity {

    EditText edtEmail, edtPassword;
    Button btnLoginEmail;
    private RestManager mRestManager;

    List<User> userList;

    public static int IDUSER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLoginEmail = (Button) findViewById(R.id.btnLoginEmail);

        mRestManager = new RestManager();

        Call<List<User>> listCall = mRestManager.getUserService().getAllUser();

        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginEmail.this, "Get user successfully", Toast.LENGTH_SHORT).show();
                    userList = response.body();

                    for(int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);

                        byte[] bytearrayMTL = Base64.decode(user.getAvatar(), Base64.DEFAULT);
                        File root1 = android.os.Environment.getExternalStorageDirectory();
                        File dir1 = new File(root1.getAbsolutePath() + "/DCIM/AVATAR/");
                        dir1.mkdirs();
                        File file1 = new File(dir1, (i+1) + ".jpg");
                        try {
                            FileOutputStream f = new FileOutputStream(file1);
                            f.write(bytearrayMTL);
                            Toast.makeText(LoginEmail.this, "Saved avatar " + user.getId(), Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginEmail.this, "fffffff", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginEmail.this, "fffffffdddd", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {
                    //Toast.makeText(LoginEmail.this, "Error on get user", Toast.LENGTH_SHORT).show();
                    int sc = response.code();
                    switch (sc){
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //Toast.makeText(LoginEmail.this, "Fail to get user", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < userList.size(); i++){
                    if(edtEmail.getText().toString().equals(userList.get(i).getEmail()) == true){
                        if(edtPassword.getText().toString().equals(userList.get(i).getPassword()) == true){
                            Toast.makeText(LoginEmail.this, "Log in success", Toast.LENGTH_SHORT).show();
                            IDUSER = i + 1;
                            LoginEmail.this.startActivity(new Intent(LoginEmail.this.getApplicationContext(), Navigation.class));
                            LoginEmail.this.finish();
                            Toast.makeText(LoginEmail.this, "IDUSER = " + IDUSER, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //Toast.makeText(LoginEmail.this, "Log in fail", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginEmail.this.startActivity(new Intent(LoginEmail.this.getApplicationContext(), Welcome.class));
        LoginEmail.this.finish();
    }
}
