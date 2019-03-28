package com.example.lenovo.cityguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
public class GridAdapter extends BaseAdapter {
    Activity context;
    ArrayList<hotelsinfo> arrayList;
    TextView mName;
    ImageView imageView;

    public GridAdapter(Activity context, ArrayList<hotelsinfo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=context.getLayoutInflater().inflate(R.layout.custom_grid,null);
        mName=(TextView)convertView.findViewById(R.id.name);
        imageView=(ImageView)convertView.findViewById(R.id.icon);
        mName.setText(arrayList.get(position).getName());
        ImageRequest imageRequest= new ImageRequest(arrayList.get(position).getUrl(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        CustomRequest.getInstance(context).addToRequestQue(imageRequest);


        return convertView;
    }
}
