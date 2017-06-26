package cn.xxywithpq.json.Bean;

import java.util.Map;

public class User extends BaseEntity {

    Map<String, Integer> grades;
    private String name;
    private Long age;

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", grades=" + grades +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}