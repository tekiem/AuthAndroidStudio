package com.example.administrateur.myapplication4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    private ImageView Profilepic;
    private TextView ProfilName, ProfilAge, ProfilEmail;
    private Button ProfilUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Profilepic = findViewById(R.id.IdProfilePic);
        ProfilName = findViewById(R.id.ProfilName);
        ProfilAge = findViewById(R.id.ProfilAge);
        ProfilEmail = findViewById(R.id.ProfilEmail);
        ProfilUpdate = findViewById(R.id.BtnProfileUpdate);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                ProfilName.setText("Name : "+userProfile.getUserName());
                ProfilAge.setText("Age : "+userProfile.getUserAge());
                ProfilEmail.setText("Email : "+userProfile.getUserEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfilActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT);
            }
        });
    }
}
