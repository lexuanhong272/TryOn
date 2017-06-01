package com.android.xuanhong.tryonglasses.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.xuanhong.tryonglasses.R;

public class LoginFace extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_face);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginFace.this.startActivity(new Intent(LoginFace.this.getApplicationContext(), Welcome.class));
        LoginFace.this.finish();
    }
}
