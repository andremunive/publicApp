package com.example.publicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publicapp.models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class prueba extends AppCompatActivity {

    private List<Student> studentList = new ArrayList<Student>();
    ArrayAdapter<Student> studentArrayAdapter;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private ListView estudiantes;
    private EditText test;
    private String usuario;
    private String cursoID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        //View
        estudiantes = findViewById(R.id.student_list);
        test = findViewById(R.id.mensajeTxt);
        usuario = getIntent().getStringExtra("usuario");
    }

    public void listarDatos(){
        ref.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Student s = objSnapshot.getValue(Student.class);
                    studentList.add(s);

                    studentArrayAdapter = new ArrayAdapter<Student>(prueba.this, android.R.layout.simple_list_item_1,
                            studentList);
                    estudiantes.setAdapter(studentArrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void userData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String email = user.getEmail();
            test.setText(email);
        }else{
            Toast.makeText(this,
                    "Usuario Nulo",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void search(){
        ref.child("Students").child(usuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    cursoID =dataSnapshot.child("courseID").getValue().toString();
                    test.setText(cursoID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("schoolproject-7f663").whereEqualTo("courseID", "112020")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                System.out.println(document.getId() + " => "+ document.getData());
                            }
                        }else{
                            Toast.makeText(prueba.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void testClick(View view){
        //listarDatos();
        //userData();
        search();
    }
}
