package com.spring.demo.registration.model.user;

import com.github.ankurpathak.username.bean.constraints.UsernamePattern;
import com.spring.demo.registration.model.dateAudit.DateAudit;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Data
public class User extends DateAudit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Size(max = 50)
        private String name;

        @UsernamePattern
        @NotBlank
        @Size(max = 15)
        private String username;

        @NaturalId
        @NotBlank
        @Size(max = 40)
        @Email
        private String email;

        @NotBlank
        private String password;


        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",
                        joinColumns = @JoinColumn(name = "user_id"),
                        inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();


}
