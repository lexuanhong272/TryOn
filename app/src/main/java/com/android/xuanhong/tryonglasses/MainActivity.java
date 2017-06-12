package com.android.xuanhong.tryonglasses;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.android.xuanhong.tryonglasses.Activities.LoginFace;
import com.android.xuanhong.tryonglasses.Activities.Welcome;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;
import com.android.xuanhong.tryonglasses.util.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends Activity {

    public static final int PICK_IMAGE = 100;


    Service service;

    private Uri mUriPhotoTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), Welcome.class));
        MainActivity.this.finish();

        Button btn = (Button) findViewById(R.id.btn_upload);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // Change base URL to your upload server URL.
        service = new Retrofit.Builder().baseUrl("http://192.168.137.1:2055").client(client).build().create(Service.class);

        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intent.resolveActivity(getPackageManager()) != null) {
                        // Save the photo taken to a temporary file.
                        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        try {
                            File file = File.createTempFile("IMG_", ".png", storageDir);
                            mUriPhotoTaken = Uri.fromFile(file);
                            intent.putExtra("data", mUriPhotoTaken);
                            startActivityForResult(intent, PICK_IMAGE);
                        } catch (IOException e) {
                            //setInfo(e.getMessage());
                        }
                    }
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");


            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            OutputStream outStream = null;

            String filename = "IMG_";
            File file = new File(extStorageDirectory, filename + ".png");
            if (file.exists()) {
                file.delete();
                file = new File(extStorageDirectory, filename + ".png");
                Log.e("file exist", "" + file + ",Bitmap= " + filename);
            }


            try {

                outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

//            Log.d("THIS", data.getData().getPath());

            retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
            Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_SHORT).show();

            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(MainActivity.this, "bbbbbb", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(MainActivity.this, "ccc", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


