package com.mooracle.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mooracle.stormy.R;
import com.mooracle.stormy.databinding.ActivityMainBinding;
import com.mooracle.stormy.weather.Current;
import com.mooracle.stormy.weather.Forecast;
import com.mooracle.stormy.weather.Hour;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   private static final String TAG = MainActivity.class.getSimpleName();

   private Forecast forecast;
   public ImageView iconImageView;

    private double latitude = 37.8267;
    private double longitude = -122.4233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getForecast(latitude, longitude);
    }

    private void getForecast(double latitude, double longitude) {
        setContentView(R.layout.activity_main);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);
        iconImageView = findViewById(R.id.iconImageView);

        TextView darkSky = findViewById(R.id.darkSkyAttribution);
        darkSky.setMovementMethod(LinkMovementMethod.getInstance());

        String apiKey = "545ef8a2ce431db1078ae75d9a13b954";

        String forecastUrl = "https://api.darksky.net/forecast/"
                + apiKey + "/"
                + latitude + ","
                + longitude;
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {

                    try {
                        String jsonData = null;
                        if (response.body() != null) {
                            jsonData = response.body().string();
                        }
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            forecast = parseForecastData(jsonData);
                            Current current = forecast.getCurrent();
                            final Current displayWeather = new Current(
                                    current.getLocationLabel(), current.getIcon(),
                                    current.getTime(), current.getTemperature(),
                                    current.getHumidity(), current.getPercipChance(),
                                    current.getSummary(), current.getTimeZone()
                            );
                            binding.setWeather(displayWeather);
                            runOnUiThread(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void run() {
                                    Drawable drawable = getResources().getDrawable(displayWeather.getIconId(),null);
                                    iconImageView.setImageDrawable(drawable);
                                }
                            });

                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IO Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSONException caught: ", e);
                    }
                }
            });
        }
        Log.d(TAG, "Main UI code is running");
    }

    private Forecast parseForecastData(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        return forecast;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");
        Hour[] hours = new Hour[data.length()]; // initiate Hour array called hours
        // iterate all data JSONArray to fetch all data for each Hour
        for (int i = 0; i < data.length(); i++){
            JSONObject hourData = data.getJSONObject(i);

            hours[i] = new Hour();

            hours[i].setTime(hourData.getLong("time"));
            hours[i].setTimeZone(timezone);
            hours[i].setIcon(hourData.getString("icon"));
            hours[i].setSummary(hourData.getString("summary"));
            hours[i].setTemperature(hourData.getDouble("temperature"));
        }
        return hours;
    }

    private Current getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: " + timezone);
        JSONObject currently = forecast.getJSONObject("currently");
        Current current = new Current();
        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setIcon(currently.getString("icon"));
        current.setLocationLabel("Alcatraz Island, California");
        current.setPercipChance(currently.getDouble("precipProbability"));
        current.setSummary(currently.getString("summary"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setTimeZone(timezone);
        Log.d(TAG, current.getFormattedTime());
        return current;
    }

    private boolean isNetworkAvailable() {
        boolean isAvailable = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        } else {
            /*Toast.makeText(this, R.string.network_unavailable_message,
                    Toast.LENGTH_LONG).show();*/
            NetworkDialogFragment networkDialogFragment = new NetworkDialogFragment();
            networkDialogFragment.showNow(getSupportFragmentManager(),"network unavailable");
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.showNow(getSupportFragmentManager(), "error dialog");
    }

    public void refreshOnClick(View view){
        getForecast(latitude, longitude);
        Toast.makeText(this,"Refreshing Data", Toast.LENGTH_SHORT).show();
    }

    public void hourlyOnClick(View view){
        //Switch to HourlyForecastActivity
        Intent intent = new Intent(this, HourlyForecastActivity.class);
        startActivity(intent);
        //todo: put a list of hours as extra and put it inside intent
    }
}
