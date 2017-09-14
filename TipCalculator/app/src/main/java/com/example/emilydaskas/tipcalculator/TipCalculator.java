package com.example.emilydaskas.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Toast;

public class TipCalculator extends AppCompatActivity {

    private TextView tipTextView;
    private TextView ratingTextView;
    private EditText totalEditText;
    private Button calculateButton;
    private SeekBar ratingSeekBar;
    int progressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        this.totalEditText = (EditText) findViewById(R.id.et_total);
        this.ratingTextView = (TextView) findViewById(R.id.tv_rating);
        this.ratingSeekBar = (SeekBar) findViewById(R.id.sb_rating);
        this.calculateButton = (Button) findViewById(R.id.b_calculate);
        this.tipTextView = (TextView) findViewById(R.id.tv_tip);
        ratingTextView.setText("5/10");

        progressValue = 5;
        setUpSeekBar();
    }
    public void setUpSeekBar() {
        ratingSeekBar.setProgress(0);
        ratingSeekBar.incrementProgressBy(4);

        ratingSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress + 1;
                        ratingTextView.setText(progressValue + "/10");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ratingTextView.setText(progressValue + "/10");
                    }
                }
        );
    }

    public void calculateTip(View view){

        Double totalDouble;
        try
        {
            totalDouble = Double.parseDouble(totalEditText.getText().toString());
        }
        catch(NumberFormatException e)
        {

            totalDouble = 0.0;

            Toast.makeText(this, "Incorrect format for your Bill Total!", Toast.LENGTH_SHORT).show();


        }
        if (totalEditText.getText() != null && totalDouble != 0.0 && progressValue != 0) {


            Double tipAmount = null;
            Double tipPercent = null;

            String totalString = totalEditText.getText().toString();
            //Double totalDouble = Double.parseDouble(totalString);
            Integer rating = progressValue;

            if (rating <= 3) {
                tipPercent = .10;
            }
            else if (rating <= 5) {
                tipPercent = .13;
            }
            else if (rating <= 7) {
                tipPercent = .15;
            }
            else if (rating <= 9) {
                tipPercent = .20;
            }
            else if (rating == 10) {
                tipPercent = .25;
            }

            tipAmount = totalDouble * tipPercent;
            String tipString = new Double(tipAmount).toString();

            String formattedTip = String.format("%.2f", tipAmount);
          //  tipTextView.setText("$" + formattedTip);
            tipTextView.setText("$" + formattedTip);


        }
    }
}
