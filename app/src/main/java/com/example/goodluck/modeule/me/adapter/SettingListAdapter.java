package com.example.goodluck.modeule.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.databinding.ItemSettingListBinding;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.ViewHolder> {
    private HashMap<String, Integer> map;
    private Context context;

    public SettingListAdapter(HashMap<String, Integer> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSettingListBinding itemSettingListBinding = ItemSettingListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemSettingListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Set<Map.Entry<String, Integer>> entrys = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrys) {
            holder.tv_title.setText(entry.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;
        private ImageView iv_more;

        public ViewHolder(@NonNull ItemSettingListBinding itemSettingListBinding) {
            super(itemSettingListBinding.getRoot());
            iv_icon = itemSettingListBinding.ivIcon;
            tv_title = itemSettingListBinding.tvTitle;
            iv_more = itemSettingListBinding.ivMore;

        }
    }
}
