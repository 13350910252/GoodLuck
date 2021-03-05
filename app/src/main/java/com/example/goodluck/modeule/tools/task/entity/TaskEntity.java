package com.example.goodluck.modeule.tools.task.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

@Entity
public class TaskEntity {
    @Id(autoincrement = true)
    private Long _id;
    /**
     * 标题
     */
    @Property
    private String title;
    /**
     * 内容
     */
    @Property
    private String content;
    /**
     * 是否完成
     */
    @Property
    private Date date = new Date();
    /**
     * 根据时间判断当日任务是否完成，未完成就置false
     */
    @Property
    private boolean isFinish;

    @Generated(hash = 946776827)
    public TaskEntity(Long _id, String title, String content, Date date,
            boolean isFinish) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isFinish = isFinish;
    }

    @Generated(hash = 397975341)
    public TaskEntity() {
    }

    public Long get_id() {
        return this._id;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIsFinish() {
        return this.isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

}
