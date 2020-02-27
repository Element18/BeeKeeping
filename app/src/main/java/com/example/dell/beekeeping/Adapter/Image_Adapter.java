package com.example.dell.beekeeping.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.beekeeping.Models.Image_Model;
import com.example.dell.beekeeping.R;

public class Image_Adapter extends CursorAdapter {

    public static class ViewHolder {
        private TextView textView;
        private ImageView imageView;

        ViewHolder (View view) {
            imageView = view.findViewById(R.id.imageView4);
            textView = view.findViewById(R.id.caption);
        }

    }

    public Image_Adapter (Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_inflate_layout,parent,false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView (View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder)view.getTag();
        Image_Model image_model = new Image_Model(cursor);
        viewHolder.textView.setText(image_model.getCaption());
        viewHolder.imageView.setImageBitmap(image_model.getImage());
    }

}
