package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BemVindoActivity extends AppCompatActivity {

    TextView txtWelcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        txtWelcomeMessage = findViewById(R.id.txtBemVindo);

        Bundle args = getIntent().getExtras(); // getExtras() retorna o Bundle passado na intent
        String nome = args.getString("userName");

        txtWelcomeMessage.setText("Ei "+nome+", seja bem-vindo!");
        alert("Bem-vindo, login realizado com sucesso!");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // adiciona o botao "up navigation ou voltar"

    }

    /*Método responsável por criar nosso menu em uma determinada activity*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.activity_mainmenu, menu);
        // ou seja, passamos o caminho do nosso menu.xml, e o menu do parametro do metodo recebe esse .xml

        return super.onCreateOptionsMenu(menu); // com esse retorno, nós criamos nosso menu.xml na actionBar
    }

    /**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_refresh:
                return true;
            case R.id.menuNewBuzz:
                startActivity(new Intent(getApplicationContext(),Listar.class));
                return true;
            case R.id.menuAccounts:
                startActivity(new Intent(getApplicationContext(),Cadastro.class));
                return true;
            case R.id.menuPreferences:
                startActivity(new Intent(getApplicationContext(), Alterar.class));
                return true;
            case R.id.menuAbbout:
                finish(); // finaliza a activity atual
                return true;
        }
            return super.onOptionsItemSelected(item);
    }

    public void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}