package com.example.easynotes.weather_adapter;

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
import com.example.easynotes.weather_data.forcast;

import java.util.ArrayList;



public class ForcastAdapter extends RecyclerView.Adapter<ForcastAdapter.ViewHolder> {

    private ArrayList<forcast> data;
    private Context context2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTemprature,txtTime,txtFeellike;
        private final ImageView imgicon;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            txtTemprature = (TextView) view.findViewById(R.id.txt_temprature2);
            txtTime = (TextView) view.findViewById(R.id.txt_time2);
            txtFeellike = (TextView) view.findViewById(R.id.txt_feelslike2);
            imgicon = (ImageView) view.findViewById(R.id.img_condion2);
        }

        public TextView getTxtTemprature() {
            return txtTemprature;
        }

        public TextView getTxtTime() {
            return txtTime;
        }

        public TextView getTxtFeellike() {
            return txtFeellike;
        }

        public ImageView getImgicon() {
            return imgicon;
        }
    }

    public ForcastAdapter(ArrayList<forcast> data,Context context2) {
        this.data = data;
        this.context2 = context2;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_forcast, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        forcast forcast = data.get(position);
        viewHolder.txtTemprature.setText(forcast.getTemprature());
        viewHolder.txtTime.setText(forcast.getTime());
        viewHolder.txtFeellike.setText(forcast.getFeellike());
        Glide.with(context2).load("https:" + forcast.getIcon()).into(viewHolder.getImgicon());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
