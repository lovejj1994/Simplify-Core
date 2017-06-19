package cn.xxywithpq.json.Bean;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private Long id;
    private String name;
    private List<User> users = new ArrayList<User>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}