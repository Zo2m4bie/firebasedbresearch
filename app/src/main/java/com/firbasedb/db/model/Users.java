package com.firbasedb.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 7/15/17.
 */

public class Users {
    private List<User> users = new ArrayList<>();

    public Users() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
