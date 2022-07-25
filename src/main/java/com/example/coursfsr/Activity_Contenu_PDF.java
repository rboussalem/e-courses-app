package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Activity_Contenu_PDF extends AppCompatActivity {


    private Intent intent;
    private PDFView pdfView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__contenu__pdf);


        intent = getIntent();
        String a = intent.getStringExtra("name");

        //file:///android_asset/"+a+".html
        pdfView = findViewById(R.id.pdf_view);
        pdfView.fromAsset(a+".pdf").load();
    }
}
