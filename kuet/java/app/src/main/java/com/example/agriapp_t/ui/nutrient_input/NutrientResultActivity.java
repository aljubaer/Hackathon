package com.example.agriapp_t.ui.nutrient_input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriapp_t.R;

public class NutrientResultActivity extends AppCompatActivity {

    TextView Cost_urea,Cost_tsp,Cost_mop,Cost_gypsum, Required_dose_urea,Required_dose_tsp,Required_dose_mop,Required_dose_gupsum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient_result);

        Cost_urea = findViewById(R.id.cost_urea);
        Cost_tsp = findViewById(R.id.cost_tsp);
        Cost_mop = findViewById(R.id.cost_mop);
        Cost_gypsum = findViewById(R.id.cost_gypsum);

        Required_dose_urea = findViewById(R.id.required_dose_urea);
        Required_dose_tsp = findViewById(R.id.required_dose_tsp);
        Required_dose_mop = findViewById(R.id.required_dose_mop);
        Required_dose_gupsum = findViewById(R.id.required_dose_gypsum);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Required_dose_urea.setText(String.valueOf( bundle.getDouble("rn") * .46) + " kg/ha");
            Required_dose_tsp.setText(String.valueOf( bundle.getDouble("rp") *0.2)  + " kg/ha");
            Required_dose_mop.setText(String.valueOf( bundle.getDouble("rk") * 0.5)  + " kg/ha");
            Required_dose_gupsum.setText(String.valueOf( bundle.getDouble("rs") * .18)  + " kg/ha");

            Cost_urea.setText(String.valueOf( bundle.getDouble("rn") * 0.46 *20.0)  + " tk");
            Cost_tsp.setText(String.valueOf( bundle.getDouble("rp") *0.2 * 22.0 ) + " tk");
            Cost_mop.setText(String.valueOf( bundle.getDouble("rk") * .5 * 17.0) + " tk");
            Cost_gypsum.setText(String.valueOf( bundle.getDouble("rs") * .18 * 12.0) + " tk");

        }


    }

    public void onShow(View view){
        String url = "https://www.google.com/maps/search/nearest+fertilizer/@22.8994699,89.5016482,10z";

        Uri path = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, path);
        intent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, path);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onDirection(View view){

        String url = "http://maps.google.com/maps?saddr=22.8986812,89.502077&daddr=22.818955,89.563577";

        Uri path = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, path);
        intent.setPackage("com.google.android.apps.maps");
        //startActivity(intent);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, path);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }
}
