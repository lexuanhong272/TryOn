package com.android.xuanhong.tryonglasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

import com.android.xuanhong.tryonglasses.models.view.MenuActivity;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;
import com.android.xuanhong.tryonglasses.util.Utils;
import com.android.xuanhong.tryonglasses.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String ASSETS_TARGET_DIRECTORY = Environment.getExternalStorageDirectory() + File.separator
            + "3DModelViewerOS";

    EditText edtID;
    Button btnLogin;
    Button btnForgotID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), LoginByEmail.class));



        edtID = (EditText) findViewById(R.id.edtID);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnForgotID = (Button) findViewById(R.id.btnForgot);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtID.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                }

                else {
                    if(Integer.parseInt(edtID.getText().toString()) == 12345){
                        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), MainScreen.class));
                        MainActivity.this.finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Wrong ID", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnForgotID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ForgotID.class));
                MainActivity.this.finish();

            }
        });



    }


    private void init() {
        try {
            Thread tcopy = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        installExamples();
                    } catch (Exception ex) {
                        Toast.makeText(MainActivity.this.getApplicationContext(),
                                "Unexpected error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("init", "Unexpected error: " + ex.getMessage(), ex);
                    }
                }
            });
            Thread tsplash = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(000);
                        MainActivity.this.startActivity(
                                new Intent(MainActivity.this.getApplicationContext(), ModelActivity.class));
                        MainActivity.this.finish();
                    } catch (InterruptedException ex) {
                        Toast.makeText(MainActivity.this.getApplicationContext(),
                                "Unexpected error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("init", "Unexpected error: " + ex.getMessage(), ex);
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Unexpected error: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("init", "Unexpected error: " + ex.getMessage(), ex);
        }

        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ModelActivity.class));
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void installExamples() {
        // TODO: Enable TODO: copy also in internal memory
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Couldn't copy assets. Please install an sd-card",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Utils.copyAssets(getApplicationContext(), "models", new File(ASSETS_TARGET_DIRECTORY, "models"));

    }

}
