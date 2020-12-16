package com.bfs.authserver.pojo;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user", schema = "employee")
public class User implements Serializable {


    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String rolename;


    public User() {
    }

    public User(Integer id, String username, String password, String firstname, String lastname, String email, String rolename) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.rolename = rolename;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername(){ return username; }

    public void setUsername(String username) { this.username = username; }

    @Basic
    @Column(name = "password")
    public String getPassword(){ return password; }

    public void setPassword(String password) { this.password = password; }

    @Basic
    @Column(name = "firstname")
    public String getFirstname(){ return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    @Basic
    @Column(name = "lastname")
    public String getLastname(){ return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


}
