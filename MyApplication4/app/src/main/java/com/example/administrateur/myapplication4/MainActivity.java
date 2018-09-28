package com.example.administrateur.myapplication4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView info;
    private Button login;
    private int compteur = 5;
    private TextView inscription;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView ResetMDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.LabelUsername);
        Password = (EditText)findViewById(R.id.LabelPassword);
        info  = (TextView) findViewById(R.id.nombreessai);
        login  = (Button) findViewById(R.id.BtnRegister);
        inscription = (TextView) findViewById(R.id.LabelInscription);
        info.setText("Nombre d'essai : 5");
        ResetMDP = (TextView) findViewById(R.id.ForgotPassword);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InscriptionActivity.class));
            }
        });

        ResetMDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MotdepasseActivity.class));
            }
        });

    }

    private void validate (String UserName, String UserPass){

        progressDialog.setMessage("ICI C'est Paris");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(UserName,UserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                   // Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_SHORT).show();
                    compteur--;
                    info.setText("Nombre d'essai : "+compteur);
                    progressDialog.dismiss();
                    if (compteur == 0){
                        login.setEnabled(false);
                    }
                }
            }
        });

    }

    private void checkEmailVerification(){
        FirebaseUser fireBaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = fireBaseUser.isEmailVerified();

        startActivity(new Intent(MainActivity.this,SecondActivity.class));

        /*if (emailflag){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }
        else{
            Toast.makeText(MainActivity.this,"Verify Your Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }*/
    }
}
