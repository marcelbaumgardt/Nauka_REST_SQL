package pl.marcelbaumgardt.naukarestsql.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.marcelbaumgardt.naukarestsql.mappers.UserMapper;
import pl.marcelbaumgardt.naukarestsql.model.User;
import pl.marcelbaumgardt.naukarestsql.model.UserDTO;
import pl.marcelbaumgardt.naukarestsql.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserMapper mapper = UserMapper.INSTANCE;
    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setUp() throws Exception {
        userService=new UserService(userRepository,mapper);
    }

    @Test
    public void getAllUsers() {
        List<User> userList = Arrays.asList(new User(), new User(), new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDTO> allUsers = userService.getAllUsers();

        //asercja -> sprawdzenie

        assertEquals(3,allUsers.size());
        //assertThat(allUsers.size(),is(3));
    }

    @Test
    public void getUser() {
        User user=User.builder()
                .username("cosmo123")
                .email("marcelidt1994@gmail.com")
                .age(24)
                .build();
        when(userRepository.findById(1L).get()).thenReturn(user);

        UserDTO userDTO=userService.getUser(1L);
        long longValue=userDTO.getAge().longValue();
        assertEquals("cosmo123",userDTO.getUsername());
        assertEquals("marcelidt1994@gmail.com",userDTO.getEmail());
        assertEquals(24,longValue);

    }

    @Test
    public void addUser() {
        UserDTO userDTO=UserDTO.builder()
                .age(24)
                .email("marcelidt1994@gmail.com")
                .username("cosmo123")
                .build();
        User user=User.builder()
                .age(24)
                .email("marcelidt1994@gmail.com")
                .username("cosmo123")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.addUser(userDTO);
        long longValueUserDTO=userDTO.getAge().longValue();
        long longValueSavedUser=savedUser.getAge().longValue();

        assertEquals(savedUser.getUsername(),userDTO.getUsername());
        assertEquals(savedUser.getEmail(),userDTO.getEmail());
        assertEquals(longValueSavedUser,longValueUserDTO);


    }

    @Test
    public void deleteUser() {

    Long id=1L;
    userService.deleteUser(id);
    verify(userRepository, times(1)).deleteById(1L);

    }

    //alt+insert + nazwa metody
    // tworzysz usera z danymi i pozniej testujesz poprawnosc tych danych
}