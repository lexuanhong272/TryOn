package com.android.xuanhong.tryonglasses.model.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xuanhong.tryonglasses.GlassesGallery;
import com.android.xuanhong.tryonglasses.R;
import com.android.xuanhong.tryonglasses.model.Glasses;
import com.android.xuanhong.tryonglasses.model.callback.GlassesService;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;
import com.android.xuanhong.tryonglasses.util.GlassesGallery2;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinky on 25-May-17.
 */

public class GlassesAdapter extends RecyclerView.Adapter<GlassesAdapter.Holder> implements View.OnClickListener {

    private static final String Tag001 = GlassesAdapter.class.getSimpleName();
    private List<Glasses> mGlasses;
    public GlassesAdapter() {
        mGlasses = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_2 , null, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glasses currGlasses = mGlasses.get(position);
        holder.txtId.setText(currGlasses.getId());
        holder.txtName.setText(currGlasses.getName());
        holder.txtPrice.setText(currGlasses.getPrice() + "");

        Log.d("thumnail", currGlasses.getThumnail());
        byte[] byteArray =  Base64.decode(currGlasses.getThumnail(), Base64.DEFAULT) ;
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        holder.imvThumnail.setImageBitmap(bmp);

        //Picasso.with(holder.imvThumnail.getContext()).load("http://services.hanselandpetal.com/photos/bonsai.jpg").into(holder.imvThumnail);
    }

    @Override
    public int getItemCount() {
        return mGlasses.size();
    }

    public void addGlasses(Glasses glasses) {
        Log.d(Tag001,  glasses.getId());
        mGlasses.add(glasses);
        notifyDataSetChanged();
    }

    public Glasses getSelectedFlower(int position) {
        return mGlasses.get(position);
    }

    @Override
    public void onClick(View v) {

    }
    //@Override
//    public void onClick(View v) {
//
//        Intent intent = new Intent(getClass(), ModelActivity.class);
//        intent.putExtra("ID_Glasses", chose.getTitle());
//        GlassesGallery.this.startActivity(intent);
//    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtName, txtId, txtPrice;
        private ImageView imvThumnail;

        public Holder(View intentView){
            super(intentView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            imvThumnail = (ImageView) itemView.findViewById(R.id.imvThumnail);
        }

        @Override
        public void onClick(View v) {
        }

    }

}
