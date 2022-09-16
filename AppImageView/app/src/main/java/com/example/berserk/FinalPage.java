package com.example.berserk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalPage extends AppCompatActivity {

    Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        avancar = findViewById(R.id.btnAvancar);

        Intent backPage = new Intent(getApplicationContext(), Page232.class);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(backPage);
            }
        });

    }
}