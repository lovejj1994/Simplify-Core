package cn.xxywithpq.json.Bean;

import java.util.*;

public class Group {

    private Long id;

    private String name;
    //基础类型
    private List type;
    //基础类型和类混搭
    private List type1;

    private List<User> users = new ArrayList<User>();

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Map<String, Integer> abcs = new HashMap<>();

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

    public List getType1() {
        return type1;
    }

    public void setType1(List type1) {
        this.type1 = type1;
    }
}