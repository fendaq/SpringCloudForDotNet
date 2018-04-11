package top.gongtao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/11 10:09
 * @Description:
 */

//@Entity(name="kpm_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<User> users = new HashSet<User>();


    public Department(){}

    public Department(String name, String description, Long pid, boolean isDisable) {
        this.name = name;
        this.description = description;
        this.pid = pid;
        this.isDisable = isDisable;
    }

    public void addUser(User user){
        users.add(user);
        user.setDepartment(this);
    }

    public void removeUser(User user){
        users.remove(user);
        user.setDepartment(null);
    }

    private Long pid;

    private boolean isDisable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public boolean isDisable() {
        return isDisable;
    }

    public void setDisable(boolean disable) {
        isDisable = disable;
    }
}
