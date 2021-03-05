package com.example.goodluck.modeule.study.photo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.goodluck.R;
import com.example.goodluck.databinding.AdapterItakePhotoBinding;
import com.example.goodluck.modeule.study.photo.entity.OnRVItemClickListener;
import com.example.goodluck.modeule.study.photo.entity.TakePhotoEntity;
import com.example.goodluck.utils.AppUtils;

import java.util.List;

public class TakePhotoAdapter extends RecyclerView.Adapter<TakePhotoAdapter.TakePhotoViewHoloder> {
    private List<TakePhotoEntity> mList;
    private Context mContext;

    public TakePhotoAdapter(List<TakePhotoEntity> list, Context context) {
        mList = list;
        mContext = context;
    }

    private OnRVItemClickListener onRVItemClickListener;

    public void setOnRVItemClickListener(OnRVItemClickListener onRVItemClickListener) {
        this.onRVItemClickListener = onRVItemClickListener;
    }

    @NonNull
    @Override
    public TakePhotoViewHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_itake_photo, parent, false);
        AdapterItakePhotoBinding binding = AdapterItakePhotoBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new TakePhotoViewHoloder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull final TakePhotoViewHoloder holder, final int position) {
        holder.iv_show_picture.setBackgroundResource(0);
        if (getItemCount() - 1 == position) {
            holder.iv_delete_image.setVisibility(View.GONE);
        } else {
            holder.iv_delete_image.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(mList.get(position).pictureUri)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners((int) AppUtils.dpToPx(mContext, 8.0f))))
                    .into(holder.iv_show_picture);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRVItemClickListener != null) {
                    onRVItemClickListener.onRVItemClick(view, holder.getAdapterPosition());
                }
            }
        });
        holder.iv_delete_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    static class TakePhotoViewHoloder extends RecyclerView.ViewHolder {
        ImageView iv_show_picture;
        ImageView iv_delete_image;

        public TakePhotoViewHoloder(@NonNull AdapterItakePhotoBinding binding) {
            super(binding.getRoot());
            iv_show_picture = binding.ivShowPicture;
            iv_delete_image = binding.ivDeleteImage;
        }
    }
}
