package com.example.testkb.service.impl;

import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User(
                1L,
                "Admin",
                "123456",
                Collections.singleton(new Role(1L, RoleName.ROLE_ADMIN)),
                new Bank("Some bank")
        );
    }

    @Test
    void create_withNotExistsUsername() {
        userService.create(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void changePassword() {
        User expected = user;
        expected.setPassword("654321");

        userService.changePassword(user, "654321");
        assertEquals(expected, user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void getById() {
        Mockito.doReturn(user)
                .when(userRepository).getReferenceById(user.getId());
    }

    @Test
    void getByUsername() {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository).findByUsername(user.getUsername());
    }
}