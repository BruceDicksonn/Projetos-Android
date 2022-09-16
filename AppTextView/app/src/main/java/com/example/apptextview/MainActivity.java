package com.example.apptextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView saida;
    private EditText usuario, senha;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vinculando objetos
        saida = (TextView) findViewById(R.id.txtSaida);
        usuario = (EditText) findViewById(R.id.edtLogin);
        senha = (EditText)findViewById(R.id.edtSenha);

        contador = 0;

    }
    public void calcular(View view){
        contador++;

        Toast.makeText(this,"Usuário = "+usuario.getText(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Senha = "+senha.getText(),Toast.LENGTH_LONG).show();

        saida.setText("Você acionou o botão: "+contador + " vezes");
    }
}