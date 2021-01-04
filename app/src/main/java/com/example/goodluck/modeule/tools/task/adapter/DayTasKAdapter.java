package com.example.goodluck.modeule.tools.task.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.databinding.ItemDayTaskBinding;
import com.example.goodluck.modeule.tools.task.entity.TaskEntity;

import java.util.List;

public class DayTasKAdapter extends RecyclerView.Adapter<DayTasKAdapter.DayTaskViewHolder> {
    private List<TaskEntity> mList;

    public DayTasKAdapter(List<TaskEntity> list) {
        mList = list;
    }

    @NonNull
    @Override
    public DayTasKAdapter.DayTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDayTaskBinding commentBinding = ItemDayTaskBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new DayTaskViewHolder(commentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DayTasKAdapter.DayTaskViewHolder holder, int position) {
        TaskEntity entity = mList.get(position);
        holder.tv_title.setText(entity.getTitle());
        holder.tv_content.setText(entity.getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 每日任务
     */
    static class DayTaskViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;

        public DayTaskViewHolder(@NonNull ItemDayTaskBinding binding) {
            super(binding.getRoot());
            tv_title = binding.tvTitle;
            tv_content = binding.tvContent;
        }
    }

}
