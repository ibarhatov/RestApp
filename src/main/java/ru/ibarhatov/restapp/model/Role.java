package ru.ibarhatov.restapp.model;

import lombok.Data;
import ru.ibarhatov.restapp.domain.RoleCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    private int roleId;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleCode roleCode;
    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;
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
        return "Role{" +
                "roleId=" + roleId +
                ", roleCode=" + roleCode +
                ", name='" + name + '\'' +
                ", dateModified=" + dateModified +
                '}';
    }
}
