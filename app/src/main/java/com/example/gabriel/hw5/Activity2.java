package com.example.gabriel.hw5;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private int seconds;
    private int index = 0;

    private ImageView imageViewCity;
    private TextView tvTitle;
    private TextView tvSubtitle;
    private Handler slideshowHandler;

    private String[]  cityTitles  = {"Bangkok", "Madrid", "Venezia", "Atenas", "Buenos Aires",
            "Dubai", "Miami", "New York", "Paris", "St. Petersburg", "Valencia", "Vatican"};

    private String[]  cityDescriptions = {"Hangover 3 recorded here", "Best city ever!", "Beautiful canals",
            "Ancient history", "Amazing spanish accent", "Fancy cars", "The city of mansions",
            "Skyscrapper city", "City of Love", "Most beautiful city in Russia", "EXPO 2012",
            "Sacred city"};

    private Integer[] imageIds   = {R.drawable.bangkok, R.drawable.madrid, R.drawable.venezia,
            R.drawable.atenas, R.drawable.buenosaires, R.drawable.dubai, R.drawable.miami,
            R.drawable.newyork, R.drawable.paris, R.drawable.stpetersburg, R.drawable.valencia,
            R.drawable.vatican};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Get View Widgets
        tvTitle         = (TextView) findViewById(R.id.textViewTitle);
        tvSubtitle      = (TextView) findViewById(R.id.textViewSubtitle);
        imageViewCity   = (ImageView) findViewById(R.id.imageViewCity);

        // Get Slideshow info
        Intent intent = getIntent();
        seconds = intent.getIntExtra("seconds", 2); // Default to 2 seconds

        // Start slideshow
        slideshowHandler = new Handler();

    }

    private Runnable changeImageRunnable = new Runnable() {
        @Override
        public void run() {
            // Customize view
            tvTitle.setText(cityTitles[index]);
            tvSubtitle.setText(cityDescriptions[index]);
            imageViewCity.setImageResource(imageIds[index]);

            // Display Toast
            final Toast toast = Toast.makeText(getApplicationContext(),
                    String.format("Index: %d", index), Toast.LENGTH_SHORT);
            toast.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, (seconds * 1000) / 2);

            // Next City
            index = (index + 1) % imageIds.length;

            // Reprogram handler
            slideshowHandler.postDelayed(changeImageRunnable, seconds * 1000);
        }
    };

    @Override
    protected void onStop() {
        super.onPause();
        slideshowHandler.removeCallbacks(changeImageRunnable);
    }

    @Override
    protected void onStart() {
        super.onResume();
        slideshowHandler.postDelayed(changeImageRunnable, 0);
    }
}
