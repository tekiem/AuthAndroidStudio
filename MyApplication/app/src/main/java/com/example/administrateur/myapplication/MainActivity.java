package com.example.administrateur.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWithListener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),R.string.bouton_with_listener,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void myOnclickBtn(View v){
        Toast.makeText(getApplicationContext(),R.string.bouton_without_listener,Toast.LENGTH_LONG).show();
    }
}
