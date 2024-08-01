package com.example.easynotes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easynotes.repository.SurahRepo;
import com.example.easynotes.response.SurahResponse;

public class SurahViewModel extends ViewModel {
    private SurahRepo surahRepo;

    public SurahViewModel() {
        surahRepo = new SurahRepo();
    }
    public LiveData<SurahResponse> getSurah(){
        return surahRepo.getSurah();
    }
}
