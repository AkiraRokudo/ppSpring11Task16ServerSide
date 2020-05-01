package ppSpring11Task16Server.dto;

import org.springframework.stereotype.Component;
import ppSpring11Task16Server.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author AkiraRokudo on 05.04.2020 in one of sun day
 * Класс адаптер между вьюхой и моделью. Ибо вьюха не может хранить объекты,
 * а связи с ними нам необходимо хранить
 */
@Component
public class UserDto {

    private Long id;
    @NotBlank(message = "login can't be empty")
    private String login;
    @NotBlank(message = "first name can't be empty")
    private String firstName;
    @NotBlank(message = "last name can't be empty")
    private String lastName;
    @NotBlank(message = "password can't be empty")
    private String password;
    @PositiveOrZero(message = "User haven't credit line")
    private Long money;

    private Set<String> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(Long id, String login, String firstName, String lastName, String password, Long money) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.money = money;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.money = user.getMoney();
        this.roles = user.getRoles() == null ? new HashSet<>() : user.getRoles().stream().map(r->r.getName()).collect(Collectors.toCollection(HashSet::new));
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public User giveMeUser() {
        User user = new User(getId(),getLogin(),getFirstName(),getLastName(),getPassword(),getMoney());
        return user;
    }
}
