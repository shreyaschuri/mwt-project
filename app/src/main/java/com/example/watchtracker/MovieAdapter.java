package com.example.watchtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieModelClass> mData;

    private OnClickListener monClickListener;

    public MovieAdapter(Context mContext, List<MovieModelClass> mData, OnClickListener monClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.monClickListener = monClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_layout, parent, false);

        return new MyViewHolder(v, monClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.vote_average.setText(mData.get(position).getVote_average());

        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/original"+mData.get(position).getImg())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView vote_average;
        ImageView img;

        OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            vote_average = itemView.findViewById(R.id.vote_average);
            img = itemView.findViewById(R.id.img);

            this.onClickListener = onClickListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnClickListener{
        void onItemClick(int position);
    }

}
