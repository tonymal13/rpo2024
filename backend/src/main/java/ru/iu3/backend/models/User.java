package ru.iu3.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User {

    public User() { }
    public User(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @Transient
    public String np;

    @Column(name = "login", nullable = false, unique = true)
    public String login;

    @JsonIgnore
    @Column(name = "password")
    public String password;

    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @JsonIgnore
    @Column(name = "salt")
    public String salt;

    @Column(name = "token")
    public String token;

    @Column(name = "activity")
    public LocalDateTime activity;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    public Set<Museum> museums = new HashSet<>();

    public void addMuseum(Museum m) {
        this.museums.add(m);
        m.users.add(this);
    }

    public void removeMuseum(Museum m) {
        this.museums.remove(m);
        m.users.remove(this);
    }
}
