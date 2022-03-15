package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView totalScoreProfile;
    TextView name;
    TextView email;
    TextView phone;
    TextView address;
    Button editProfileBtn;
    SharedPreferences sp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = findViewById(R.id.profileName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        totalScoreProfile = (TextView) findViewById(R.id.totalScoreProfile);

        editProfileBtn=findViewById(R.id.editProfileBtn);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);



        String name1 = sp.getString(getString(R.string.name), "");
        name.setText(name1);
        String address1 = sp.getString(getString(R.string.address), "");
        address.setText(address1);
        String phone1 = sp.getString(getString(R.string.phone), "");
        phone.setText(phone1);
        String email1 = sp.getString(getString(R.string.email), "");
        email.setText(email1);
        totalScoreProfile.setText(sp.getString(getString(R.string.score), ""));

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivityIntent = new Intent(ProfileActivity.this, EditProfile.class);
                finish();
                startActivity(switchActivityIntent);
            }
        });

    }
    @Override
    protected void onResume() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        super.onResume();
        Log.e("resume",(sp.getString(getString(R.string.name),"")));
    }
}
