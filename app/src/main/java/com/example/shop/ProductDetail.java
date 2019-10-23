package com.example.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.pm.PackageInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.shop.Model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetail extends AppCompatActivity {

    private Button addtocart;
    private ImageView img1;
    private ElegantNumberButton elegantNumberButton;
    private  TextView ProdName,ProdDisc,ProdPrice;
    private String prodId="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        prodId=getIntent().getStringExtra("pid");
        addtocart=(Button)findViewById(R.id.addtocart);



//        btn=(FloatingActionButton)findViewById(R.id.btn_cart);
        img1=(ImageView)findViewById(R.id.imgdetail);
        elegantNumberButton=(ElegantNumberButton)findViewById(R.id.addcartprod);
        ProdName=(TextView)findViewById(R.id.prodDetName);
        ProdDisc=(TextView)findViewById(R.id.ProddesDtl);
        ProdPrice=(TextView)findViewById(R.id.priceDtl);
        getProductdetail(prodId);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });



    }

    private void addToCart() {
        String saveCurretTime,saveCurrentDate;
        Calendar cartDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(cartDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurretTime = currentTime.format(cartDate.getTime());
        final DatabaseReference cartListref=FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("pid",prodId);
        cartMap.put("pname",ProdName.getText().toString());
        cartMap.put("price",ProdPrice.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurretTime);
        cartMap.put("quantity",elegantNumberButton.getNumber());
        cartMap.put("discount","");

        cartListref.child("User View").child("Products").child(prodId)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            cartListref.child("Admin View").child("Products").child(prodId)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(ProductDetail.this, "Add to cart list", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(ProductDetail.this ,HomeActivity.class);
                                            }

                                        }
                                    });

                        }

                    }
                });







    }

    private void getProductdetail(final String prodId) {
        DatabaseReference prodref=FirebaseDatabase.getInstance().getReference().child("Products");
        prodref.child(prodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Products products=dataSnapshot.getValue(Products.class);
                ProdName.setText(products.getPname());
                ProdDisc.setText(products.getDescription());
                ProdPrice.setText("Price:"+products.getPrice()+"$");
                Picasso.get().load(products.getImage()).into(img1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
