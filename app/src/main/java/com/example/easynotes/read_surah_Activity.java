package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynotes.Listner.SurahListner;
import com.example.easynotes.Model.Surah;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.easynotes.activites.SurahDetailActivity;
import com.example.easynotes.adapter.SurahAdapter;
import com.example.easynotes.commen.Commen;
import com.example.easynotes.viewmodel.SurahViewModel;

import java.util.ArrayList;
import java.util.List;

public class read_surah_Activity extends AppCompatActivity implements SurahListner {
    private RecyclerView recyclerView;
    private SurahAdapter surahAdapter;
    private List<Surah> list;
    private SurahViewModel surahViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_suah);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                ,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.surah_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getSurah().observe(this,surahResponse -> {
            for (int i =0; i<surahResponse.getList().size();i++){
                list.add(new Surah((surahResponse.getList().get(i).getNumber()),
                        String.valueOf(surahResponse.getList().get(i).getName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishNameTranslation()),
                        (surahResponse.getList().get(i).getNumberOfAyahs()),
                        String.valueOf(surahResponse.getList().get(i).getRevelationType())

                        ));
            }
            if (list.size()!=0){
                surahAdapter = new SurahAdapter(this,list,this::onSurahListner);
                recyclerView.setAdapter(surahAdapter);
                surahAdapter.notifyDataSetChanged();
            }


        });

    }

    @Override
    public void onSurahListner(int postion) {
        Intent intent = new Intent(read_surah_Activity.this, SurahDetailActivity.class);
        intent.putExtra(Commen.SURAH_NO,list.get(postion).getNumber());
        intent.putExtra(Commen.SURAH_NAME,list.get(postion).getName());
        intent.putExtra(Commen.SURAH_TOTAL_AYA,list.get(postion).getNumberOfAyahs());
        intent.putExtra(Commen.SURAH_TYPE,list.get(postion).getRevelationType());
        intent.putExtra(Commen.SURAH_TRANSLATION,list.get(postion).getEnglishNameTranslation());

        startActivity(intent);
    }


}