package com.mooracle.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        recyclerView = findViewById(R.id.hourlyListItems);

        //get the intent
        Intent intent = getIntent();

        //get List of parcelable from intent
        List<Hour> hourList = intent.getParcelableArrayListExtra("HourlyList");

       //set adapter and bind it to recycler view
        adapter = new HourlyAdapter(hourList, this);
        recyclerView.setAdapter(adapter);

        //optimize the recyclerView performance:
        recyclerView.setHasFixedSize(true); //<-set the size of the List in recyclerView has fixed size to save memory
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //set layout manager and put it into recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }


}
