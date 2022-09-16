package com.example.appedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vincular objeto
        edtTexto = (EditText)findViewById(R.id.edtNome);
    }

    public void executar(View view){
        String texto = edtTexto.getText().toString();

        Toast msg = Toast.makeText(this,"Você escreveu: "+texto,Toast.LENGTH_LONG);
        msg.show();
    }
}