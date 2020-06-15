package com.printhub.musicapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.printhub.musicapp.Model.Upload;
import com.printhub.musicapp.R;
import com.printhub.musicapp.SongsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.myViewHolder>{

    private Context mcontext;
    private List<Upload> uploads;

    public RecyclerviewAdapter(Context mcontext, List<Upload> uploads) {
        this.mcontext = mcontext;
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater= LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.card_view_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final Upload upload = uploads.get(position);
        holder.tv_book_title.setText(upload.getName());

        Glide.with(mcontext).load(upload.getUrl()).into(holder.imd_book_thumnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, SongsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("songsCategory", upload.getSongsCategory());
                intent.putExtra("url", upload.getUrl());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView imd_book_thumnail;
        CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_book_title = itemView.findViewById(R.id.book_title_id);
            imd_book_thumnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.card_view_id);
        }
    }
}
