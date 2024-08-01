package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.easynotes.Listner.SurahListner;
import com.example.easynotes.Read_quran.Read_Quran_Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    AdView adView;
 LinearLayout onlinequran,readquran,weatherbtn,readsurah,sharebtn,ratebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView = findViewById(R.id.addview);
        // first we initlize mobile adds step one
        MobileAds.initialize(this);
        //now create we add request for sending it on request que step two
        AdRequest adRequest = new AdRequest.Builder().build();
        // step there here finley place
        adView.loadAd(adRequest);


        // here we intilize variables
        sharebtn = findViewById(R.id.btn06);
        ratebtn = findViewById(R.id.btn05);
       onlinequran = findViewById(R.id.btn01);
       readquran = findViewById(R.id.btn02);
       weatherbtn = findViewById(R.id.btn04);
       readsurah = findViewById(R.id.btn03);
       // these are smple onclick listners
       // now we create onclick listner on btnoniline page
        onlinequran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String wpurl="https://wa.me/+923179570982?text=Hi,is here a Quran Academy?";
              Intent intent = new Intent(Intent.ACTION_VIEW);
              intent.setData(Uri.parse(wpurl));
              startActivity(intent);
            }
        });
        //now we create onclikistner for btn read quran
        readquran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Read_Quran_Activity.class);
                startActivity(intent);

            }
        });

        // now we create onclick listner for surah
        readsurah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, read_surah_Activity.class);
                startActivity(intent);

            }
        });
        // now we create onclick listner for weathr activity
        weatherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, weather_Activity.class);
                startActivity(intent);

            }
        });




        // herae writen code of share and rate btn
        // onclick lisnter on share btn
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Check This App\n"+"https://play.google.com/store/apps/details?id" + "appPackageName");
            }
        });


    }
}