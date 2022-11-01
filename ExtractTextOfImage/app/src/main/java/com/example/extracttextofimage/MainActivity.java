package com.example.extracttextofimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Bitmap imagem;
    private String localImagem;
    private File arquivoFoto = null;
    private ArrayList<String> palavras = new ArrayList<>();
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private final int IMAGE_GALERY = 200;
    //private HashMap<String,String> dadosDoUsuario = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        imageView.setOnClickListener(abrirGaleria);

    }

    public void requestPermissions(){
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, PackageManager.PERMISSION_GRANTED
        );
    }

    public HashMap<String,String> fillHashMap(){

        HashMap<String,String> hashMap = new HashMap<>();

        for(int i = 0; i < palavras.size(); i++){
            switch (palavras.get(i).toLowerCase()){
                case "nome":
                case "nome:":
                case "tipo sanguíneo:":
                case "agência:":
                case "tel. agência:":
                case "doc. identidade/ órg. emissor / uf":
                case "data nascimento":
                case "filiação":
                case "permissão":
                case "nº registro":
                case "validade":
                case "1º habilitação":
                    hashMap.put(palavras.get(i), this.palavras.get(i+1));
                break;
            }
        }

        return hashMap;
    }

    public void fillTextView(){

        String result = "";

        for(String value : palavras){
            result += value + "\n\n";
        }


        if(!result.isEmpty()) textView.setText(result);

    }

    View.OnClickListener abrirGaleria = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGalery();
        }
    };

    public void readText(){

        try{

            //String stringFileName = "/Storage/emulated/0/Download/";
            //imagem = BitmapFactory.decodeFile(localImagem);9

            TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
            Frame frameImage = new Frame.Builder().setBitmap(imagem).build();
            SparseArray<TextBlock> textBlockSparseArray = textRecognizer.detect(frameImage);

            String stringImageText = "";

            for(int i = 0; i < textBlockSparseArray.size(); i++){

                TextBlock textBlock = textBlockSparseArray.get(textBlockSparseArray.keyAt(i));
                this.palavras.add(textBlock.getValue());
                stringImageText = stringImageText + " " + textBlock.getValue();

            }

            //textView.setText(stringImageText);

        }catch(Exception e){
            textView.setText("Failed!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.openCamera:
                openCamera();
            break;
            case R.id.openGalery:
                openGalery();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMAGE_GALERY);
    }

    public File createImageFile() throws IOException{

        File image = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //image = File.createTempFile(imageFileName, ".jpg",storageDir);
        image = new File(storageDir.getPath() + File.separator + "JPG_" + timeStamp + ".jpg");

        return image;

    }

    public void openCamera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null) {

            try {

                this.arquivoFoto = createImageFile();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(this.arquivoFoto != null) {

                Uri imageUri = FileProvider.getUriForFile(getBaseContext(),
                                                          getBaseContext().getApplicationContext().getPackageName()
                                                          + ".provider", arquivoFoto);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }

        }

    }

    private void exibirImagem() {
        // Get the dimensions of the View
        int targetW = this.imageView.getWidth();
        int targetH = this.imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);

        this.imagem = bitmap; // obtém o bitmap da imagem recuperada
        this.imageView.setImageBitmap(imagem);
        this.localImagem = arquivoFoto.getAbsolutePath(); // obtem o caminho relativo até o arquivo

    }

    public Bitmap getImageRotated(Bitmap image){

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap imagemRotacionada = Bitmap.createBitmap(image,0,0,
                                                       image.getWidth(),
                                                       image.getHeight(),
                                                       matrix, true);
        return imagemRotacionada;

    }

    public boolean imageIsValid(Bitmap image){
        return image.getHeight() > image.getWidth();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //this.dadosDoUsuario.clear();
        this.palavras.clear();

        if(requestCode == IMAGE_GALERY){

            Bitmap imagem = null;

            try {

                switch (requestCode){
                    case 200:
                        Uri local = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),local);
                    break;
                }

                if(imagem != null) {

                    if(!imageIsValid(imagem)){
                        alert("\nErro\n\nTire uma nova foto com o celular \nem modo paisagem(90º virado) \n");
                        return;
                    }

                    this.imageView.setImageBitmap(imagem);
                    this.imagem = imagem; // obtém o bitmap da imagem recuperada
                    this.localImagem = data.getData().getPath(); // obtem o caminho relativo até o arquivo

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_IMAGE_CAPTURE){

            if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

                //imagem = (Bitmap) data.getExtras().get("data");

                /*
                *   Isso envia a Intent para qualquer componente em seu app que tenha sido registrado
                *   para recebê-lo. Para receber uma instância de LocalBroadcastManager, chame getInstance().
                *   Ou seja, enviamos a foto para a galeria antes de chamarmos o método de exibir imagem.
                *
                * */
                sendBroadcast(new Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(arquivoFoto)
                ));

                exibirImagem();
            }

        }

        //alert(localImagem);
        readText();
        //this.dadosDoUsuario = fillHashMap();
        fillTextView();

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void alert(String txt){
        Toast.makeText(this,txt, Toast.LENGTH_LONG).show();
    }

    public void initComponents(){
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textOfImage);
    }
}