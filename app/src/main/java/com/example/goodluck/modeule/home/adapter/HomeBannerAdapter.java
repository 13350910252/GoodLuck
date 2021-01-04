package com.example.goodluck.modeule.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.databinding.ItemHomeBannerBinding;
import com.example.goodluck.modeule.home.entity.BannerEntity;
import com.example.goodluck.utils.GlideUtils;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<BannerEntity, HomeBannerAdapter.HomeBannerViewHolder> {
    private final Context context;

    public HomeBannerAdapter(List<BannerEntity> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public HomeBannerAdapter.HomeBannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ItemHomeBannerBinding bannerBinding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HomeBannerViewHolder(bannerBinding);
    }

    @Override
    public void onBindView(HomeBannerViewHolder holder, BannerEntity data, int position, int size) {
        BannerEntity entity = mDatas.get(position);

        GlideUtils.loadUrl(context, holder.iv_banner, null, entity.getImagePath(), null, null, false, true);
    }

   static class HomeBannerViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_banner;

        public HomeBannerViewHolder(@NonNull ItemHomeBannerBinding bannerBinding) {
            super(bannerBinding.getRoot());
            iv_banner = bannerBinding.ivBanner;
        }
    }
}
