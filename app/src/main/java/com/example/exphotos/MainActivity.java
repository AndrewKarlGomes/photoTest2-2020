package com.example.exphotos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnTakePic, btnRead;
    ImageView imgYourPicture;
    EditText edtRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePic();
        readCode();
    }

    private void readCode() {
        btnRead = (Button)findViewById(R.id.btnReading);
        edtRead = (EditText)findViewById(R.id.edtReading);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
            }
        });
    }

    private void takePic(){
        btnTakePic=(Button)findViewById(R.id.btnTakePicture); //Widget identify by id
        imgYourPicture=(ImageView)findViewById(R.id.yourImage); //Widget identify by id
        btnTakePic.setOnClickListener(new View.OnClickListener() {
            //placing the listener to start the event
            @Override
            public void onClick(View view) {
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        if(data != null){
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imgYourPicture.setImageBitmap(bitmap);
        }
        if(requestCode == 0){
            edtRead.setText(data.getStringExtra("SCAN_RESULT"));
        }
    }
}