package pl.marcelbaumgardt.naukarestsql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.marcelbaumgardt.naukarestsql.model.UserDTO;
import pl.marcelbaumgardt.naukarestsql.service.UserService;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-CoinAPI-Key","klucz");
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//        String URL = "https://.coinapi.io/v1/exchangerate/BTC";
//        ResponseEntity<R> exchange = restTemplate.exchange(URL, HttpMethod.GET, entity, User.class);
//        User body = exchange.getBody();
        this.userService = userService;
    }

    //metoda / user ktora zwroci liste userow dto{

    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable(required = false) Long id){
        return userService.getUser(id);
    }
    //get(users/id) + test

    @PostMapping(path = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
