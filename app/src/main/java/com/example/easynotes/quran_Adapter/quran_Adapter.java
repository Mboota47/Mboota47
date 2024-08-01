package com.example.easynotes.quran_Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easynotes.R;
import com.example.easynotes.quran_Data;
import com.example.easynotes.weather_adapter.ForcastAdapter;
import com.example.easynotes.weather_data.forcast;

import java.util.ArrayList;

public class quran_Adapter extends RecyclerView.Adapter<quran_Adapter.ViewHolder> {

    private ArrayList<quran_Data> quran_data;
    private Context context3;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView quranNo,quranName,quranenglishName,qurantotalAya;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            quranNo = (TextView) view.findViewById(R.id.quran_number);
            quranName = (TextView) view.findViewById(R.id.quaran_arabic_name);
            quranenglishName = (TextView) view.findViewById(R.id.quran_english_name);
            qurantotalAya = (TextView) view.findViewById(R.id.quran_total_aya);

        }

        // create here getter of it

        public TextView getQuranNo() {
            return quranNo;
        }

        public TextView getQuranName() {
            return quranName;
        }

        public TextView getQuranenglishName() {
            return quranenglishName;
        }

        public TextView getQurantotalAya() {
            return qurantotalAya;
        }
    }

    public quran_Adapter(ArrayList<quran_Data> quran_data,Context context3) {
        this.quran_data = quran_data;
        this.context3 = context3;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quran_layout, parent, false);

        return new quran_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        quran_Data quranData = quran_data.get(position);
        holder.quranNo.setText(String.valueOf(quranData.getPage()));
        holder.quranName.setText(String.valueOf(quranData.getName()));
        holder.quranenglishName.setText(String.valueOf(quranData.getEnglishName()));
        holder.qurantotalAya.setText(String.valueOf(quranData.getJuz()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return quran_data.size();
    }
}
