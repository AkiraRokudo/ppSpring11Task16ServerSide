package ppSpring11Task16Server.mapstruct;

import ppSpring11Task16Server.dto.UserDto;
import ppSpring11Task16Server.model.User;
import org.mapstruct.Mapper;

/**
 * @author AkiraRokudo on 10.04.2020 in one of sun day
 */
@Mapper(componentModel = "spring")
public interface UserDtoUserMapper {
    User dtoToUser(UserDto source);
    UserDto userToDto(User destination);
}
