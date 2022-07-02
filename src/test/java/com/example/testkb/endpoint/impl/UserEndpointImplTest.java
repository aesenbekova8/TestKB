package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.mapper.UserViewMapper;
import com.example.testkb.service.BankService;
import com.example.testkb.service.RoleService;
import com.example.testkb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserEndpointImplTest {

    @Autowired
    private UserEndpointImpl userEndpoint;

    @MockBean
    private UserService userService;
    @MockBean
    private UserViewMapper userViewMapper;
    @MockBean
    private RoleService roleService;
    @MockBean
    private BankService bankService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private Bank bank;
    private String password;
    private Role role;
    private User user;

    @BeforeEach
    public void setup() {
        bank = new Bank(1L, "Some bank");
        password = "123456";
        role = new Role(1L, RoleName.ROLE_CASHIER);
        user = new User(
                1L,
                "admin",
                password,
                Collections.singleton(role),
                bank);
    }

    @Test
    void addCashier() {
        userEndpoint.addCashier(new UserCreateRequest(user.getUsername(), user.getPassword(), bank.getId()));
        Mockito.verify(bankService, Mockito.times(1)).getById(1L);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(password);
        Mockito.verify(roleService, Mockito.times(1)).getByName(role.getName());
//        Mockito.verify(userService, Mockito.times(1)).create(user);
//        Mockito.verify(userViewMapper, Mockito.times(1)).toUserView(user);
    }

    @Test
    void updatePassword() {
        userEndpoint.updatePassword(new PasswordUpdateRequest("123456", "654321"), new UserPrincipal(1L));
        Mockito.verify(userService, Mockito.times(1)).getById(user.getId());
    }
}