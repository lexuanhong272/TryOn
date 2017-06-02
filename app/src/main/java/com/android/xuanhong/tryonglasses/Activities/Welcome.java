package com.android.xuanhong.tryonglasses.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.xuanhong.tryonglasses.EditProfile;
import com.android.xuanhong.tryonglasses.GlassesGallery;
import com.android.xuanhong.tryonglasses.LoginByEmail;
import com.android.xuanhong.tryonglasses.MainScreen;
import com.android.xuanhong.tryonglasses.R;

public class Welcome extends Activity {

    Button btnLoginEmail, btnLoginFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Welcome.this.startActivity(new Intent(Welcome.this.getApplicationContext(), Navigation.class));
        Welcome.this.finish();

//        Welcome.this.startActivity(new Intent(Welcome.this.getApplicationContext(), GlassesGallery.class));
//        Welcome.this.finish();

        btnLoginEmail = (Button) findViewById(R.id.btnLoginEmail);
        btnLoginFace = (Button) findViewById(R.id.btnLoginFace);

        btnLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Welcome.this.startActivity(new Intent(Welcome.this.getApplicationContext(), LoginFace.class));
                Welcome.this.finish();
            }
        });

        btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Welcome.this.startActivity(new Intent(Welcome.this.getApplicationContext(), LoginEmail.class));
                Welcome.this.finish();
            }
        });


    }
}
