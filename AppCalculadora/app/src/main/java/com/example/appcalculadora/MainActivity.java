package com.example.appcalculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText txtPriNumero, txtSegNumero, txtResultado;
    private Button btnCalcular, btnLimpar;
    private RadioButton radSoma, radSubt, radMult, radDiv, radSetado;
    private RadioGroup radGroup;
    private Boolean radOperacoesSetado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPriNumero = (EditText)findViewById(R.id.edPriNumero);
        txtSegNumero = (EditText)findViewById(R.id.edSegNumero);
        txtResultado = (EditText)findViewById(R.id.edResultado);

        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnLimpar = (Button)findViewById(R.id.btnLimpar);

        radSoma = (RadioButton) findViewById(R.id.radSomar);
        radSubt = (RadioButton) findViewById(R.id.radSubtrair);
        radMult = (RadioButton) findViewById(R.id.radMultiplicar);
        radDiv = (RadioButton) findViewById(R.id.radDividir);

        radGroup = (RadioGroup) findViewById(R.id.radGroupOperacoes);

        /* aplicar um onClick para cada child do radioGroup */

        for(int id = 0; id < radGroup.getChildCount(); id++){
            RadioButton currentRadio = (RadioButton) radGroup.getChildAt(id);
            currentRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       radOperacoesSetado = true;
                       radSetado = currentRadio;
                }
            });
        }


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*se os campos estiverem vazios*/
                if(txtPriNumero.getText().toString().length() == 0 || txtSegNumero.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this, "Preencha os campos vazios!", Toast.LENGTH_SHORT).show();
                } else {

                    Double valor1 = Double.parseDouble(txtPriNumero.getText().toString());
                    Double valor2 = Double.parseDouble(txtSegNumero.getText().toString());
                    String operacaoEscolhida = "";
                    String resultado = "";
                    String tipoPrimitivoResultado;


                    /* se houver ou não uma operação selecionada*/
                    if (!radOperacoesSetado) {
                        Toast.makeText(MainActivity.this, "Escolha uma operação", Toast.LENGTH_SHORT).show();
                    } else {

                        operacaoEscolhida = radSetado.getText().toString().toLowerCase();
                        switch (operacaoEscolhida) {
                            case "somar":
                                resultado = soma(valor1, valor2).toString();
                                break;
                            case "subtrair":
                                resultado = subtracao(valor1, valor2).toString();
                                break;
                            case "multiplicar":
                                resultado = multiplicacao(valor1, valor2).toString();
                                break;
                            case "dividir":
                                if (valor2 == 0.0 || valor2 == 0.0) {
                                    resultado = "Não é possível dividir por zero";
                                } else {
                                    resultado = divisao(valor1, valor2).toString();
                                }
                                break;
                        }


                        /* verificar tipo primitivo de resultado */

                        tipoPrimitivoResultado = verificaEAtribuiTipoPrimitivoAdequado(resultado);


                        if (tipoPrimitivoResultado.equals("Integer")) {
                            Double resposta = Double.parseDouble(resultado);
                            String respostaFinal = String.valueOf(resposta.intValue());
                            txtResultado.setText(respostaFinal);
                        } else if (tipoPrimitivoResultado.equals("Double")) {
                            txtResultado.setText(resultado);
                        } else {
                            txtResultado.setText(resultado);
                        }


                    }
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPriNumero.setText("");
                txtSegNumero.setText("");
                txtResultado.setText("");
                desmarcaRadioChecked();
                txtSegNumero.clearFocus();
            }
        });

    }

    private void desmarcaRadioChecked(){

        if(radOperacoesSetado){
            radOperacoesSetado = false;
            radSetado.setChecked(false);
        }
    }

    private String verificaEAtribuiTipoPrimitivoAdequado(String value){

        String penultimaLetra;
        String ultimaLetra;
        String duasUltimasLetras = "";

        if(value.equals("Não é possível dividir por zero")){
             return value;
        } else if(value.length() >= 2){
             ultimaLetra = value.substring(value.length()-1, value.length());
             penultimaLetra = value.substring(value.length()-2,value.length()-1);
             duasUltimasLetras = penultimaLetra+ultimaLetra;
        } else {
            return "Double";
        }

        if(duasUltimasLetras.equals(".0")){
            return "Integer";
        } else {
            return "Double";
        }

    }

    /*Quatro formas possíveis de atribuição de tipos nos campos*/
    private int soma(int x, int y){return x + y;}
    private Double soma(Double x, Double y){return x + y;}
    private Double soma(int x, Double y){return x + y;}
    private Double soma(Double x, int y){return x + y;}


    private int subtracao(int x, int y){return x - y;}
    private Double subtracao(Double x, Double y){return x - y;}
    private Double subtracao(int x, Double y){return x - y;}
    private Double subtracao(Double x, int y){return x - y;}

    private int multiplicacao(int x, int y){return x * y;}
    private Double multiplicacao(Double x, Double y){return x * y;}
    private Double multiplicacao(int x, Double y){return x * y;}
    private Double multiplicacao(Double x, int y){return x * y;}

    private int divisao(int x, int y){return x / y;}
    private Double divisao(Double x, Double y){return x / y;}
    private Double divisao(int x, Double y){return x / y;}
    private Double divisao(Double x, int y){return x / y;}


}