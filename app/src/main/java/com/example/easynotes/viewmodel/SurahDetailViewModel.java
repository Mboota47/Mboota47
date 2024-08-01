package com.example.easynotes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easynotes.repository.SurahDetailRepo;
import com.example.easynotes.response.SurahDetailResponse;

public class SurahDetailViewModel extends ViewModel {

    public SurahDetailRepo surahDetailRepo;

    public SurahDetailViewModel() {
        surahDetailRepo = new SurahDetailRepo();
    }
    public LiveData<SurahDetailResponse> getSurahDetail(String lan,int id){
        return surahDetailRepo.getSurahDetail(lan, id);
    }
}
