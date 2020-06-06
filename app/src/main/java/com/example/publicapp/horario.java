package com.example.publicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class horario extends AppCompatActivity {

        RecyclerView nRecyclerView;
        MyAdapter nMyAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        nRecyclerView = findViewById(R.id.recyclerview);
       nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       nMyAdapater =  new MyAdapter(this , getMyList());
       nRecyclerView.setAdapter(nMyAdapater);
    }

    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTittle("Lunes");
        m.setImg(R.drawable.monday);
        models.add(m);

        m = new Model();
        m.setTittle("Martes");
        m.setImg(R.drawable.monday);
        models.add(m);

        m = new Model();
        m.setTittle("Miercoles");
        m.setImg(R.drawable.monday);
        models.add(m);

        m = new Model();
        m.setTittle("jueves");
        m.setImg(R.drawable.monday);
        models.add(m);

        m = new Model();
        m.setTittle("viernes");
        m.setImg(R.drawable.monday);
        models.add(m);

       return models;
    }
}
