package br.com.meli.wave4.entities;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
public class Profile implements GrantedAuthority{

    private static final long serialVersionUID = 7497401423570420955L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "profiles")
    Set<User> userList = new HashSet<>();

    @Override
    public String getAuthority() {
        return null;

    }

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

}