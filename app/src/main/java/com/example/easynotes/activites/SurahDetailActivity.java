package com.example.easynotes.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.easynotes.Model.SurahDetail;
import com.example.easynotes.R;
import com.example.easynotes.adapter.SurahDetailAdapter;
import com.example.easynotes.commen.Commen;
import com.example.easynotes.response.SurahDetailResponse;
import com.example.easynotes.viewmodel.SurahDetailViewModel;
import com.example.easynotes.viewmodel.SurahViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailActivity extends AppCompatActivity {

    private TextView surahName,surahType,surahTranslation;
    private int no;
    private RecyclerView recyclerView;
    private List<SurahDetail> list;
    private SurahDetailAdapter surahDetailAdapter;
    private SurahDetailViewModel surahDetailViewModel;
    private String urdu ="urdu_junagarhi";
    private String hindi = "hindi_omari";
    private String english = "english_hilali_khan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail);
        // here we intilize thses objects here

        surahName = findViewById(R.id.surah_name);
        surahType = findViewById(R.id.type);
        surahTranslation = findViewById(R.id.translation);
        recyclerView = findViewById(R.id.surah_detail_rv);

        no = getIntent().getIntExtra(Commen.SURAH_NO,0);

        surahName.setText(getIntent().getStringExtra(Commen.SURAH_NAME));

        surahType.setText(getIntent().getStringExtra(Commen.SURAH_TYPE)+" "+
                getIntent().getIntExtra(Commen.SURAH_TOTAL_AYA,0)+"Ayaat");

        surahTranslation.setText(getIntent().getStringExtra(Commen.SURAH_TRANSLATION));


        recyclerView.setHasFixedSize(true);
        list= new ArrayList<>();

        surahTranslation(english,no);



    }

    private void surahTranslation(String lan, int id) {
        if (list.size()>0){
            list.clear();
        }

        surahDetailViewModel = new ViewModelProvider(this).get(SurahDetailViewModel.class);
        surahDetailViewModel.getSurahDetail(lan, id).observe(this, (Observer<? super SurahDetailResponse>) surahDetailResponse -> {

            for (int i=0; i <surahDetailResponse.getList().size();i++){
                list.add(new SurahDetail(surahDetailResponse.getList().get(i).getId(),
                        surahDetailResponse.getList().get(i).getSura(),
                        surahDetailResponse.getList().get(i).getAya(),
                        surahDetailResponse.getList().get(i).getArabic_text(),
                        surahDetailResponse.getList().get(i).getTranslation(),
                        surahDetailResponse.getList().get(i).getFootnotes()));
            }
            if (list.size()!=0){
                surahDetailAdapter = new SurahDetailAdapter(this,list);
                recyclerView.setAdapter(surahDetailAdapter);
            }

        });
    }
}