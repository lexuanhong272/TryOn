package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.models.view.MenuActivity;

public class ForgotID extends Activity {

    EditText edtEmail;
    Button btnSendEmail;

    Button btnReturnMainFromForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_id);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);

        btnReturnMainFromForgot = (Button) findViewById(R.id.btnReturnMainFromForgot);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().equals(""))
                    Toast.makeText(ForgotID.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(ForgotID.this, "Email sent!", Toast.LENGTH_SHORT).show();

            }
        });


        btnReturnMainFromForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotID.this.startActivity(new Intent(ForgotID.this.getApplicationContext(), MainActivity.class));
                ForgotID.this.finish();

            }
        });

    }
}
