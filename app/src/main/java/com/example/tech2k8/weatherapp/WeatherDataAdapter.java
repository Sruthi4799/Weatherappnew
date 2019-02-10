package com.example.tech2k8.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.WeatherViewHolder> {

    private ArrayList<WeatherData> weatherData;
    private Context context;

    public WeatherDataAdapter(ArrayList<WeatherData> weatherData,Context context) {
        this.weatherData = weatherData;
        this.context=context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.recyclerview_row,viewGroup,false);
        WeatherViewHolder holder =new WeatherViewHolder(rowView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WeatherViewHolder weatherViewHolder, int i) {
        WeatherData data =weatherData.get(i);
        weatherViewHolder.lat.setText(data.getLatitude());
        weatherViewHolder.lon.setText(data.getLongitude());
        weatherViewHolder.temp.setText(data.getTemperature());

        weatherViewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu =new PopupMenu(context,weatherViewHolder.menu);
                popupMenu.inflate(R.menu.list_data_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.delete)
                        {
                            Toast.makeText(context, "delete clicked", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder
    {

        TextView lat,lon,temp,menu;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            lat=itemView.findViewById(R.id.lat);
            lon=itemView.findViewById(R.id.lon);
            temp=itemView.findViewById(R.id.temp);
            menu=itemView.findViewById(R.id.menu);
        }
    }
}
