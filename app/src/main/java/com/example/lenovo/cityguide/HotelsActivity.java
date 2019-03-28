package com.example.lenovo.cityguide;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity{
ListView listView;
hadapter adapter;
ArrayList<hotelsinfo> hotel_data;
String url="https://api.myjson.com/bins/1f0x62";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        listView=(ListView)findViewById(R.id.listview);
        hotel_data = new ArrayList<hotelsinfo>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processJSON(response);
                adapter = new hadapter(HotelsActivity.this,hotel_data);
                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        CustomRequest.getInstance(HotelsActivity.this).addToRequestQue(jsonObjectRequest);
    }

    public void processJSON(JSONObject response){
        try {
            JSONArray jsonArray = response.getJSONArray("Hotels");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String jHotel = jsonObject.getString("hotelname");
                String jInfo = jsonObject.getString("info");
                String jAddress = jsonObject.getString("address");
                String jWebsite = jsonObject.getString("website");
                String jPhone = jsonObject.getString("contact");
                String image_url= jsonObject.getString("url");//Log.v("jHOTEL",jHotel);
                hotel_data.add(new hotelsinfo(jHotel,jAddress,jPhone,jWebsite,jInfo,image_url));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
