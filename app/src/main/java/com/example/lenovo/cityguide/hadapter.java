package com.example.lenovo.cityguide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class hadapter extends BaseAdapter{
    Activity context;
    ArrayList<hotelsinfo> arrayList;
    TextView tname,taddress,twebsite,tcontact,tinfo;
    ImageView himage,cimage,wimage,timage,aimage;
    String name,address,contact,number,website,info,url;
    hotelsinfo hotels;

    public hadapter(Activity context, ArrayList<hotelsinfo> arrayList) {
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
        convertView=context.getLayoutInflater().inflate(R.layout.hotel_custom,null);
        tname=(TextView)convertView.findViewById(R.id.hotel_title);
        tinfo=(TextView)convertView.findViewById(R.id.hotel_info);
        taddress=(TextView)convertView.findViewById(R.id.hotel_address);
        twebsite=(TextView)convertView.findViewById(R.id.hotel_website);
        timage = (ImageView)convertView.findViewById(R.id.timage) ;
        wimage = (ImageView)convertView.findViewById(R.id.wimage) ;

        tcontact=(TextView)convertView.findViewById(R.id.hotel_number);
            tname.setText(arrayList.get(position).getName());
            taddress.setText(arrayList.get(position).getAddress());
            tcontact.setText(arrayList.get(position).getContact());
        himage=(ImageView)convertView.findViewById(R.id.hotel_image);

        if(arrayList.get(position).hasinfo()){
            tinfo.setText(arrayList.get(position).getInfo());

        }
            else{
                tinfo.setVisibility(View.GONE);
                timage.setImageResource(R.drawable.ic_food);
                wimage.setImageResource(R.drawable.ic_foodtype);

        }
            twebsite.setText(arrayList.get(position).getWebsite());
            ImageRequest imageRequest= new ImageRequest(arrayList.get(position).getUrl(), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    himage.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.FIT_START, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            CustomRequest.getInstance(context).addToRequestQue(imageRequest);


        return convertView;

    }
}
