package com.example.appcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox box01;
    private CheckBox box02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box01 = (CheckBox)findViewById(R.id.box01);
        box02 = (CheckBox)findViewById(R.id.box02);
    }
    //chamado quando algum CheckBox é escolhido
    public void acionarCheckBox(View view){
        boolean setado = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.box01:
                if(setado){
                    Toast.makeText(this,"Você Selecionou a Primeira opção ",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Você Cancelou a Primeira opção ",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.box02:
                if(setado){
                    Toast.makeText(this,"Você Selecionou a Segunda opção ",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Você Cancelou a Segunda opção ",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //chamado quando o botão é acionado
    public void acionarBotao(View view){
        boolean setop01 = box01.isChecked();
        boolean setop02 = box02.isChecked();

        Toast mensagem = null;

        if(setop01 && setop02){
            mensagem = Toast.makeText(this,"Você selecionou as DUAS opções",Toast.LENGTH_LONG);
        }else if(setop01){
            mensagem = Toast.makeText(this,"Você selecionou a Primeira opção",Toast.LENGTH_LONG);
        }else if(setop02){
            mensagem = Toast.makeText(this,"Você selecionou a Segunda opção",Toast.LENGTH_LONG);
        }else{
            mensagem = Toast.makeText(this,"Você NÃO selecionou UMA opção",Toast.LENGTH_LONG);
        }
        mensagem.show();
    }
}