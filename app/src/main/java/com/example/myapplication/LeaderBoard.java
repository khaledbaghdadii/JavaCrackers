package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class LeaderBoard extends AppCompatActivity {
    private ListView users;
    private List<HashMap> userList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_board);
        users= findViewById(R.id.users);
        userList=new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
//                    HashMap list= (HashMap) task.getResult().getValue();
//                    final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
//                    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//                    userList = mapper.convertValue(list, List.class);
//                    System.out.println(userList);
//                    for(HashMap map:userList){
//                        Map<String,String> mapp = new HashMap<>();
//                        Map.Entry<String,String> entry = (Map.Entry<String, String>) map.entrySet().iterator().next();
//                        String key = entry.getKey();
//                        String value = entry.getValue();
//
//
//
//                    }


                }
            }
        });


    }

}
