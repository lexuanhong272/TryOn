package com.android.xuanhong.tryonglasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Pinky on 04-May-17.
 */

public class GlassAdapter extends SearchAdapter<Glass> {
    class ViewHolder {
        @InjectView(R.id.txt_id) TextView txt_id;
        @InjectView(R.id.txt_gia) TextView txt_gia;
        @InjectView(R.id.txt_mota) TextView txt_mota;
        @InjectView(R.id.imgv_glass) ImageView imgv_glass;


        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public GlassAdapter(List<Glass> movies, Context context) {
        super(movies, context);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_glass, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.txt_gia.setText(filteredContainer.get(position).getPrice());
        viewHolder.txt_mota.setText(filteredContainer.get(position).getDescribe());
        viewHolder.txt_id.setText(filteredContainer.get(position).getTitle());
        viewHolder.imgv_glass.setImageDrawable(context.getResources().getDrawable(filteredContainer.get(position).getPoster()));
        return convertView;
    }
}

