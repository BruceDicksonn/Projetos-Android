package com.example.appcombustivel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText txtNumeroLitros, txtPrecoLitro;
    TextView tv_resposta, tv_valorASerPago;
    Spinner formasDePagamento, tiposDeCombustiveis;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumeroLitros = findViewById(R.id.txtNumeroDeLitros);
        txtPrecoLitro = findViewById(R.id.txtPrecoDoLitro);
        tv_resposta = findViewById(R.id.tv_resposta);
        tv_valorASerPago = findViewById(R.id.tv_valorASerPago);
        formasDePagamento = findViewById(R.id.spinnerFormasDePagamento);
        tiposDeCombustiveis = findViewById(R.id.spinnerTiposDeCombustiveis);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(this::onclick);

    }

    public void onclick(View v){

        if(!verificaCamposVazios()){

            Double valorDoLitro = Double.parseDouble(txtPrecoLitro.getText().toString());
            Double numeroLitros = Double.parseDouble(txtNumeroLitros.getText().toString());
            Double resultado = 0.0;

            /* explicar o porquê eu botei os if's aninhados */
            if(tipoCombustivelEscolhido(numeroLitros,valorDoLitro) != -1){

                resultado = tipoCombustivelEscolhido(numeroLitros,valorDoLitro);

                if(formaDePagamentoEscolhida(resultado) != -1){

                    resultado = formaDePagamentoEscolhida(resultado);

                    tv_valorASerPago.setVisibility(View.VISIBLE);
                    tv_resposta.setText(monetario(resultado).toString());
                    tv_resposta.setVisibility(View.VISIBLE);

                } else {
                    alert("Escolha uma forma de pagamento");
                }

            } else {
                alert("Escolha um tipo de combustível");
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_refresh,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_refresh:
                limparDados();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Double tipoCombustivelEscolhido(Double numeroLitros, Double valorLitro){
        Double resultado = 0.0;
        switch (tiposDeCombustiveis.getSelectedItemPosition()){
            case 0:
                return -1.0;
            case 1:
                resultado = calcularValorAlcool(numeroLitros,valorLitro);
                break;
            case 2:
                resultado = calcularValorGasolina(numeroLitros,valorLitro);
                break;
            case 3:
                resultado = calcularValorDiesel(numeroLitros,valorLitro);
                break;
        }
        return resultado;
    }

    public Double formaDePagamentoEscolhida(Double totalAPagar){

        switch (formasDePagamento.getSelectedItemPosition()){
            case 0:
                return -1.0;
            case 1:
                totalAPagar = aplicarDescontoDebito(totalAPagar);
                break;
            case 2:
                totalAPagar = aplicarAcrescimoCredito(totalAPagar);
                break;
        }

        return totalAPagar;
    }

    public void alert(String  message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public Double calcularValorAlcool(Double numeroLitros, Double valorLitro){
        Double total = valorLitro*numeroLitros;
        Double desconto = 0.0;
        if(numeroLitros <= 20){
            desconto = (valorLitro*0.03)*numeroLitros;
            return total - desconto;
        } else if(numeroLitros > 20){
            desconto = (valorLitro*0.05)*numeroLitros;
            return total - desconto;
        }
        return 0.0;
    }

    public Double calcularValorGasolina(Double numeroLitros,Double valorLitro){
        Double total = valorLitro*numeroLitros;
        Double desconto = 0.0;
        if(numeroLitros <= 20){
            desconto = (valorLitro*0.04)*numeroLitros;
            return total - desconto;
        } else if(numeroLitros > 20){
            desconto = (valorLitro*0.06)*numeroLitros;
            return total - desconto;
        }
        return 0.0;
    }

    public Double calcularValorDiesel(Double numeroLitros, Double valorLitro){
        Double total = valorLitro*numeroLitros;
        Double desconto = 0.0;
        if(numeroLitros <= 20){
            desconto = (valorLitro*0.07)*numeroLitros;
            return total - desconto;
        } else if(numeroLitros > 20){
            desconto = (valorLitro*0.09)*numeroLitros;
            return total - desconto;
        }
        return 0.0;
    }

    public Double aplicarDescontoDebito(Double valor){
        Double desconto = valor*0.05;
        return valor-desconto;
    }

    public Double aplicarAcrescimoCredito(Double valor){
        Double acrescimo = valor * 0.05;
        return valor+acrescimo;
    }

    public boolean verificaCamposVazios(){
        if(txtNumeroLitros.getText().toString().length() == 0 || txtPrecoLitro.getText().toString().length() == 0){
            alert("Preencha os campos vazios!");
            return true;
        }
        return false;
    }

    public String monetario(Double valor){
        return NumberFormat.getCurrencyInstance(new Locale("pt","br")).format(valor);
    }

    public void limparDados(){
        txtNumeroLitros.setText("");
        txtNumeroLitros.clearFocus();
        txtPrecoLitro.setText("");
        txtPrecoLitro.clearFocus();
        formasDePagamento.setSelection(0);
        tiposDeCombustiveis.setSelection(0);
        tv_resposta.setText("");
        tv_resposta.setVisibility(View.GONE);
        tv_valorASerPago.setVisibility(View.GONE);
    }
}