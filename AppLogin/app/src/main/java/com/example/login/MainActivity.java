package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtUserName, txtPassword;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnSignup = findViewById(R.id.btnSignUp);

        btnSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name, password;

        name = txtUserName.getText().toString();
        password = txtPassword.getText().toString();

        if(name.equals("Bruce") && password.equals("123")){

            Intent intent = new Intent(getApplicationContext(),BemVindoActivity.class);
            Bundle parameters = new Bundle();

            parameters.putString("userName",name);
            intent.putExtras(parameters);

            startActivity(intent);

            apagaDadosCadastrais();

            txtUserName.clearFocus();
            txtPassword.clearFocus();

        } else {
            alert("Usuário ou Senha inválidos.");
        }

    }

    public void apagaDadosCadastrais(){
        txtUserName.setText("");
        txtPassword.setText("");
    }

    public void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}