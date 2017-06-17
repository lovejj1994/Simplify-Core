package cn.xxywithpq.json.Bean;

public class User extends BaseEntity {

    private String name;

    private Long age;


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
}