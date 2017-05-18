package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditProfile extends Activity {


    Button btnUpdate;
    Button btnBackToMainFromEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnBackToMainFromEdit = (Button) findViewById(R.id.btnBackToMainFromEdit);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnBackToMainFromEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile.this.startActivity(new Intent(EditProfile.this.getApplicationContext(), MainScreen.class));
                EditProfile.this.finish();
            }
        });

    }
}
