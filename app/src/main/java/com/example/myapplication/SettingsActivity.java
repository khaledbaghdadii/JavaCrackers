package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SettingsActivity extends AppCompatActivity {
    Switch switchMode ;
    SharedPreferences sp;
    Button resetPasswordButton;
    SharedPreferences.Editor spEditor;
    EditText oldPassEditText,newPassEditText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        switchMode=findViewById(R.id.switchMode);
        resetPasswordButton=findViewById(R.id.changePass);
        oldPassEditText=findViewById(R.id.oldPassEditText);
        newPassEditText=findViewById(R.id.newPassEditText);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();
        checkSharedPreferences();
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchMode.isChecked()) {
                    spEditor.putString(getString(R.string.darkMode), "True");
                    spEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    spEditor.putString(getString(R.string.darkMode), "False");
                    spEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////start of on click

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email=user.getEmail();
                String oldPass=oldPassEditText.getText().toString();
                String newPass= newPassEditText.getText().toString();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(email, oldPass);

// Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Reset Password", "Password updated");
                                                Toast.makeText(SettingsActivity.this,"Password updated successfully",Toast.LENGTH_SHORT);
                                            } else {
                                                Log.d("Reset Password", "Error password not updated");
                                                Toast.makeText(SettingsActivity.this,"Password failed to update",Toast.LENGTH_SHORT);
                                            }
                                        }
                                    });
                                } else {
                                    Log.d("Reset Password", "Error auth failed");
                                    Toast.makeText(SettingsActivity.this,"Failed. Check old password!",Toast.LENGTH_SHORT);
                                }
                            }
                        });


                //end of on click
            }
        });


    }

    private void checkSharedPreferences() {
        String check = sp.getString(getString(R.string.darkMode), "");
        if(check.equals("True")){
            switchMode.setChecked(true);
        }else{
            switchMode.setChecked(false);
        }
    }



}