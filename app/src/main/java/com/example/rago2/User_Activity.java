package com.example.rago2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class User_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        Button btnlogout =findViewById(R.id.btn_logout);

        SharedPreferences sharedpref=getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE );
        final SharedPreferences.Editor editor = sharedpref.edit();
        String username=sharedpref.getString("USERNAME","");


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("USERNAME","");
                editor.putString("PASSWORD","");
                editor.putString("ISREMEMBER", String.valueOf(false));
                editor.apply();

                Toast.makeText(User_Activity.this,"you have successfully logout",Toast.LENGTH_LONG).show();
                startActivity(new Intent(User_Activity.this,MainActivity.class));
                finish();
            }
        });
    }
}