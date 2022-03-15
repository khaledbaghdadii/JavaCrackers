package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class IntroActivity extends AppCompatActivity {
    TabLayout tabLayout;
    Fragment fragment;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    TextView tv;
    String languages = "";
    boolean ara=false;
    boolean eng = true;
    boolean fr = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_intro);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();
        tabLayout = (TabLayout)findViewById(R.id.tabLayout) ;
        fragment=new FirstFragmentIntro();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment=new FirstFragmentIntro();
                int pos = tabLayout.getSelectedTabPosition();
                System.out.println("Selected tab is: "+pos);
                switch (pos) {
                    case 0:
                        System.out.println("CASE 0 REACHED");
                        fragment = new FirstFragmentIntro();
                        break;
                    case 1:
                        System.out.println("CASE 1 REACHED");
                        fragment = new SecondFragmentIntro();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

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
                AlertDialog.Builder alert = new AlertDialog.Builder(IntroActivity.this);
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

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        languages="";
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxArabic:
                if (checked)
                    ara=true;
                else
                    ara=false;
                break;
            case R.id.checkBoxEng:
                if (checked)
                    eng=true;
                else
                    eng=false;
                break;
            case R.id.checkBoxFrench:
                if (checked)
                    fr=true;
                else
                    fr=false;
                break;
            default:
                ara=false;
                eng=true;
                fr=false;
        }

        tv=(TextView)findViewById(R.id.languages);
        if(eng)
            languages+=" english, ";
        if(fr)
            languages+=" french, ";
        if(ara)
            languages+=" arabic, ";
        tv.setText(String.valueOf("Just as you speak"+languages+" we speak java!"));
    }
}
