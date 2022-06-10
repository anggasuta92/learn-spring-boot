package com.test.demo.sevice;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import com.test.demo.service.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getAllTest(){
        List<User> listUser = new ArrayList<User>();
        listUser.add(new User(new Long(1), "user1", "pass1"));
        listUser.add(new User(new Long(2), "user2", "pass1"));

        Mockito.when(userRepository.findAll()).thenReturn(listUser);
        assertThat(userRepository.findAll()).isEqualTo(listUser);
    }

    @Test
    public void getUserByIdTest(){
        Optional<User> user = Optional.ofNullable(new User(new Long(1), "user1", "pass1"));
        Mockito.when(userRepository.findById(new Long(1))).thenReturn(user);
        assertThat(userRepository.findById(new Long(1))).isEqualTo(user);
    }

    @Test
    public void saveUserTest(){
        User user = new User(new Long(1), "user1", "pass1");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThat(userRepository.save(user)).isEqualTo(user);
    }
}
