package ppSpring11Task16Server.mapstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppSpring11Task16Server.dto.UserDto;
import ppSpring11Task16Server.model.User;
import ppSpring11Task16Server.dao.RoleDAO;

/**
 * @author AkiraRokudo on 10.04.2020 in one of sun day
 */
@Component
public class UserDtoUserMapperImpl implements UserDtoUserMapper {

    private RoleDAO roleDAO;

    @Autowired
    public UserDtoUserMapperImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public User dtoToUser(UserDto source) {
        if(source == null) {
            return null;
        }
        User user = source.giveMeUser();
        user.setRoles(roleDAO.getRolesByNames(source.getRoles()));
        return user;
    }


    //этот метод нам на самом деле не нужен, но для гармонии оставлю
    @Override
    public UserDto userToDto(User destination) {
        if(destination == null) {
            return null;
        }
        return new UserDto(destination);
    }
}
