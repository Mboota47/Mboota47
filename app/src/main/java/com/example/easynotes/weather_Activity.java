package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.easynotes.weather_adapter.ForcastAdapter;
import com.example.easynotes.weather_data.forcast;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class weather_Activity extends AppCompatActivity {
    String Url = "https://api.weatherapi.com/v1/forecast.json?key=24320ede057c4aaebe435130241806&q=sialkot";
    TextView textTemprature, textData, textLocation, feellike;
    ImageView imageCondition;
    ForcastAdapter adapter;
    RecyclerView rvForcast;
    ArrayList<forcast> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        textTemprature = findViewById(R.id.txt_temprature);
        textData = findViewById(R.id.txt_date_time);
        textLocation = findViewById(R.id.txt_location);
        imageCondition = findViewById(R.id.img_condition);
        feellike = findViewById(R.id.txt_feelslike);
        rvForcast = findViewById(R.id.rv_forcast);
        data = new ArrayList<>();
        adapter = new ForcastAdapter(data, this);
        rvForcast.setAdapter(adapter);
        rvForcast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        loadweatherData();
    }

    private void loadweatherData() {
        StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("Weather Response", s);

                try {
                    //for getting object from api
                    JSONObject weatherObject = new JSONObject(s);
                    JSONObject locationObject = weatherObject.getJSONObject("location");
                    JSONObject currentobject = weatherObject.getJSONObject("current");
                    JSONObject conditionObject = currentobject.getJSONObject("condition");
                    JSONObject ForcastObject = weatherObject.getJSONObject("forecast");
                    JSONArray ForcastDayArray = ForcastObject.getJSONArray("forecastday");
                    JSONObject ForcastDayObject = ForcastDayArray.getJSONObject(0);
                    JSONArray hourArray = ForcastDayObject.getJSONArray("hour");
                    // make a for loop for hours
                    for (int i = 0; i < hourArray.length(); i++) {
                        JSONObject hoursObject = hourArray.getJSONObject(i);
                        String hourTime = hoursObject.getString("time").split(" ")[1];
                        String hourTemprature = hoursObject.getString("temp_c");
                        JSONObject hourCondionObject = hoursObject.getJSONObject("condition");
                        String imgIcon = "https:" + hourCondionObject.getString("icon");
                        String feellike = hourCondionObject.getString("text");

                        // set data
                        forcast forcastData = new forcast();
                        forcastData.setTime(hourTime);
                        forcastData.setTemprature(hourTemprature);
                        forcastData.setFeellike(feellike);
                        forcastData.setIcon(imgIcon);

                        data.add(forcastData);
                    }


                    // for get single item form api thats we need

                    String icon = "https:" + conditionObject.getString("icon");
                    String date = currentobject.getString("last_updated");
                    String temprature = currentobject.getString("temp_c");
                    String cityName = locationObject.getString("name");
                    String country = locationObject.getString("country");
                    String Feel = conditionObject.getString("text");


                    feellike.setText(Feel);
                    textData.setText(date);
                    textTemprature.setText(temprature + " \u2103");
                    textLocation.setText(cityName + ", " + country);
                    Glide.with(weather_Activity.this).load(icon).into(imageCondition);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Weather Error", error.toString());

            }
        });

        Volley.newRequestQueue(this).add(request);
    }
}
