package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.xuanhong.tryonglasses.util.GlassesGallery2;

public class MainScreen extends Activity {

    Button btnEditProfile;
    Button btnLogout;
    Button btnGlasses;
    Button btnPictures;
    Button btnCarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnGlasses = (Button) findViewById(R.id.btnGlasses);
        btnPictures = (Button) findViewById(R.id.btnPictures);
        btnCarts = (Button) findViewById(R.id.btnCarts);


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.this.startActivity(new Intent(MainScreen.this.getApplicationContext(), EditProfile.class));
                MainScreen.this.finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.this.startActivity(new Intent(MainScreen.this.getApplicationContext(), MainActivity.class));
                MainScreen.this.finish();
            }
        });

        btnCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.this.startActivity(new Intent(MainScreen.this.getApplicationContext(), Carts.class));
                MainScreen.this.finish();
            }
        });

        btnGlasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.this.startActivity(new Intent(MainScreen.this.getApplicationContext(), GlassesGallery.class));
                MainScreen.this.finish();
            }
        });

        btnPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.this.startActivity(new Intent(MainScreen.this.getApplicationContext(), Pictures.class));
                MainScreen.this.finish();


            }
        });
    }
}
