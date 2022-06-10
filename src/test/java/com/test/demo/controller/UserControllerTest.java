package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import com.test.demo.model.User;
import com.test.demo.service.UserServiceImpl;

@SpringBootTest
public class UserControllerTest {
    
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userServiceImpl;

    @Test
    void findAllTest(){
        List<User> listUser = new ArrayList<User>();
        listUser.add(new User(new Long(1), "user1", "pass1"));
        listUser.add(new User(new Long(2), "user2", "pass1"));

        Mockito.when(userServiceImpl.getAll()).thenReturn(listUser);
        assertThat(userServiceImpl.getAll()).isEqualTo(userController.findAll().getBody());
        assertThat(userServiceImpl.getAll().get(0)).isEqualTo(userController.findAll().getBody().get(0));
        assertThat(userServiceImpl.getAll().get(1)).isEqualTo(userController.findAll().getBody().get(1));

        listUser.clear();
        Mockito.when(userServiceImpl.getAll()).thenReturn(listUser);
        assertThat(HttpStatus.NO_CONTENT).isEqualTo(userController.findAll().getStatusCode());
    }

    @Test
    void findByIdTest(){
        Optional<User> user = Optional.ofNullable(new User(new Long(1), "user1", "pass1"));
        Mockito.when(userServiceImpl.getUserById(new Long(1))).thenReturn(user);

        assertThat(userServiceImpl.getUserById(new Long(1)).get()).isEqualTo(userController.findById(new Long(1)).getBody());
        assertThat(userServiceImpl.getUserById(new Long(1)).get().getUsername()).isEqualTo(userController.findById(new Long(1)).getBody().getUsername());
        assertThat(HttpStatus.NOT_FOUND).isEqualTo(userController.findById(new Long(2)).getStatusCode());
    }

    @Test
    void saveTest(){
        User user = new User(new Long(1), "user1", "pass1");
        Mockito.when(userServiceImpl.saveUser(user)).thenReturn(user);
        assertThat(userServiceImpl.saveUser(user)).isEqualTo(userController.save(user).getBody());
    }
}
