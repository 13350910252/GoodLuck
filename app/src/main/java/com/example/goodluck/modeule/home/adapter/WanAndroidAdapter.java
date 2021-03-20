package com.example.goodluck.modeule.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.databinding.ItemWanAndroidBinding;
import com.example.goodluck.modeule.home.entity.WanAndroidHomeEntity;

import java.util.ArrayList;

public class WanAndroidAdapter extends RecyclerView.Adapter<WanAndroidAdapter.ViewHolder> {
    private ArrayList<WanAndroidHomeEntity> list;

    public WanAndroidAdapter(ArrayList<WanAndroidHomeEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWanAndroidBinding binding = ItemWanAndroidBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WanAndroidHomeEntity entity = list.get(position);
        if (entity.getAuthor().isEmpty()) {
            holder.tv_author.setText("分享人：");
            holder.tv_author_name.setText(entity.getAuthor());
        } else {
            holder.tv_author.setText("作者：");
            holder.tv_author_name.setText(entity.getShareUser());
        }
        holder.tv_title.setText(entity.getTitle());
        holder.tv_share_date.setText(entity.getNiceShareDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_author;
        private TextView tv_author_name;
        private TextView tv_share_date;

        public ViewHolder(@NonNull ItemWanAndroidBinding binding) {
            super(binding.getRoot());
            tv_title = binding.tvTitle;
            tv_author = binding.tvAuthor;
            tv_author_name = binding.tvAuthorName;
            tv_share_date = binding.tvShareDate;
        }
    }
}
