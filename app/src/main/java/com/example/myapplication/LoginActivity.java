package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    Button signupBtn;
    EditText email_input, password_input;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CheckBox checkbox;
    public String loginStatus;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        email_input= findViewById(R.id.email_input);
        password_input= findViewById(R.id.password_input);
        checkbox=findViewById(R.id.checkBox2);
        fAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();

        checkSharedPreference();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_input.getText().toString().trim();
                String password=password_input.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    email_input.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    password_input.setError("Password is Required");
                    return;
                }

                //authenticate
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loginStatus = "True";
                            Toast.makeText(LoginActivity.this,"Logged In",Toast.LENGTH_SHORT).show();
                            if(checkbox.isChecked()){
                                spEditor.putString(getString(R.string.checkBox), "True");
                                spEditor.commit();

                                String email = email_input.getText().toString();
                                spEditor.putString(getString(R.string.email), email);
                                spEditor.commit();

                                String password = password_input.getText().toString();
                                spEditor.putString(getString(R.string.password), password);
                                spEditor.commit();

                                spEditor.putString("com.example.myapplication.loginStatus", loginStatus);
                                spEditor.commit();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }else {
                                loginStatus = "False";
                                spEditor.putString(getString(R.string.checkBox), "False");
                                spEditor.commit();

                                spEditor.putString(getString(R.string.password), "");
                                spEditor.commit();

                                spEditor.putString("com.example.myapplication.loginStatus", loginStatus);
                                spEditor.commit();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                            String email = email_input.getText().toString();
                            String path=email.replace(".","");

                            databaseReference.child("users").child(path).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    }
                                    else {

                                        //Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                                        HashMap map =(HashMap) task.getResult().getValue();
//                                        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
//                                        final User user = mapper.convertValue(map, User.class);
//                                        spEditor.putString(getString(R.string.email),user.getEmail());
//                                        spEditor.putString(getString(R.string.name),user.getName());
//                                        spEditor.putString(getString(R.string.address),user.getLocation());
//                                        spEditor.putString(getString(R.string.phone),user.getPhoneNumber());
//                                        spEditor.commit();

                                    }
                                }
                            });


                        }else{
                            Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchActivities(1);
            }
        });
    }
    private void switchActivities(int position) {
        System.out.println("Position: "+position);
        Intent switchActivityIntent;
        switch(position){
            case 0:
                switchActivityIntent = new Intent(this, HomeActivity.class);
                break;
            case 1:
                switchActivityIntent = new Intent(this, SignupActivity.class);
                break;
            default:
                switchActivityIntent = new Intent(this, IntroActivity.class);
        }
        startActivity(switchActivityIntent);
    }
    private void checkSharedPreference(){
        String checkBox = sp.getString(getString(R.string.checkBox), "False");
        String email = sp.getString(getString(R.string.email), "");
        String password = sp.getString(getString(R.string.password), "");
        String loginStatus = sp.getString("com.example.myapplication.loginStatus","");

        email_input.setText(email);
        password_input.setText(password);

        if(checkBox.equals("True")){
            checkbox.setChecked(true);
        }else{
            checkbox.setChecked(false);
        }
        if(loginStatus.equals("True")){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }else{
        }
    }

}