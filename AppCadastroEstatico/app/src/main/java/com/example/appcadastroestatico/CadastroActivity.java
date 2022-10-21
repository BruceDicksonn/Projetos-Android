package com.example.appcadastroestatico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appcadastroestatico.DAO.DaoDados;
import com.example.appcadastroestatico.MODEL.Dados;

import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome,email,senha;
    private Button btnSalvar;
    private CircleImageView foto;
    private final int selecao_galeria = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        initComponents();
        btnSalvar.setOnClickListener(logar);
        foto.setOnClickListener(escolherFOtoPerfil);
    }

    View.OnClickListener escolherFOtoPerfil = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGalery();
        }
    };

    View.OnClickListener logar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            logar();
        }
    };

    public void openGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,selecao_galeria);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == selecao_galeria){

            Bitmap imagem = null;

            try {

                switch (requestCode){
                    case selecao_galeria:

                        Uri localImagem = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagem);

                    break;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            if(imagem != null) foto.setImageBitmap(imagem);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean validacaoInputs(){

        String txtNome  = nome.getText().toString();
        String txtEmail = email.getText().toString();
        String txtSenha = senha.getText().toString();


        if(txtNome.isEmpty() || txtEmail.isEmpty() || txtSenha.isEmpty()){

            alert("Preencha os campos vazios!");
            return false;

        }
            return true;
    }

    private void alert(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    private void logar(){

          if(validacaoInputs()){

              Intent intent = new Intent(this, MainActivity.class);
              Bundle extras = new Bundle();

              String txtNome  = nome.getText().toString();
              String txtEmail = email.getText().toString();
              String txtSenha = senha.getText().toString();

              extras.putCharSequence("nome",txtNome);
              extras.putCharSequence("email",txtEmail);
              extras.putCharSequence("senha",txtSenha);

              DaoDados.addData(new Dados(
                   foto,
                   nome.getText().toString(),
                   email.getText().toString(),
                   senha.getText().toString()
              ));

              intent.putExtras(extras);
              travelToMainActivity(intent);

          }

    }

    private void travelToMainActivity(Intent intent){
        startActivity(intent);
    }

    private void initComponents(){
        nome  = findViewById(R.id.cadTxtNome);
        email = findViewById(R.id.cadTxtEmail);
        senha = findViewById(R.id.cadTxtSenha);
        foto = findViewById(R.id.cadFotoPerfil);
        btnSalvar = findViewById(R.id.cadBtnSalvar);
    }
}