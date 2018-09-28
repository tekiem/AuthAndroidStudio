package com.example.administrateur.myapplication4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MotdepasseActivity extends AppCompatActivity {

    private EditText EmailMdp;
    private Button BtnResetMdp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motdepasse);

        EmailMdp = (EditText)findViewById(R.id.EmailPassword);
        BtnResetMdp = (Button)findViewById(R.id.BtnesetMDP);
        firebaseAuth = FirebaseAuth.getInstance();

        BtnResetMdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = EmailMdp.getText().toString().trim();
                if (useremail.equals("")){
                    Toast.makeText(MotdepasseActivity.this,"Entrez votre Email !",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MotdepasseActivity.this,"Le mot de passe vous a été envoyé par email",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(MotdepasseActivity.this,MainActivity.class));
                            }
                            else {
                                Toast.makeText(MotdepasseActivity.this,"Erreur lors de l'envoi du mot de passe par email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
