package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    EditText editTextName,/* editTextEmail ,*/editTextPhone,editTextAddress;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button updateBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        editTextName=findViewById(R.id.editTextName);
//        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPhone=findViewById(R.id.editTextPhone);
        editTextAddress=findViewById(R.id.editTextAddress);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor=  sp.edit();
//        editTextEmail.setText(sp.getString(getString(R.string.email), ""));
        editTextName.setText(sp.getString(getString(R.string.name), ""));
        editTextPhone.setText(sp.getString(getString(R.string.phone), ""));
        editTextAddress.setText(sp.getString(getString(R.string.address), ""));
        updateBtn=findViewById(R.id.editProfileButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= editTextName.getText().toString().trim();
                String email=sp.getString(getString(R.string.email),"").trim();
                String phone=editTextPhone.getText().toString().trim();
                String address=editTextAddress.getText().toString().trim();
                String s=sp.getString(getString(R.string.score), "0");
                int score = Integer.parseInt(s);
                User u= new User(name,email,phone,address,score);
                String path=u.getEmail().replace(".","");
                databaseReference.child("users").child(path).setValue(u);
//                spEditor.putString(getString(R.string.email), email);
                spEditor.putString(getString(R.string.name), name);
                spEditor.putString(getString(R.string.address),address);
                spEditor.putString(getString(R.string.phone),phone);
                spEditor.commit();
                Intent switchActivityIntent = new Intent(EditProfile.this, ProfileActivity.class);
                startActivity(switchActivityIntent);







            }
        });

    }
}
