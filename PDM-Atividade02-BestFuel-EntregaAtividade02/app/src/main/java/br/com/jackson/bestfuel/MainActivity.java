package br.com.jackson.bestfuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private double gasValue = 0.0;
    private double etValue = 0.0;
    private double total = 0.0;

    private TextView gasPriceTextView;
    private TextView etanolPriceTextView;
    private TextView bestOptionInputEditText;

    private SeekBar gasPriceSeekBar;
    private SeekBar etanolPriceSeekBar;

    private ImageView bestOptionImageView;

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasPriceTextView = findViewById(R.id.gasPriceTextView);
        etanolPriceTextView = findViewById(R.id.etanolPriceTextView);
        gasPriceSeekBar = findViewById(R.id.gasPriceSeekBar);
        etanolPriceSeekBar = findViewById(R.id.etanolPriceSeekBar);
        bestOptionImageView = findViewById(R.id.bestOptionImageView);
        bestOptionInputEditText = findViewById(R.id.bestOptionTextInputEditText);


        etanolPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    etValue = (float) progress/100;
                    etanolPriceTextView.setText(currencyFormat.format(etValue));
                    calcularValores(etValue, gasValue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        gasPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                gasValue = (float) progress/100;
                gasPriceTextView.setText(currencyFormat.format(gasValue));
                calcularValores(etValue, gasValue);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    private void atualizarGUI(double t){

        if(t >= 0.7){
            bestOptionImageView.setImageDrawable(getResources().getDrawable(R.drawable.gasolina, null));
            bestOptionInputEditText.setText(getResources().getString(R.string.best_option_gasoline));
        }
        else{

            bestOptionImageView.setImageDrawable(getResources().getDrawable(R.drawable.etanol, null));
            bestOptionInputEditText.setText(getResources().getString(R.string.best_option_ethanol));
        }

    }


    private void calcularValores(double E, double G){
        try{
            total = E / G;
            atualizarGUI(total);

        }catch (ArithmeticException e){

            bestOptionInputEditText.setText(getResources().getString(R.string.warning_best_option));
        }
    }

}
