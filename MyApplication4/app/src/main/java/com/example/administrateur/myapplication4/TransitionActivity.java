package com.example.administrateur.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class TransitionActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button musique,news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_transition );

        firebaseAuth = FirebaseAuth.getInstance();

        musique = (Button) findViewById( R.id.btnmusique );
        musique.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionActivity.this,SecondActivity.class));
            }
        } );

        news = (Button) findViewById( R.id.btnnews );
        news.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionActivity.this,SecondActivity.class));
            }
        } );


    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TransitionActivity.this,MainActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.profilMenu:{
                startActivity(new Intent(TransitionActivity.this,ProfilActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
