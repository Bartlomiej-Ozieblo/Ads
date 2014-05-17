package com.ads.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @OneToOne
    @JoinColumn(name = "role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "contact")
    private Contact contact;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_ads",
    joinColumns = {@JoinColumn(name = "user", referencedColumnName = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "ad", referencedColumnName = "ad_id")})
    private Set<Ad> ads;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }
}
