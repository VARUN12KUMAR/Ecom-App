package com.example.shop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class signup extends AppCompatActivity {

    EditText name,email,pass;
    TextView b1,b2;
    FirebaseAuth auth;
    ProgressDialog pd1;
    String na,em,pas;
    FirebaseStorage firebaseStorage;
    DatabaseReference firebaseDatabase;
    TextView admin1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
        pd1=new ProgressDialog(this);
        pd1.setCanceledOnTouchOutside(false);
        pass=(EditText)findViewById(R.id.editText3);
        b1=(TextView) findViewById(R.id.button);
        admin1=(TextView)findViewById(R.id.admin1);
        b2=(TextView) findViewById(R.id.btnLogin);
        auth= FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,Login.class));
            }
        });
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,Admin.class));

            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

    }






    private void SignIn() {

        na=name.getText().toString();
        em=email.getText().toString();
        pas=pass.getText().toString();
        if(na.isEmpty()||em.isEmpty()||pas.isEmpty()) {
            Toast.makeText(this, "fill Your Detail", Toast.LENGTH_SHORT).show();
            return;
        }

        pd1.setMessage("Registration......");
        pd1.show();
        auth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                    userData();
                    startActivity(new Intent(signup.this,Login.class));
                }
                else {
                    Toast.makeText(signup.this, "Register Not Successfull", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
    public void userData(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(auth.getUid());


        UserDataInfo userinfo= new UserDataInfo(na,em,pas);
        databaseReference.setValue(userinfo);



    }




    }




