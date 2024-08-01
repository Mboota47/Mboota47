package com.example.easynotes.response;

import com.example.easynotes.Model.Surah;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurahResponse {
    @SerializedName("data")
    private List<Surah> list;

    public List<com.example.easynotes.Model.Surah> getList() {
        return list;
    }

    public void setList(List<com.example.easynotes.Model.Surah> list) {
        this.list = list;
    }
}
