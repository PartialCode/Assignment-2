package com.example.assignment2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private View.OnClickListener onClickListener;

    public ImageAdapter(int[] imageIds, View.OnClickListener listener){
        onClickListener = listener;
        this.imageIds = imageIds;
    }

    public ImageAdapter(int [] imageIds){
        this.imageIds = imageIds;
    }
    private int[] imageIds;
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imageview_image, parent, false);
        ImageViewHolder ivh = new ImageViewHolder(view);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.setImage(imageIds[position]);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        int id;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageview_image_image);
        }

        public void setImage(int id){
            this.id = id;
            image.setImageResource(id);
        }
    }
}
