package com.mooracle.stormy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import okhttp3.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "545ef8a2ce431db1078ae75d9a13b954";
        double latitude = 37.8267;
        double longitude = -122.4233;
        String forecastUrl = "https://api.darksky.net/forecast/"
                + apiKey + "/"
                + latitude + ","
                + longitude;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(forecastUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    Log.v(TAG, response.body().string());
                    if (response.isSuccessful()){

                    } else {
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception caught: ", e);
                }
            }
        });

        Log.d(TAG, "Main UI code is running");
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error dialog");
    }
}
