package ppSpring11Task16Server.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ppSpring11Task16Server.dao.RoleDAO;
import ppSpring11Task16Server.dto.RoleDto;
import ppSpring11Task16Server.dto.UserDto;
import ppSpring11Task16Server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkiraRokudo on 26.04.2020 in one of sun day
 */
@RestController
public class RoleRestController {

    private RoleDAO roleDAO;

    @Autowired
    public RoleRestController(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    //содержит один метод для получения всех ролей
    @GetMapping("/admin/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = null;
        try {
            roles = roleDAO.readAllObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<RoleDto>> responseEntity;
        if(roles != null) {
            responseEntity = new ResponseEntity<>(roles.stream().map(r->new RoleDto(r.getName())).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
