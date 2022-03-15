package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private CardView helloCard, dataCard, conditionalsCard, loopsCard;
    Button logoutBtn;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//        logoutBtn=findViewById(R.id.logout_btn);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();
        helloCard = (CardView) findViewById(R.id.helloCard);
        dataCard = (CardView) findViewById(R.id.dataCard);
        conditionalsCard = (CardView) findViewById(R.id.conditionalsCard);
        loopsCard = (CardView) findViewById(R.id.loopsCard);

//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
//                alert.setMessage("Are you sure you want to logout?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                FirebaseAuth.getInstance().signOut();
//                                spEditor.putString("com.example.myapplication.loginStatus", "False");
//                                spEditor.commit();
//                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                                finish();
//                            }
//                        }).setNegativeButton("No", null);
//                AlertDialog alert1 = alert.create();
//                alert1.show();
//            }
//        });


        helloCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class));
            }
        });

        dataCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),DataTypesActivity.class));
            }
        });

        conditionalsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),ConditionalActivity.class));
            }
        });

        loopsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),LoopActivity.class));
            }
        });


    }
    private void switchActivity(int position) {
        System.out.println("Position: "+position);
        Intent switchActivityIntent;
        switch(position){
            case 0:
                switchActivityIntent = new Intent(this, IntroActivity.class);
                break;
            case 1:
                switchActivityIntent = new Intent(this, DataTypesActivity.class);
                break;
            case 2:
                switchActivityIntent = new Intent(this, ConditionalActivity.class);
                break;
            case 3:
                switchActivityIntent = new Intent(this, LoopActivity.class);
                break;
            default:
                switchActivityIntent = new Intent(this, IntroActivity.class);
        }
        startActivity(switchActivityIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settingsMenu:
                //Toast.makeText(this,"Settings selected",Toast.LENGTH_SHORT).show();
                Intent settings =new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.profileMenu:
                //Toast.makeText(this,"Profile selected",Toast.LENGTH_SHORT).show();
                Intent profile =new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.aboutMenu:
                Intent about =new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.leaderBoardMenu:
                Intent leadershipBoard= new Intent(this,LeaderBoard.class);
                startActivity(leadershipBoard);
                break;
            case R.id.logout:
                AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                alert.setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                spEditor.putString("com.example.myapplication.loginStatus", "False");
                                spEditor.commit();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();
                            }
                        }).setNegativeButton("No", null);
                AlertDialog alert1 = alert.create();
                alert1.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        finish();
//    }
}