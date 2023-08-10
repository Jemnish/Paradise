package com.example.paradise;

import com.example.paradise.entity.User;
import com.example.paradise.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Order(1)
    public void saveUserTest() {
        User user = User.builder()
                .fullname("jenish")
                .email("jenish@gmail.com")
                .mobileNo("9810000000")
                .password("password")
                .build();

        userRepo.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserTest() {
        User user = createUser();

        userRepo.save(user);
        User userCreated = userRepo.findById(user.getId()).get();

        Assertions.assertThat(userCreated.getId()).isEqualTo(user.getId());
    }

    @Test
    @Order(3)
    public void getListOfUserTest() {
        User user = createUser();
        userRepo.save(user);

        List<User> users = userRepo.findAll();

        Assertions.assertThat(users).isNotEmpty();
    }

    @Test
    @Order(4)
    public void updateUserTest() {
        User user = createUser();
        userRepo.save(user);

        User user1 = userRepo.findById(user.getId()).get();
        user1.setFullname("new name");
        User userUpdated = userRepo.save(user1);

        Assertions.assertThat(userUpdated.getFullname()).isEqualTo("new name");
    }

    @Test
    @Order(5)
    public void deleteUserTest() {
        User user = createUser();
        userRepo.save(user);

        User user1 = userRepo.findById(user.getId()).get();
        userRepo.delete(user1);

        Optional<User> optionalUser = userRepo.findByEmail("jenish@gmail.com");
        Assertions.assertThat(optionalUser).isEmpty();
    }

    private User createUser() {
        return User.builder()
                .fullname("jenish")
                .email("jenish@gmail.com")
                .mobileNo("9810000000")
                .password("password")
                .build();
    }
}
