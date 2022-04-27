package com.example.yalla;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class infoFragment extends Fragment {

    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
// ...

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView mEmail = (TextView)view.findViewById(R.id.textEmail);
        TextView mCreatedDate = (TextView) view.findViewById(R.id.textPassword);
        TextView mDetail = (TextView) view.findViewById(R.id.textDetail);
        Button btnLogout = (Button)view.findViewById(R.id.btnLogOut);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

//       mEmail.setText(mEmail.getText().toString() + " " + firebaseAuth.getCurrentUser().getEmail().toString());
//       mCreatedDate.setText("User ID: " + firebaseAuth.getUid().toString());
//       mDetail.setText(firebaseAuth.getCurrentUser().getPhoneNumber());

       btnLogout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it = new Intent(getActivity(),MainActivity.class);
               Toast.makeText(getActivity(),"Logout",Toast.LENGTH_SHORT).show();
               //firebaseAuth.signOut();
               startActivity(it);
           }
       });
        return view;
    }
}