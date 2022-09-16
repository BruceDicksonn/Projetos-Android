package com.example.appspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
    }
    public void verificaOpcao(View view){

        String texto = String.valueOf(spinner.getSelectedItemId());
        int posicao = spinner.getSelectedItemPosition();

        Toast.makeText(this,"Você selecionou a " + texto + " de indice " + posicao, Toast.LENGTH_SHORT ).show();
    }
}