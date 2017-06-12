package com.android.xuanhong.tryonglasses.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.xuanhong.tryonglasses.R;

public class Welcome extends Activity {

    Button btnLoginEmail, btnLoginFace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        btnLoginEmail = (Button) findViewById(R.id.btnLoginEmail);
        btnLoginFace = (Button) findViewById(R.id.btnLoginFace);


        btnLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit application")
                .setMessage("Are you sure you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}