package com.example.rago2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private EditText etenterEmail;
    private SharedPreferences.Editor editor;
    private EditText mEtPassword;
    private CheckBox mChRememberMe;


    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       toolbar=(Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       nav=(NavigationView)findViewById(R.id.nav_view);
       drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
       toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();

       nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId()){
                   case R.id.menu_home:
                       Toast.makeText(getApplicationContext(),"Home panel is open",Toast.LENGTH_LONG).show();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;

                   case R.id.menu_edit:
                       Toast.makeText(getApplicationContext(),"Edit panel is open",Toast.LENGTH_LONG).show();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;

               }
               return true;
           }
       });

        mEtPassword = findViewById(R.id.etPassword);
        etenterEmail = findViewById(R.id.editTextTextEmailAddress);
        mChRememberMe = findViewById(R.id.chk_remember);
        Button btnlogin = findViewById(R.id.button);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
        editor = prefManager.edit();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etenterEmail.getText().toString();
                String password = mEtPassword.getText().toString();


                boolean isRemember = mChRememberMe.isChecked();
                if (isRemember) {
                    editor.putString("USERNAME", username);
                    editor.putString("PASSWORD", password);
                    editor.putString("ISREMEMBER", String.valueOf(isRemember));

                    editor.apply();
                }
                moveToUserActivity();
            }
        });
//        boolean isAlreadyLogin=prefManager.getBoolean("ISREMEMBER",true);
//        if (isAlreadyLogin){
//            moveToUserActivity();
//        }

    }
    private void moveToUserActivity() {
        startActivity(new Intent(MainActivity.this, User_Activity.class));
        finish();
    }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){

            super.onActivityResult(requestCode, resultCode, data);
            SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefManager.edit();
            if (requestCode == 1000) {
                if (resultCode == Activity.RESULT_OK) {
                    String editedUsername = data.getExtras().getString("EDITED_USERNAME");
                    etenterEmail.setText(editedUsername);

                } else {
                    Toast.makeText(MainActivity.this, "user Cancelled operation", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

