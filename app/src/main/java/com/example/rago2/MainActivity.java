package com.example.rago2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.rago2.Models.User;
import com.example.rago2.databinding.ActivityMainBinding;
import com.example.rago2.databinding.ActivitySplashBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
    private EditText etenterEmail;
    private ImageView imageView;
    private EditText mEtPassword;
    private EditText username;
    private int counter =5 ;
    private Button button;
    private FirebaseAuth mAuth;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we're create your Account");
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");

        binding.Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(binding.editTextTextEmailAddress.getText().toString(),binding.etPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                           progressDialog.dismiss();
                           if (task.isSuccessful())
                           {
                               Intent intent=new Intent(MainActivity.this,User_Activity.class);
                               startActivity(intent);
                           }
                            else {
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                           }}
                        });
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.getText().toString(),
                        binding.etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            User user =new User(binding.username.getText().toString(),binding.editTextTextEmailAddress.getText().toString(),binding.etPassword.getText().toString());

                            String id =task.getResult().getUser().getUid();
                            database.getReference().child("User").child(id).setValue(user);

                            Toast.makeText(MainActivity.this,"UserCreated Successfully", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");

// Write a message to the database

       toolbar=(Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       nav=(NavigationView)findViewById(R.id.nav_view);
       drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
       toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
       mEtPassword = findViewById(R.id.etPassword);
        etenterEmail = findViewById(R.id.editTextTextEmailAddress);
        imageView=findViewById(R.id.iv_view2);
        button = findViewById(R.id.Signin);
        username=findViewById(R.id.username);





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
        Button btnlogin = findViewById(R.id.Signin);

//        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
//        editor = prefManager.edit();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                validate(button.getText().toString(),mEtPassword.getText().toString());
//                String username = etenterEmail.getText().toString();
//                String password = mEtPassword.getText().toString();
//
//
//                boolean isRemember = mChRememberMe.isChecked();
//                if (isRemember) {
//                    editor.putString("USERNAME", username);
//                    editor.putString("PASSWORD", password);
//                    editor.putString("ISREMEMBER", String.valueOf(isRemember));
//
//                    editor.apply();
//                }
//                moveToUserActivity();
//            }
//        });
//    }
//
////        boolean isAlreadyLogin=prefManager.getBoolean("ISREMEMBER",true);
////        if (isAlreadyLogin){
////            moveToUserActivity();
////        }
////
////    }
//    private void validate(String userName, String userPassword) {
//        if ((userName == "Admine") && ( userPassword == "1234")){
//            Intent intent = new Intent(MainActivity.this, User_Activity.class);
//            startActivity(intent);
//        }else {
//
//            counter--;
//            if (counter==0) {
//                button.setEnabled(false);
//
//            }
//            }
//        }
//
//
//
//
//    private void moveToUserActivity() {
//        startActivity(new Intent(MainActivity.this, User_Activity.class));
//        finish();
//    }
//
//        @Override
//        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
//
//            super.onActivityResult(requestCode, resultCode, data);
//            SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
//            SharedPreferences.Editor editor;
//            editor = prefManager.edit();
//            if (requestCode == 1000) {
//                if (resultCode == Activity.RESULT_OK) {
//                    String editedUsername = data.getExtras().getString("EDITED_USERNAME");
//                    etenterEmail.setText(editedUsername);
//
//                } else {
//                    Toast.makeText(MainActivity.this, "user Cancelled operation", Toast.LENGTH_LONG).show();
//                }
//            }
        }
    }

