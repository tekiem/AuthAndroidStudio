package com.example.administrateur.myapplication4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InscriptionActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail, UserAge;
    private Button Btnsetregister;
    private TextView userLogin;
    private ImageView ImageAndroid;
    private FirebaseAuth firebaseAuth;
    String age,name, password,email;


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
                              // Toast.makeText(InscriptionActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                              // startActivity(new Intent(InscriptionActivity.this,MainActivity.class));
                               //sendEmailVerification();
                               sendUserData();
                               firebaseAuth.signOut();
                               Toast.makeText(InscriptionActivity.this,"Vous êtes bien enregistré, les informations ont été enregistré !",Toast.LENGTH_SHORT).show();
                               finish();
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
        UserAge = (EditText) findViewById(R.id.Age);
        ImageAndroid = (ImageView) findViewById(R.id.ImageViewAndroid);
    }

    private Boolean validate(){
        Boolean result = false;

        name = UserName.getText().toString();
        password = UserPassword.getText().toString();
        email = UserEmail.getText().toString();
        age = UserAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()){
            Toast.makeText(this,"Vous n'avez pas rentré tout les champs",Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(InscriptionActivity.this,"Vous êtes bien enregistré, un mail vous a été envoyé",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(InscriptionActivity.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(InscriptionActivity.this,"le mail n'a pas été envoyé",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(age,email,name);
        myRef.setValue(userProfile);
    }
}
