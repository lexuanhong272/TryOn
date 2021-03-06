package com.android.xuanhong.tryonglasses.models.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.android.xuanhong.tryonglasses.models.model.Object3DBuilder;
import com.android.xuanhong.tryonglasses.models.model.Object3DData;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoaderObjects extends LoaderScenes {

	public LoaderObjects(ModelActivity modelActivity) {
		super(modelActivity);
	}


	public static Object3DData objFace;
	public static Object3DData objGlass;

	public void init() {
		super.init();
		new AsyncTask<Void, Void, Void>() {

			ProgressDialog dialog = new ProgressDialog(parent);
			List<Exception> errors = new ArrayList<Exception>();

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog.setCancelable(false);
				dialog.setMessage("Please wait a few minutes, program is loading objects...");
				dialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {

				File root = android.os.Environment.getExternalStorageDirectory();

				try {
					// 3D Axis
					Object3DData axis = Object3DBuilder.buildAxis().setId("axis");
					axis.setColor(new float[] { 0.0f, 0.0f, 0.0f, 1.0f });
					axis.centerAndScale(4);
					addObject(axis);





					try {
						objFace = Object3DBuilder.loadV5(parent.getAssets(), "models/", "sculpted_model.obj");
						objFace.setPosition(new float[] { 0f, 0f, 0f });
						objFace.centerAndScale(3);


						addObject(objFace);
					} catch (Exception ex) {
						errors.add(ex);
					}

					try {
						objGlass = Object3DBuilder.loadV5(parent.getAssets(), "models/", "glasses2.obj");

						objGlass.centerAndScale(1.3f);
						//objGlass.setRotation(new float[] {0, 180, 0});
						objGlass.setPosition(new float[] { 0f, 0.5f, -0.3f });
						addObject(objGlass);
					} catch (Exception ex) {
						errors.add(ex);
					}

				} catch (Exception ex) {
					errors.add(ex);
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				if (!errors.isEmpty()) {
					StringBuilder msg = new StringBuilder("There was a problem loading the data");
					for (Exception error : errors) {
						Log.e("Example", error.getMessage(), error);
						msg.append("\n" + error.getMessage());
					}
					Toast.makeText(parent.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
				}
			}
		}.execute();
	}
}
