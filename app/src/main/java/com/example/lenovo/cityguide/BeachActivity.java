package com.example.lenovo.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeachActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String URL = "https://api.myjson.com/bins/9uqiy";
    ArrayList<hotelsinfo> beach_info;
    GridView gridView;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        gridView=(GridView)findViewById(R.id.gridview);
        beach_info= new ArrayList<hotelsinfo>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processJSON(response);
                adapter = new GridAdapter(BeachActivity.this,beach_info);
                gridView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        CustomRequest.getInstance(BeachActivity.this).addToRequestQue(jsonObjectRequest);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hotel) {
            Intent i= new Intent(getApplicationContext(),HotelsActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_cafe) {

        } else if (id == R.id.nav_restaurant) {
            Intent i = new Intent(getApplicationContext(),RestaurantActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_beach) {

        } else if (id == R.id.nav_sports) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void processJSON(JSONObject response){
        try {
            JSONArray jsonArray = response.getJSONArray("Beach");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String jHotel = jsonObject.getString("name");
                String image_url= jsonObject.getString("url");
                beach_info.add(new hotelsinfo(jHotel,image_url));
                Log.v("jHOTEL",jHotel);
                Log.v("image  ",image_url);


            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
