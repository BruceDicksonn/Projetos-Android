package com.example.appbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void acionar(View view){
        Toast.makeText(this,"Você acionou o botão",Toast.LENGTH_LONG).show();
    }
    public void imprimirMensagem(View v){
        TextView tv = (TextView)findViewById(R.id.txtMessage);
        tv.setText("Hello, World!");
    }
}