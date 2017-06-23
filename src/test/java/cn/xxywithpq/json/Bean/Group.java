package cn.xxywithpq.json.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    private Long id;
    private String name;
    private List type;
    private List<User> users = new ArrayList<User>();

    private Map<String,Integer> abcs = new HashMap<>();

    public Map<String, Integer> getAbcs() {
        return abcs;
    }

    public void setAbcs(Map<String, Integer> abcs) {
        this.abcs = abcs;
    }

    public List getType() {
        return type;
    }

    public void setType(List type) {
        this.type = type;
    }

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