package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTextView, totalScoreTextView;
    public static String quizName;
    private Button done;
    public static int totalScore;
    private DatabaseReference mDatabase;
    SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    @Override
    public void onBackPressed() {
        Intent switchActivity = new Intent(ScoreActivity.this, HomeActivity.class);
        startActivity(switchActivity);

        //disables the user from going back
        //moveTaskToBack(false);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();
        MediaPlayer ring= MediaPlayer.create(ScoreActivity.this,R.raw.sound);
        ring.start();
        scoreTextView = (TextView) findViewById(R.id.scoreText);
        switch(quizName){
            case "DataPrimitive":
                scoreTextView.setText(""+SecondFragmentData.mScore);
                break;
            case "Introduction":
                scoreTextView.setText(""+SecondFragmentIntro.mScore1);
                break;
            case "Conditionals":
                scoreTextView.setText(""+SecondFragmentConditionals.mScore2);
                break;
            case "Loops":
                scoreTextView.setText(""+FourthFragmentQuiz.mScore3);
                break;
            default:
                scoreTextView.setText(""+0);
        }




        totalScore = SecondFragmentData.mScore + SecondFragmentIntro.mScore1
                + SecondFragmentConditionals.mScore2 + FourthFragmentQuiz.mScore3;



        totalScoreTextView = (TextView) findViewById(R.id.totalScore);
        totalScoreTextView.setText(""+totalScore);
        updateScore();

        done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent switchActivity = new Intent(ScoreActivity.this, HomeActivity.class);
                startActivity(switchActivity);
            }
        });



    }
    public void updateScore(){
        String path=sp.getString(getString(R.string.email),"").replace(".","");
        spEditor.putString(getString(R.string.score),totalScore+"");
        spEditor.commit();
        mDatabase.child("users").child(path).child("score").setValue(totalScore);

    }

}
