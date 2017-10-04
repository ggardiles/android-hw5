package com.example.gabriel.hw5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SLIDESHOW = 99;

    private TextView tvNumber;
    private SeekBar seekBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare SeekBar and Textview
        seekBar  = (SeekBar)  findViewById(R.id.seekBar);
        tvNumber = (TextView) findViewById(R.id.textViewNumber);

        registerSeekBar();

        // Register button
        registerButton();

    }

    private void registerSeekBar(){

        seekBar.setMax(20);
        seekBar.setProgress(2);
        tvNumber.setText("2");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0){
                    Toast.makeText(getApplicationContext(),
                            "Changed 0s to minimum: 1s", Toast.LENGTH_SHORT).show();
                    tvNumber.setText("1");
                } else{
                    tvNumber.setText(String.format("%d", progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void registerButton(){
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seconds = Integer.parseInt(tvNumber.getText().toString());

                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("seconds", seconds);

                startActivity(intent);
            }
        });
    }
}
