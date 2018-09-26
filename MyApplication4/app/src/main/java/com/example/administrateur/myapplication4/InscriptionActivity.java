package com.example.administrateur.myapplication4;

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

public class InscriptionActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button Btnsetregister;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        Btnsetregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (validate()){

                   String user_email = UserEmail.getText().toString().trim();
                   String user_password = UserPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(InscriptionActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(InscriptionActivity.this,MainActivity.class));
                           }
                            else{
                               Toast.makeText(InscriptionActivity.this,"Registration Fail",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InscriptionActivity.this,MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        UserName = (EditText)findViewById(R.id.SetUserName);
        UserPassword = (EditText)findViewById(R.id.labelMDP);
        UserEmail  = (EditText) findViewById(R.id.LabelEmail);
        Btnsetregister = (Button) findViewById(R.id.BtnRegister);
        userLogin = (TextView) findViewById(R.id.LabelSeconnecter);
    }

    private Boolean validate(){
        Boolean result = false;

        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(this,"Vous n'avez pas rentré tout les champs",Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }
}
