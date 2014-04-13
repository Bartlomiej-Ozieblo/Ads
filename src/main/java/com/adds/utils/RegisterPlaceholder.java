package com.adds.utils;

import com.adds.domain.Contact;
import com.adds.domain.User;

public class RegisterPlaceholder {

    private User user;
    private Contact contact;
    private String repeatPassword;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
