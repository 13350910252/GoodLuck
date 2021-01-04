package com.example.goodluck.modeule.study.photo.entity;

import android.net.Uri;

public class TakePhotoEntity {
    /**
     * 图片的真实路径
     */
    public String pathName;
    /**
     * 图片uri
     */
    public Uri pictureUri;

    public TakePhotoEntity() {
    }

    public TakePhotoEntity(Uri pictureUri) {
        this.pictureUri = pictureUri;
    }

    public TakePhotoEntity(String pathName) {
        this.pathName = pathName;
    }

    public TakePhotoEntity(Uri pictureUri, String pathName) {
        this.pictureUri = pictureUri;
        this.pathName = pathName;
    }
}
