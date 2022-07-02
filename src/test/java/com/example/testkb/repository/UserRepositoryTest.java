package com.example.testkb.repository;

import com.example.testkb.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_byExistsUsername() {
        User user = userRepository.findByUsername("Admin").get();
        Assertions.assertThat(user.getUsername()).isEqualTo("Admin");
    }

    @Test
    void findByUsername_byNotExistsUsername() {
        User user = userRepository.findByUsername("AnotherAdmin").get();
        org.junit.jupiter.api.Assertions.assertThrows(new EntityNotFoundException());
    }

    @Test
    void existsByUsername() {
        boolean isExist = userRepository.existsByUsername("Admin");
        Assertions.assertThat(isExist).isTrue();
    }
}