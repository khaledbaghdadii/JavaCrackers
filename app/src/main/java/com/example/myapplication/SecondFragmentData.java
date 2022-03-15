package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class SecondFragmentData extends Fragment {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private String mAnswer;
    public static int mScore = 0;
    private int mQuestionNumber = 0;

    public SecondFragmentData() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second_data, container, false);

        mScoreView = (TextView) rootView.findViewById(R.id.totalScore);
        mQuestionView = (TextView) rootView.findViewById(R.id.question);
        mButtonChoice1 = (Button) rootView.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) rootView.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) rootView.findViewById(R.id.choice3);

        mScore = 0;

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity(),"Wrong!",Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(getActivity(),"Wrong!",Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"Wrong!",Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button3
        return rootView;
    }

    private void updateQuestion(){
        if(mQuestionNumber<4){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);

            mQuestionNumber++;
        }else{
            ScoreActivity.quizName = "DataPrimitive";
            Intent switchActivity = new Intent(getActivity(), ScoreActivity.class);
            startActivity(switchActivity);


        }
    }


    private void updateScore(int point) {
        mScoreView.setText("" + point);
    }
}