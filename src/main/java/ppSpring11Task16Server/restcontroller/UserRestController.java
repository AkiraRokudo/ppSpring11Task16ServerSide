package ppSpring11Task16Server.restcontroller;

import org.springframework.security.core.parameters.P;
import ppSpring11Task16Server.dto.UserDto;
import ppSpring11Task16Server.mapstruct.UserDtoUserMapper;
import ppSpring11Task16Server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ppSpring11Task16Server.dao.UserDAO;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkiraRokudo on 26.04.2020 in one of sun day
 */
@RestController
public class UserRestController {

    private UserDAO userDAO;
    private UserDtoUserMapper mapper;
    //содержит методы для получения
    /*
    1. списка всех пользователей(вызывается при загрузке и при каждом запросе на него влияющем - апдейт, делит, криейт
    2. данных пользователя - при удалении либо редактировании, для открытия модалки
    3. удаление пользователя
    4. редактирование пользователя
     */

    @Autowired
    public UserRestController(UserDAO userDAO, UserDtoUserMapper mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @GetMapping("/admin/allusers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = null;
        try {
            users = userDAO.readAllObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<UserDto>> responseEntity;
        if(users != null) {
            responseEntity = new ResponseEntity<>(users.stream().map(u->new UserDto(u)).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/userInfo")
    public ResponseEntity<List<UserDto>> getCurrentUser(@RequestBody String loginName) {
        User uPrincipal = null;
        try {
            uPrincipal = userDAO.readUserByLogin(loginName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<UserDto>> responseEntity;
        if(uPrincipal != null) {
            responseEntity = new ResponseEntity<>(Collections.singletonList(new UserDto(uPrincipal)), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/admin/edituser/{id}")
    public ResponseEntity<UserDto> getUsersWithEditing(@PathVariable("id") Long id) {
        User user = null;
        try {
            user = userDAO.readObjectById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResponseEntity<UserDto> responseEntity;
        if(user != null) {
            responseEntity = new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/user/principalInfo")
    public ResponseEntity<UserDto> getPrincipalEmailAndRoles(@RequestBody String loginName) {
        User principal = null;
        try {
            principal = userDAO.readUserByLogin(loginName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UserDto userDto = new UserDto();
        ResponseEntity<UserDto> responseEntity;
        if(principal != null) {
            userDto.setLogin(principal.getLogin()); //что спросили то и дали
            userDto.setPassword(principal.getPassword()); //что спросили то и дали
            userDto.setRoles(principal.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));
            responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/admin/create")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        try {
            userDAO.createObject(mapper.dtoToUser(userDto));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //кидаем 500 если что не так
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/edit")
    public ResponseEntity<?> editUser(@RequestBody UserDto userDto) {
        try {

            userDAO.updateObject(mapper.dtoToUser(userDto));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //кидаем 500 если что не так
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
        //корректности ради
        try {
            User deleteUser = userDAO.readObjectById(mapper.dtoToUser(userDto).getId()); //А вдруг нам только айдишники прислали? ибо косяк может быть с селектнутыми ролями
            userDAO.deleteObject(deleteUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //кидаем 500 если что не так
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
