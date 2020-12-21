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
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rago2.Models.All_UserMember;
import com.example.rago2.Models.Booking_UsersMember;
import com.example.rago2.databinding.ActivityUserBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static com.example.rago2.R.*;

public class User_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityUserBinding binding;
    FirebaseAuth auth;
    NavigationView navi;
    EditText etName,etAddress,etLandArea,etDate,etPhone,etBookingId;
    Button btnBooking;
    Spinner spinner;
    ActionBarDrawerToggle toggle;
    ProgressBar progressBar;
    UploadTask uploadTask;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    Booking_UsersMember bookingUsersMember;
    String currentUserId;

    Toolbar toolbar;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bookingUsersMember=new Booking_UsersMember();
        auth = FirebaseAuth.getInstance();
        toolbar = (Toolbar) findViewById(id.toolbar1);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(id.drawer2);
        navi = (NavigationView) findViewById(id.nav_view2);
        etName=findViewById(R.id.et9);
        etAddress=findViewById(R.id.et5);
        etLandArea=findViewById(R.id.et2);
        etDate=findViewById(R.id.et4);
        etPhone=findViewById(R.id.et7);
        etBookingId=findViewById(R.id.et6);
        btnBooking=findViewById(id.button4);

        spinner=findViewById(id.et3);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = user.getUid();
        documentReference = db.collection("user").document(currentUserId);
        databaseReference = database.getReference("Booking Users");

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, array.crops, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

btnBooking.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uploadData();
    }
});

        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_profile:
                        startActivity(new Intent(User_Activity.this, Profile1.class));
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

    private void uploadData() {
        final String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String landArea = etLandArea.getText().toString();
        String date = etDate.getText().toString();
        String phone = etPhone.getText().toString();

        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(address) ||!TextUtils.isEmpty(landArea)
                ||!TextUtils.isEmpty(date) ||!TextUtils.isEmpty(phone) ){
         Task<Uri> urlTask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
             @Override
             public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
              if (!task.isSuccessful()){
                  throw task.getException();
              }
                 return null;
             }
         })  .addOnCompleteListener(new OnCompleteListener<Uri>() {
             @Override
             public void onComplete(@NonNull Task<Uri> task) {

                 if (task.isSuccessful()){
                     Uri downloadUri =task.getResult();

                     Map<String,String> Booking = new HashMap<>();
                     Booking.put("name",name);

                 }
             }
         });


        }

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
                startActivity(new Intent(User_Activity.this, Profile1.class));
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
