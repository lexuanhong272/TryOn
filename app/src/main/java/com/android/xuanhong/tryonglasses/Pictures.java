package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pictures extends Activity {

    Button btnBackToMainFromPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);

        btnBackToMainFromPic = (Button) findViewById(R.id.btnBackToMainFromPic);

        btnBackToMainFromPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pictures.this.startActivity(new Intent(Pictures.this.getApplicationContext(), MainScreen.class));
                Pictures.this.finish();
            }
        });



    }
}
