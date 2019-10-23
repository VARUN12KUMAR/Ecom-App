package com.example.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    static String adm,pass1;
    EditText adminName,pass;
    Button btnAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        adminName=(EditText)findViewById(R.id.Name);
        pass=(EditText)findViewById(R.id.pass);
        btnAd=(Button) findViewById(R.id.adminbtn);

        btnAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump();
            }
        });


    }

    private void jump() {
        adm=adminName.getText().toString();
        pass1=pass.getText().toString();
        if(adm.isEmpty()||pass1.isEmpty()) {
            Toast.makeText(this, "Fill Your Detail", Toast.LENGTH_SHORT).show();
            return;

        }
        if(adm.equals("idealvillage123@#")  && pass1.equals("123454321")){
            startActivity(new Intent(Admin.this,Category.class));
        }
        else{
            Toast.makeText(this, "Wrong Name or Password" , Toast.LENGTH_SHORT).show();
        }

    }
}
