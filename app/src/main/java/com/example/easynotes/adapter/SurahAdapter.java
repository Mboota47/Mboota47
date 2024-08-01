package com.example.easynotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easynotes.Listner.SurahListner;
import com.example.easynotes.Model.Surah;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynotes.R;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {

    private Context context;
    private List<Surah> list;
    private SurahListner surahListner;

    public SurahAdapter(Context context, List<Surah> list,SurahListner surahListner) {
        this.context = context;
        this.list = list;
        this.surahListner = surahListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_layout,parent,false);
        return new ViewHolder(view,surahListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.surahNo.setText(String.valueOf(list.get(position).getNumber()));
        holder.englishName.setText(list.get(position).getEnglishName());
        holder.arabicName.setText(list.get(position).getName());
        holder.totalAya.setText( "Ayaat : " +String.valueOf(list.get(position).getNumberOfAyahs()));

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView surahNo,arabicName,englishName,totalAya;
        private SurahListner surahListner;

        public ViewHolder(@NonNull View itemView,SurahListner surahListner) {
            super(itemView);

            surahNo = itemView.findViewById(R.id.surah_number);
            arabicName = itemView.findViewById(R.id.arabic_name);
            englishName = itemView.findViewById(R.id.english_name);
            totalAya = itemView.findViewById(R.id.total_aya);
            // create a onclick listner on surah's
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    surahListner.onSurahListner(getAdapterPosition());
                }
            });




        }


    }
}
