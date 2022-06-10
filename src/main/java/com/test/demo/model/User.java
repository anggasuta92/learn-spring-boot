package com.test.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name= "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", length = 25, nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    @Size(max = 25, message = "Max 25 char length")
    private String username;

    @Column(name = "password", length = 25, nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(max = 25, message = "Max 25 char length")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public User(){}

    public User(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
