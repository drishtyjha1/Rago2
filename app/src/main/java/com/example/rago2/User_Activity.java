package com.example.rago2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rago2.databinding.ActivityUserBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.rago2.R.*;

public class User_Activity extends AppCompatActivity {
    ActivityUserBinding binding;
    FirebaseAuth auth;
    NavigationView navi;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        toolbar = (Toolbar) findViewById(id.toolbar1);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(id.drawer2);
        navi = (NavigationView) findViewById(id.nav_view2);




        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_profile:
                        Toast.makeText(getApplicationContext(), "profile panel is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_edit:
                        Toast.makeText(getApplicationContext(), "edit panel is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "setting panel is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:

                        auth.signOut();
                        Toast.makeText(User_Activity.this, "you have successfully logout", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(User_Activity.this, MainActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;



                }
                return true;
            }

        });


//        Button btnlogout = findViewById(R.id.btn_logout);


//

//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor.putString("USERNAME", "");
//                editor.putString("PASSWORD", "");
//                editor.putString("ISREMEMBER", String.valueOf(false));
//                editor.apply();
//
//                Toast.makeText(User_Activity.this, "you have successfully logout", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(User_Activity.this, MainActivity.class));
//                finish();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                startActivity(new Intent(User_Activity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "profile panel is open", Toast.LENGTH_LONG).show();


                break;
            case R.id.menu_edit:
                Toast.makeText(getApplicationContext(), "edit panel is open", Toast.LENGTH_LONG).show();

                break;

            case R.id.settings:
                Toast.makeText(User_Activity.this, "Setting Click", Toast.LENGTH_LONG).show();
                break;

            case R.id.logout:

                auth.signOut();
                Toast.makeText(User_Activity.this, "you have successfully logout", Toast.LENGTH_LONG).show();
                startActivity(new Intent(User_Activity.this, MainActivity.class));
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
