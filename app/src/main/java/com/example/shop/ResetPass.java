package com.example.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {
    EditText ed1;
    Button bt2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ed1=(EditText)findViewById(R.id.forEmail);
        auth=FirebaseAuth.getInstance();
        bt2=(Button)findViewById(R.id.frgetbtn);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });
    }

    private void forgotpass() {
        String resetEm;
        resetEm=ed1.getText().toString();
        if(resetEm.isEmpty()){
            Toast.makeText(this, "Pls Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            auth.sendPasswordResetEmail(resetEm).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPass.this, "Pls Check Your Email", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ResetPass.this,Login.class));
                    }
                    else {
                        Toast.makeText(ResetPass.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}