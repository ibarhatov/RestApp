package ru.ibarhatov.restapp.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    private int userId;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
    private Timestamp dateModified;

    @PrePersist
    void preInsert() {
        if (this.dateModified == null)
            this.dateModified = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    void preUpdate() {
        this.dateModified = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login=" + login +
                ", name='" + name + '\'' +
                ", dateModified=" + dateModified +
                '}';
    }
}
