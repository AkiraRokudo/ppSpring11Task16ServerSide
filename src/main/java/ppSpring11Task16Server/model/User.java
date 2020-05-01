package ppSpring11Task16Server.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author AkiraRokudo on 29.02.2020 in one of sun day
 */

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login" , unique = true) //лень писать собственный валидатор
    @NotBlank(message = "login can't be empty")
    private String login;
    @Column(name = "first_name")
    @NotBlank(message = "first name can't be empty")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "last name can't be empty")
    @NotNull
    private String lastName;
    @Column(name = "password")
    @NotBlank(message = "password can't be empty")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "user_role",
            joinColumns =  @JoinColumn(name = "user_id" , referencedColumnName = "ID") ,
            inverseJoinColumns =  @JoinColumn(name = "role_id" , referencedColumnName = "ID")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "money")
    @PositiveOrZero(message = "User haven't credit line")
    private Long money;

    public User() {
    }

    public User(Long id, String login, String firstName, String lastName, String password, Long money) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.money = money;
    }

    public User(String login, String firstName, String lastName, String password, Long money) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.money = money;
    }


    //Нужно только с одной стороны
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getLogin();
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
