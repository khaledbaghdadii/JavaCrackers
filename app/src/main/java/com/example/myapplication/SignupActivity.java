package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    Button addBtn;
    EditText text_name, email_address, text_username, text_password, text_address, text_phone;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        addBtn = findViewById(R.id.add_button);
        email_address = findViewById(R.id.email_address);
        text_username = findViewById(R.id.text_username);
        text_password = findViewById(R.id.text_password);
        text_address = findViewById(R.id.text_address);
        text_phone = findViewById(R.id.text_phone);
        text_name = findViewById(R.id.editTextName);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor spEditor = sp.edit();
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_address.getText().toString().trim();
                String password = text_password.getText().toString().trim();
                String name = text_name.getText().toString().trim();
                String location = text_address.getText().toString().trim();
                String phoneNumber = text_phone.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    email_address.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    text_password.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    text_password.setError("Name is Required");
                    return;
                }
                if (password.length() < 6) {
                    text_password.setError("Password must be 6 or more characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            user = new User(name, email, phoneNumber, location, 0);
                            addDataToDatabase(user);
                            spEditor.putString("com.example.myapplication.name", name);
                            spEditor.commit();

                            spEditor.putString(getString(R.string.checkBox), "True");
                            spEditor.commit();

                            String email = email_address.getText().toString();
                            spEditor.putString("com.example.myapplication.email", email);
                            spEditor.commit();

                            String password = text_password.getText().toString().trim();
                            spEditor.putString(getString(R.string.password), password);
                            spEditor.commit();

                            String name = text_name.getText().toString().trim();
                            spEditor.putString(getString(R.string.name), name);
                            spEditor.commit();

                            String address = text_address.getText().toString().trim();
                            spEditor.putString(getString(R.string.address), address);
                            spEditor.commit();

                            String phone = text_phone.getText().toString().trim();
                            spEditor.putString(getString(R.string.phone), phone);
                            spEditor.commit();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(SignupActivity.this, "Error!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private void addDataToDatabase(User u) {
        System.out.println("adding data");
        //String key = databaseReference.child("users").push().getKey();
        String path=u.getEmail().replace(".","");
        databaseReference.child("users").child(path).setValue(u);
    }
}