package com.example.administrateur.myapplication4;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;

    private SoundPool soundPool;
    private int son1,son2,son3,son4,son5,son6;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.BtnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Logout();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool = new SoundPool(6,AudioManager.STREAM_MUSIC,0);
        }

        son1 = soundPool.load(this,R.raw.son1,1);
        son2 = soundPool.load(this,R.raw.son2,1);
        son3 = soundPool.load(this,R.raw.son3,1);
        son4 = soundPool.load(this,R.raw.son4,1);
        son5 = soundPool.load(this,R.raw.son5,1);
        son6 = soundPool.load(this,R.raw.son6,1);

    }

    public void playSound(View v){
        switch (v.getId()){
            case R.id.BtnSound1:
                soundPool.play(son1,1,1,0,0,1);
                break;
            case R.id.BtnSound2:
                soundPool.play(son2,1,1,0,0,1);
                break;
            case R.id.BtnSound3:
                soundPool.play(son3,1,1,0,0,1);
                break;
            case R.id.BtnSound4:
                soundPool.play(son4,1,1,0,0,1);
                break;
            case R.id.BtnSound5:
                soundPool.play(son5,1,1,0,0,1);
                break;
            case R.id.BtnSound6:
                soundPool.play(son6,1,1,0,0,1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool=null;
    }

    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.jacksonfive);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
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
                startActivity(new Intent(SecondActivity.this,ProfilActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
