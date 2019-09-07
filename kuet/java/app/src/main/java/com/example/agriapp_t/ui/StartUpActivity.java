package com.example.agriapp_t.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agriapp_t.R;
import com.example.agriapp_t.data.database.Database;
import com.example.agriapp_t.data.repository.CropRepository;

public class StartUpActivity extends AppCompatActivity {

    public static CropRepository cropRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        cropRepository = new CropRepository(getApplication());
        Button testButton = findViewById(R.id.test_btn);

    }

    public void onTest (View view) {
        cropRepository.test();
    }

    public void onTest2 (View view) {
        Intent intent = new Intent(StartUpActivity.this, CropInputActivity.class);
        startActivity(intent);
    }

    
}
