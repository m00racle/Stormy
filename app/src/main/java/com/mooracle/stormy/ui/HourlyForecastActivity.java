package com.mooracle.stormy.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mooracle.stormy.R;
import com.mooracle.stormy.adapters.HourlyAdapter;
import com.mooracle.stormy.databinding.ActivityHourlyForecastBinding;
import com.mooracle.stormy.weather.Hour;

import java.util.ArrayList;
import java.util.List;

public class HourlyForecastActivity extends AppCompatActivity {
    //add adapter and binding variables
    private HourlyAdapter adapter;
    private ActivityHourlyForecastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        //get the serialized hour data from intent
        Intent intent = getIntent();
        List<Hour> hourList = (ArrayList<Hour>) intent.getSerializableExtra("HourlyList");
        //set binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hourly_forecast);

        //set fixed size with the binding
        binding.hourlyListItems.setHasFixedSize(true);

        //add dividing lines decoration to the list:
        binding.hourlyListItems.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //set adapter
        adapter = new HourlyAdapter(hourList, this);

        //binding with hourly list items
        binding.hourlyListItems.setAdapter(adapter);
        binding.hourlyListItems.setLayoutManager(new LinearLayoutManager(this));
    }


}
