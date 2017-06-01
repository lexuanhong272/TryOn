package com.android.xuanhong.tryonglasses.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.Glass;
import com.android.xuanhong.tryonglasses.GlassesGallery;
import com.android.xuanhong.tryonglasses.MainActivity;
import com.android.xuanhong.tryonglasses.R;
import com.android.xuanhong.tryonglasses.controller.RestManager;
import com.android.xuanhong.tryonglasses.model.Glasses;
import com.android.xuanhong.tryonglasses.model.adapter.GlassesAdapter;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlassesGallery2 extends Activity {

    private RecyclerView mRecyclerView;
    private RestManager mRestManager;
    private GlassesAdapter mGlassesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glasses_gallery2);
        configViews();

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GlassesGallery2.this, "On click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GlassesGallery2.this, ModelActivity.class);
                GlassesGallery2.this.startActivity(intent);
            }
        });

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Call<List<Glasses>> listCall = mRestManager.getGlassesService().getAllGlasses();
        //Toast.makeText(GlassesGallery2.this, "AAA", Toast.LENGTH_SHORT).show();

        listCall.enqueue(new Callback<List<Glasses>>() {

            @Override
            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(GlassesGallery2.this, "Get Glasses successfully", Toast.LENGTH_SHORT).show();
                    List<Glasses> glassesList = response.body();
                    for(int i = 0; i < glassesList.size(); i++){
                        Glasses glasses = glassesList.get(i);
                        mGlassesAdapter.addGlasses(glasses);
                    }
                }
                else {
                    Toast.makeText(GlassesGallery2.this, "Error on get glasses", Toast.LENGTH_SHORT).show();
                    int sc = response.code();
                    switch (sc){
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Glasses>> call, Throwable t) {
                Toast.makeText(GlassesGallery2.this, "Fail to get glasses", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mGlassesAdapter = new GlassesAdapter();
        mRecyclerView.setAdapter(mGlassesAdapter);
        mRestManager = new RestManager();
    }


}
