package com.mooracle.stormy.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mooracle.stormy.R;
import com.mooracle.stormy.databinding.HourlyListItemBinding;
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
        HourlyListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.hourly_list_item,
                        viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hour hour = hours.get(i);
        viewHolder.hourlyListItemBinding.setHour(hour);
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Binding
        public HourlyListItemBinding hourlyListItemBinding;

        //constructor
        public ViewHolder(HourlyListItemBinding hourlyLayoutBinding){
            super(hourlyLayoutBinding.getRoot());
            hourlyListItemBinding = hourlyLayoutBinding;
        }
    }
}
