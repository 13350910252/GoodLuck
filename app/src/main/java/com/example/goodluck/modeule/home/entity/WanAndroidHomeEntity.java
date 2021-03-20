package com.example.goodluck.modeule.home.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.io.Serializable;

public class WanAndroidHomeEntity implements Parcelable {
    private String apkLink;
    private int audit;
    private String author;//作者
    private boolean canEdit;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String descMd;
    private String envelopePic;
    private boolean fresh;
    private String host;
    private int id;//17541
    private String link;//文章链接
    private String niceDate;
    private String niceShareDate;//分享时间1天前
    private String origin;
    private String prefix;
    private String projectLink;
    private long publishTime;//发布时间
    private int realSuperChapterId;
    private int selfVisible;
    private int shareDate;//1615039008000
    private String shareUser;//鸿洋
    private int superChapterId;
    private String superChapterName;
    private JsonElement tags;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;

    protected WanAndroidHomeEntity(Parcel in) {
        apkLink = in.readString();
        audit = in.readInt();
        author = in.readString();
        canEdit = in.readInt() == 1;
        chapterId = in.readInt();
        chapterName = in.readString();
        collect = in.readInt() == 1;
        courseId = in.readInt();
        desc = in.readString();
        descMd = in.readString();
        envelopePic = in.readString();
        fresh = in.readInt() == 1;
        host = in.readString();
        id = in.readInt();
        link = in.readString();
        niceDate = in.readString();
        niceShareDate = in.readString();
        origin = in.readString();
        prefix = in.readString();
        projectLink = in.readString();
        publishTime = in.readLong();
        realSuperChapterId = in.readInt();
        selfVisible = in.readInt();
        shareDate = in.readInt();
        shareUser = in.readString();
        superChapterId = in.readInt();
        superChapterName = in.readString();
        title = in.readString();
        type = in.readInt();
        userId = in.readInt();
        visible = in.readInt();
        zan = in.readInt();
    }

    public static final Creator<WanAndroidHomeEntity> CREATOR = new Creator<WanAndroidHomeEntity>() {
        @Override
        public WanAndroidHomeEntity createFromParcel(Parcel in) {
            return new WanAndroidHomeEntity(in);
        }

        @Override
        public WanAndroidHomeEntity[] newArray(int size) {
            return new WanAndroidHomeEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apkLink);
        dest.writeString(author);
        dest.writeString(chapterName);
        dest.writeString(desc);
        dest.writeString(descMd);
        dest.writeString(envelopePic);
        dest.writeString(host);
        dest.writeString(link);
        dest.writeString(niceDate);
        dest.writeString(niceShareDate);
        dest.writeString(origin);
        dest.writeString(prefix);
        dest.writeString(projectLink);
        dest.writeString(shareUser);
        dest.writeString(superChapterName);
        dest.writeString(title);

        dest.writeInt(canEdit ? 1 : 0);
        dest.writeInt(collect ? 1 : 0);
        dest.writeInt(fresh ? 1 : 0);

        dest.writeInt(audit);
        dest.writeInt(chapterId);
        dest.writeInt(courseId);
        dest.writeInt(id);
        dest.writeInt(realSuperChapterId);
        dest.writeInt(selfVisible);
        dest.writeInt(shareDate);
        dest.writeInt(superChapterId);
        dest.writeInt(type);
        dest.writeInt(userId);
        dest.writeInt(visible);
        dest.writeInt(zan);
    }

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescMd() {
        return descMd;
    }

    public void setDescMd(String descMd) {
        this.descMd = descMd;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getRealSuperChapterId() {
        return realSuperChapterId;
    }

    public void setRealSuperChapterId(int realSuperChapterId) {
        this.realSuperChapterId = realSuperChapterId;
    }

    public int getSelfVisible() {
        return selfVisible;
    }

    public void setSelfVisible(int selfVisible) {
        this.selfVisible = selfVisible;
    }

    public int getShareDate() {
        return shareDate;
    }

    public void setShareDate(int shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public JsonElement getTags() {
        return tags;
    }

    public void setTags(JsonElement tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }
}
