package com.example.easynotes.Read_quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.easynotes.R;
import com.example.easynotes.quran_Adapter.quran_Adapter;
import com.example.easynotes.quran_Data;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Read_Quran_Activity extends AppCompatActivity {
    String Url_quran = "https://api.alquran.cloud/v1/juz/30/quran-uthmani";
    quran_Adapter quranAdapter;
    RecyclerView rv_quran;
    ArrayList<quran_Data> data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_quran);
        // here we intlize it
        rv_quran = findViewById(R.id.Quran_RV);
        data =new ArrayList<>();
        quranAdapter=new quran_Adapter(data,this);
        rv_quran.setAdapter(quranAdapter);
        rv_quran.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        // here we create a function

        loadQuranData();

    }

    private void loadQuranData(){
        StringRequest request = new StringRequest(Request.Method.GET,Url_quran, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Read_Quran_Activity.this, "this api is responsing ", Toast.LENGTH_SHORT).show();

                Log.d("Quran Response", response);
                try {
                    JSONObject qurandataObject = new JSONObject(response);
                    JSONObject quranDataObjec =qurandataObject.getJSONObject("data");
                    JSONArray quran_aya_Array=quranDataObjec.getJSONArray("ayahs");

                    for (int i = 0; i<quran_aya_Array.length();i++){
                        JSONObject quranAyaObject = quran_aya_Array.getJSONObject(i);
                     //   String juzNo = quranAyaObject.getString("juz");
                     //   String txtno = quranAyaObject.getString("text");
                        // ingerdient Gsonlibraies for recyles these objects form api
                        data.add(new Gson().fromJson(quranAyaObject.toString(),quran_Data.class));
                    }
                    



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Quaran_juzz Error",error.toString());
                Toast.makeText(Read_Quran_Activity.this, "this api is not responsing ", Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(request);
    }

}