package com.example.apicamera;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView foto;
    static String currentPhotoPath;
    private static final int SELECAO_GALERIA = 200;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto = findViewById(R.id.foto);

    }

    private void abrirCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    private void abrirGaleriaIntent(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

/*        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem rs"),PICK_IMAGE);
*
*         createChooser(Intent,String msg, Action)
*
*         permite que voce altere a mensagem que aparecera na janela
*         de seleção da galeria ou explorer.
*
* */

        startActivityForResult(intent, SELECAO_GALERIA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if(resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");

            foto.setImageBitmap(imagem);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnOpenCamera:
                 abrirCameraIntent();
            break;
            case R.id.btnOpenGalery:
                abrirGaleriaIntent();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}