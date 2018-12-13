package com.mooracle.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mooracle.stormy.R;
import com.mooracle.stormy.adapters.HourlyAdapter;
import com.mooracle.stormy.weather.Hour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HourlyForecastActivity extends AppCompatActivity {
    //add adapter and binding variables
    private HourlyAdapter adapter;
    //private ActivityHourlyForecastBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        recyclerView = findViewById(R.id.hourlyListItems);

        //get the serialized hour data from intent
        Intent intent = getIntent();
        // TODO: fix this casting warnings!
        List<Hour> hourList = (List<Hour>) intent.getSerializableExtra("HourlyList");

       //set adapter and bind it to recycler view
        adapter = new HourlyAdapter(hourList, this);
        recyclerView.setAdapter(adapter);

        //set layout manager and put it into recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }


}
