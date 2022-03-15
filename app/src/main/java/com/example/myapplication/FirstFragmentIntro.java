package com.example.myapplication;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstFragmentIntro extends Fragment {
    View rootView;
    Button runBtn, testBtn;
    TextView output, outputTest, outputTest2, welldone;
    EditText nameTest ;
    public FirstFragmentIntro() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_first_intro, container, false);
        runBtn = (Button) rootView.findViewById(R.id.runBtn);
        output=(TextView) rootView.findViewById(R.id.output);
        testBtn = (Button) rootView.findViewById(R.id.testBtn);
        outputTest=(TextView) rootView.findViewById(R.id.outputTest);
        outputTest2=(TextView) rootView.findViewById(R.id.outputTest2);
        welldone = (TextView) rootView.findViewById(R.id.welldone);
        nameTest = (EditText)rootView.findViewById(R.id.nameTest);
        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Welcome to our application!");
                welldone.setText("Well done! You just ran your first java program!");
            }
        });
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputTest.setText("Output: " + String.valueOf(nameTest.getText()));
                outputTest2.setText("Well done! Now it's time to test your knowledge!");
            }
        });
        return rootView;
    }



}