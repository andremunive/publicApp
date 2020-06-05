package com.example.publicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public EditText user, password;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase dbRef = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        user = findViewById(R.id.userTxt);
        password = findViewById(R.id.passwordTxt);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            showHomeView();
        }
    }

    public void loginClick(View view){
        logingMethod();
    }

    public void logingMethod(){
        studentValidate(user.getText().toString());
    }

    public void studentValidate(String usuario){

        dbRef.getReference().child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Si la ruta "Students" existe
                if(dataSnapshot.exists()){
                    //Si el user ingresado existe
                    if(dataSnapshot.child(user.getText().toString()).exists()){
                        //Se obtiene correo de la BD
                        String correo = dataSnapshot.child(user.getText().toString())
                                .child("email").getValue().toString();
                        //Se obtiene clave de la BD
                        String clave = dataSnapshot.child(user.getText().toString())
                                .child("password").getValue().toString();
                        //Validación de que la clave escrita en pantalla sea la misma de la BD
                        if (clave.equals(password.getText().toString())) {
                            //Login con FirebaseAuth
                            auth.signInWithEmailAndPassword(correo, clave);
                            showHomeView();
                        }else{
                            password.setError("Clave Incorrecta");
                        }
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showHomeView(){
        finish();
        Intent homeIntent = new Intent(this, homeStudent.class);
        homeIntent.putExtra("usuario", user.getText().toString());
        startActivity(homeIntent);
    }


}
