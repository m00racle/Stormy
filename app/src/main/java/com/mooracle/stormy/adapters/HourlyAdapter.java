package com.mooracle.stormy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mooracle.stormy.R;
import com.mooracle.stormy.weather.Hour;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    //variable definitions
    private List<Hour> hours;
    private Context context;

    //constructor


    public HourlyAdapter(List<Hour> hours, Context context) {
        this.hours = hours;
        this.context = context;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //TODO: change this to directly use Layout inflater to make space for views
        /*HourlyListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.hourly_list_item,
                        viewGroup, false);*/
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hourly_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hour hour = hours.get(i);
        //TODO: DELETE THIS viewHolder.hourlyListItemBinding.setHour(hour);
        //ASSIGNS views to proper values
        viewHolder.iconImageView.setImageResource(hour.getIcon());
        viewHolder.temperatureTextView.setText(String.valueOf(hour.getTemperature()));
        viewHolder.timeTextView.setText(hour.getTime());
        viewHolder.summaryTextView.setText(hour.getSummary());
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //TODO delete Binding:        public HourlyListItemBinding hourlyListItemBinding;
        //view list:
        ImageView iconImageView;
        TextView temperatureTextView, timeTextView, summaryTextView;

        //constructor
        public ViewHolder(View view){
            super(view);
            //TODO: delete hourlyListItemBinding = hourlyLayoutBinding;
            //assign views:
            iconImageView = view.findViewById(R.id.iconImageView);
            temperatureTextView = view.findViewById(R.id.temperatureLabel);
            timeTextView = view.findViewById(R.id.timeLabel);
            summaryTextView = view.findViewById(R.id.summaryLabel);
        }
    }
}
