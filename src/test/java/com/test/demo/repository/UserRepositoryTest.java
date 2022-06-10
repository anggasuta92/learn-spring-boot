package com.test.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import com.test.demo.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void saveUserTest(){
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass1");
        User saveData = testEntityManager.persist(user);
        User getData = userRepository.findById(saveData.getId()).get();

        assertThat(getData).isEqualTo(saveData);
    }

    @Test
    void findByIdTest(){
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass1");
        User saveData = testEntityManager.persist(user);
        User getData = userRepository.findById(saveData.getId()).get();
        assertThat(getData).isEqualTo(saveData);
    }

    @Test
    void findAllTest(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("pass2");

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);

        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0).getUsername()).isEqualTo(user1.getUsername());
    }
}
