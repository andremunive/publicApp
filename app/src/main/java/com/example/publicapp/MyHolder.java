package com.example.publicapp;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyHolder extends RecyclerView.ViewHolder {
   ImageView nImageView;
   TextView ntittle;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.nImageView = itemView.findViewById(R.id.imglunes);
        this.ntittle = itemView.findViewById(R.id.tittleTV);

    }
}
