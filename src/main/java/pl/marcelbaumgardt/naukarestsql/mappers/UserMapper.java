package pl.marcelbaumgardt.naukarestsql.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import pl.marcelbaumgardt.naukarestsql.model.User;
import pl.marcelbaumgardt.naukarestsql.model.UserDTO;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);



    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);


}
