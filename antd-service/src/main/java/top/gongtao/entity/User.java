package top.gongtao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/10 13:28
 * @Description:
 */
//@Data
//@Entity(name = "kpm_user")
//@JsonIgnoreProperties(value = { "roles" })
public class User implements Serializable {

    /**
     * 静态long类型常量serialVersionUID的作用：
     * <p>
     * 显示的设置serialVersionUID值就可以保证版本的兼容性，如果你在类中写上了这个值，就算类变动了，
     * 它反序列化的时候也能和文件中的原值匹配上。而新增的值则会设置成零值，删除的值则不会显示。
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 用户备注
     */
    private String remark;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 状态 1.启用 0.禁用
     */
    private Byte status;


    /**
     * 关系维护端，负责多对多关系的维护
     *
     * @JoinTable 表示关联表的信息，其中：
     * 1.name 表示关联表的名字
     * 2.joinColumns 指定外键的名字，关联到关系维护端Role
     * 3.inverseJoinColumns 指定外键的名字，要关联的关系被维护端
     * 以上完全可以默认，默认情况下：
     * 1.name 主表名_从表名
     * 2.joinColumns 主表_id
     * 3.inverseJoinColumns 从表_id
     */
//    @JsonIgnoreProperties(value = { "roles" })
//    @JsonIgnore
    @JSONField(serialize = false)
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "kpm_user_role" ,
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns ={@JoinColumn(name="role_id")})
    private Set<Role> roles = new HashSet<>();


//    @JsonIgnore

    @JSONField(serialize = false)
    @ManyToOne
    private Department department;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
