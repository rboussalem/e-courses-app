package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Activity_Contenu_HTML extends AppCompatActivity {

    Intent intent,intent2;
    WebView web_view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenu_html);
        intent = getIntent();
        int a=20;
        try {
            a = Integer.parseInt(intent.getStringExtra("position")) ;
        }
        catch (Exception e)
        {}


        //Toast.makeText(getApplicationContext(),"TESTuuu :::::: "+a, Toast.LENGTH_LONG).show();
        web_view = findViewById(R.id.web_view);

        if(a >= 4 && a <= 7 ){
            Intent i = new Intent(this,Activity_Contenu_PDF.class);
            i.putExtra("name",a+"");
            startActivity(i);
        }
        else if(a >= 0 && a <= 3){
        web_view.loadUrl("file:///android_asset/"+a+".html");
        web_view.getSettings().setBuiltInZoomControls(true);}

        else if (a==20){

            web_view.loadUrl("https://sites.google.com/site/mitfsr/");
            web_view.getSettings().setBuiltInZoomControls(true);
            web_view.getSettings().setJavaScriptEnabled(true);
        }
    }
}
