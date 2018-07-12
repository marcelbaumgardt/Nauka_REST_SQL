package pl.marcelbaumgardt.naukarestsql.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.marcelbaumgardt.naukarestsql.model.User;
import pl.marcelbaumgardt.naukarestsql.model.UserDTO;
import pl.marcelbaumgardt.naukarestsql.service.UserService;
import pl.marcelbaumgardt.naukarestsql.util.RestUtil;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //userController=new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }

    @Test
    public void getUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().username("cosmo123").email("marcelidt1994@gmail.com").age(24).build();

        when(userService.getUser(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username",is("cosmo123")))
                .andExpect(jsonPath("$.email",is("marcelidt1994@gmail.com")))
                .andExpect(jsonPath("$.age",is(24)));
    }

    @Test
    public void addUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().username("cosmo123").email("marcelidt1994@gmail.com").age(24).build();
        User user=new User();


        when(userService.addUser(userDTO)).thenReturn(user);

        String userDTOJSON = RestUtil.convertToJSON(userDTO);

        mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userDTOJSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void deletetUser() throws Exception {


        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService,times(1)).deleteUser(anyLong());
    }

    // tworzysz userDTO z danymi i sprawdzasz czy zostaly zwrocone takie same $.username -> is(expected)

    //wskazowki w naszym projekcie - Przzemek M
}