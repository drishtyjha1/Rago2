package com.example.rago2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Fragment_Profile#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Fragment_Profile extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    ImageView profileIv;
    TextView nameTv,emailIv,phoneIv;


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Fragment_Profile() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Fragment_Profile.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Fragment_Profile newInstance(String param1, String param2) {
//        Fragment_Profile fragment = new Fragment_Profile();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment__profile,container,false);

        firebaseAuth=firebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("profile_Users");
        // Inflate the layout for this fragment
        profileIv=view.findViewById(R.id.iv_icon);
        nameTv=view.findViewById(R.id.nameTv);
        emailIv=view.findViewById(R.id.emailTv);
        phoneIv=view.findViewById(R.id.phoneTv);

        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             for (DataSnapshot ds :snapshot.getChildren()){
                 String name=""+ds.child("name").getValue();
                 String email=""+ds.child("email").getValue();
                 String phone=""+ds.child("phone").getValue();
                 String image=""+ds.child("image").getValue();


                 nameTv.setText(name);
                 emailIv.setText(email);
                 phoneIv.setText(phone);
                try {
                    Picasso.get().load(R.drawable.ic_add_img).into(profileIv);

                }catch ( Exception e){

                    Picasso.get().load(image).into(profileIv);

                }
             }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}