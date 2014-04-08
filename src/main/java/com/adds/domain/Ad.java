package com.adds.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "ads")
public class Ad implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ad_id")
    private Integer id;

    @Column(name = "ad_title")
    private String title;

    @Column(name = "ad_text")
    @Type(type = "text")
    private String text;

    @Column(name = "ad_date")
//    @Type(type = "timestamp")
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "category")
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
