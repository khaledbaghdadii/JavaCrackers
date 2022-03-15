package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoopActivity extends AppCompatActivity {
    TabLayout tabLayout;
    Fragment fragment;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_loops);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spEditor = sp.edit();
        tabLayout = (TabLayout)findViewById(R.id.tabLayout) ;
        fragment = new FirstFragmentLoops();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment = new FirstFragmentLoops();
                int pos = tabLayout.getSelectedTabPosition();
                System.out.println("Selected tab is: "+pos);
                switch (pos) {
                    case 0:
                        System.out.println("CASE 0 REACHED");
                        fragment = new FirstFragmentLoops();
                        break;
                    case 1:
                        System.out.println("CASE 0 REACHED");
                        fragment = new SecondFragmentFor();
                        break;
                    case 2:
                        fragment = new ThirdFragmentWhile();
                        break;
                    case 3:
                        fragment = new FourthFragmentQuiz();
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
            case R.id.logout:
                AlertDialog.Builder alert = new AlertDialog.Builder(LoopActivity.this);
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
}
