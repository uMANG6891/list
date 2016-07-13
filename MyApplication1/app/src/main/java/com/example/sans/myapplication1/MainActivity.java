package com.example.sans.myapplication1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView=(ListView)findViewById(R.id.list);
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "http://api.themoviedb.org/3/movie/popular?api_key=fa468af9e769ba5fbc027e9e933130ed",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Movie> movieList= new ArrayList<Movie>();
                        try{
                            JSONArray jsonArray=response.getJSONArray("results");
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject jsonObject= jsonArray.getJSONObject(i);
                                String title= jsonObject.getString("title");

                                movieList.add(new Movie(title));
                                MovieAdapter adapter= new MovieAdapter(getApplicationContext(),movieList);
                                listView.setAdapter(adapter);

                            }


                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(), "Error getting data", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                    }
                });
        requestQueue.add(jsonObjectRequest);


    }




}

