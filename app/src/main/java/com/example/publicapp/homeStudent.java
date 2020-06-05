package com.example.publicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homeStudent extends AppCompatActivity {

    private Button grades;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("prueba");
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        //View
        grades = findViewById(R.id.gradesBtn);

        //Usuario
        usuario = getIntent().getStringExtra("usuario");
        Toast.makeText(this,
                "Bienvendido "+usuario,
                Toast.LENGTH_SHORT).show();


    }


    public void gradesClick(View view){

        Intent testIntent = new Intent(this, prueba.class);
        testIntent.putExtra("usuario", usuario);
        startActivity(testIntent);

    }

    public void horarioClick(View view){

    }



    public void queries(){
        dbRef = FirebaseDatabase.getInstance().getReference("Admins");

    }
}
