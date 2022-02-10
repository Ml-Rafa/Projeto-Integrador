package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="users")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String document;
    @JsonIgnore
    @Column(name="username")
    private String user;
    @JsonIgnore
    @Column(name="password")
    private String password;
    @JsonIgnore
    @Column(name="enabled")
    private boolean active;


    //REPRESENTATIVE
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id")
    private Warehouse warehouse = new Warehouse();

    @OneToMany(mappedBy = "representative", cascade = CascadeType.ALL)
    private List<Batch> batch = new ArrayList<>();

    //CLIENT
    private String address = "";
    private String telephone = "";
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<PurchaseOrder> listPurchaseOrder = new ArrayList<>();

    //SELLER
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Product> productList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_profiles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<Profile> profiles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.profiles.forEach(profile-> authorities.add(new SimpleGrantedAuthority(profile.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.user;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }


}
