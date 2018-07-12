package pl.marcelbaumgardt.naukarestsql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcelbaumgardt.naukarestsql.errors.UserNotFoundException;
import pl.marcelbaumgardt.naukarestsql.mappers.UserMapper;
import pl.marcelbaumgardt.naukarestsql.model.User;
import pl.marcelbaumgardt.naukarestsql.model.UserDTO;
import pl.marcelbaumgardt.naukarestsql.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
//alt + enter do testow i na nazwe klasy, testujemy serwisy i kontrolery;
    UserRepository userRepository;
    UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Pobieramy wszystkich userow z Repo
    //Mapujemy na UserDTO i zwracamy


    public List<UserDTO> getAllUsers(){
//        userRepository.findAll().stream().map(user -> userMapper.userToUserDTO(user));
        List<UserDTO> userDTOS = userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
        return userDTOS;
    }

    public UserDTO getUser(Long id){

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        return userDTO;

    }

    public  User addUser(UserDTO userDTO){
        User user=userMapper.userDTOToUser(userDTO);
        return userRepository.save(user);
    }

    public  void deleteUser(Long id){
        userRepository.deleteById(id);
    }


    //metoda ktora pobiera po Id -> xi zwraca userDTO zmapowany + test
}
