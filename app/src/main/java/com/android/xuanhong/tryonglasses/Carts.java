package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Carts extends Activity {

    Button btnBackToMainFromCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        btnBackToMainFromCart = (Button) findViewById(R.id.btnBackToMainFromCart);

        btnBackToMainFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carts.this.startActivity(new Intent(Carts.this.getApplicationContext(), MainScreen.class));
                Carts.this.finish();
            }
        });


    }
}
