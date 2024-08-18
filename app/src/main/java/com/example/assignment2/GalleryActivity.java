package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

public class GalleryActivity extends AppCompatActivity {

    private int[] imageIds;
    private RecyclerView rwImages;
    private ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        loadFromDrawable();
        setUpRecyclerView();


        Intent intent = getIntent();
        if (intent != null){
            adapter.setOnClickListener(v -> {
                Intent result = new Intent();
                ImageAdapter.ImageViewHolder ivh =
                        (ImageAdapter.ImageViewHolder)rwImages.findContainingViewHolder(v);
                if (ivh != null) {
                    result.putExtra("imageResourceID", ivh.id);
                }
                setResult(Activity.RESULT_OK, result);
                finish();
            });
        }
        else{
            adapter.setOnClickListener(v -> {
                Toast.makeText(getApplicationContext(),"Activity started without an intent",Toast.LENGTH_LONG).show();
            });
        }
    }

    public void setUpRecyclerView(){
        rwImages = findViewById(R.id.rwImageGallery);
        adapter = new ImageAdapter(imageIds);
        rwImages.setAdapter(adapter);
        rwImages.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rwImages.addItemDecoration(new EqualSpaceItemDecoration(10));
    }


    private void loadFromDrawable(){
        Field[] fields = R.drawable.class.getFields();
        imageIds = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                imageIds[i] = fields[i].getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}