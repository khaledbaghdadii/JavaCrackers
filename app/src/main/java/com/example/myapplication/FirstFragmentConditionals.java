package com.example.myapplication;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstFragmentConditionals extends Fragment {

    public FirstFragmentConditionals() {
// Required empty public constructor
    }

    TextView textView;
    Button btnRun;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first_consitionals, container, false);
        textView=view.findViewById(R.id.exampleConditionalTextView);
        btnRun= view.findViewById(R.id.runBtnConditionals);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Young");
            }
        });
        return view;
    }

}