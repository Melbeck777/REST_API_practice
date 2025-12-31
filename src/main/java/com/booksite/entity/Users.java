
package com.booksite.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name",nullable = false, length = 100)
    private String first_name;

    @Column(name = "last_name",nullable = false, length = 100)
    private String last_name;

    @Column(name = "password_bash",nullable = false)
    private String password_bash ;

    @Column(name = "created_at",nullable = false, length = 100)
    private LocalDateTime created_at;

    @Column(name = "updated_at",nullable = false, length = 100)
    private LocalDateTime updated_at;

    public Users(long id, String first_name, String last_name, String password_bash, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password_bash = password_bash;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword_bash() {
        return password_bash;
    }

    public void setPassword_bash(String password_bash) {
        this.password_bash = password_bash;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
