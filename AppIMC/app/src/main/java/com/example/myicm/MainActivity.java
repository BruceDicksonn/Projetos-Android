package com.example.myicm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtAltura;
    private EditText edtPeso;
    private EditText edtIdade;
    private RadioButton btnMasc;
    private RadioButton btnFem;
    private Switch btnGestante;
    private Button btnCalcular;
    private TextView txtResultado;
    private TextView txtIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAltura = (EditText) findViewById(R.id.edtAltura);
        edtPeso = (EditText) findViewById(R.id.edtPeso);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        btnMasc = (RadioButton) findViewById(R.id.btnMasc);
        btnFem = (RadioButton) findViewById(R.id.btnFem);
        btnGestante = (Switch) findViewById(R.id.btnGestante);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtIMC = (TextView) findViewById(R.id.txtIMC);
    }

    public double calculaIMC() {
        double altura, peso, imc;
        altura = Double.valueOf(edtAltura.getText().toString());
        peso = Double.valueOf(edtPeso.getText().toString());

        return (peso / (altura * altura));
    }

    public String chamaJovem() {
        String resultado = "";

        if (calculaIMC() < 18.5) {
            resultado = "Abaixo do Peso com IMC";
        } else if (calculaIMC() > 18.5 && calculaIMC() < 24.9) {
            resultado = "Peso normal";
        } else if (calculaIMC() > 25 && calculaIMC() < 29.9) {
            resultado = "Sobrepeso";
        } else if (calculaIMC() > 30 && calculaIMC() < 34.9) {
            resultado = "Obesidade Grau 1";
        }
        return resultado;
    }

    public String chamaIdoso() {
        String resultado = "";

        if (calculaIMC() < 20) {
            resultado =  "Magreza";
        } else if (calculaIMC() > 20 && calculaIMC() < 30) {
            resultado = "Eutrófico";
        } else if (calculaIMC() > 30) {
            resultado = "Sobrepeso";
        }
        return resultado;
    }

    public String chamaGestante() {
        String resultado = "";

        if (calculaIMC() < 19.9) {
            resultado = "Baixo peso";
        } else if (calculaIMC() > 20 && calculaIMC() < 24.9) {
            resultado = "Adequado";
        } else if (calculaIMC() > 25 && calculaIMC() < 30) {
            resultado = "Sobrepeso";
        } else if (calculaIMC() > 30.1) {
            resultado = "Obesidade";
        }
        return resultado;
    }

    public void geraLaudo(View v) {
        Double imc = calculaIMC();
        String laudo = "";
        int idade = Integer.valueOf(edtIdade.getText().toString());

        if (btnMasc.isChecked() && btnGestante.isChecked()) {
            laudo = "Desmarque a opção - gestante";
        } else if (btnMasc.isChecked()) {
            if (idade > 19 && idade < 60) {
                laudo = chamaJovem();
            } else if (idade > 60) {
                laudo = chamaIdoso();
            }
        } else if (btnFem.isChecked()) {
            if (btnGestante.isChecked()) {
                laudo = chamaGestante();
            } else {
                if (idade > 19 && idade < 60) {
                    laudo = chamaJovem();
                } else if (idade > 60) {
                    laudo = chamaIdoso();
                }
            }
        }

        txtResultado.setText(laudo);
        txtIMC.setText(String.valueOf(imc.byteValue()));
    }
}