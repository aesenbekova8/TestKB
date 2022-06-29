package com.example.testkb.service;

import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.repository.RoleRepository;
import com.example.testkb.repository.UserRepository;
import com.example.testkb.service.impl.UserServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = RoleRepository.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private BankService bankService;

    @BeforeAll
    public void setup() {
        Role roleCashier = new Role(1L, RoleName.ROLE_CASHIER);
        roleRepository.save(roleCashier);
        Role roleAdmin = new Role(2L, RoleName.ROLE_ADMIN);
        roleRepository.save(roleAdmin);
    }

//    @Before
//    public void setup() {
//        userRepository = Mockito.mock(UserRepository.class);
////        roleRepository = Mockito.mock(RoleRepository.class);
//        passwordEncoder = Mockito.mock(PasswordEncoder.class);
//        bankService = Mockito.mock(BankService.class);
//        this.userService = new UserServiceImpl(this.userRepository, this.roleRepository, this.passwordEncoder, this.bankService);
//    }

    @Test
    public void createUser() {
        Bank bank = new Bank(1L, "Some bank");
        UserCreateRequest request = new UserCreateRequest("admin", "123456", 1L);
        User user = new User(1L, "admin", "123456", bank);
        Role roleCashier = new Role(1L, RoleName.ROLE_CASHIER);
        roleRepository.save(roleCashier);
        Role roleAdmin = new Role(2L, RoleName.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

//        Assert.assertEquals(roleCashier.getName(), RoleName.ROLE_CASHIER);

        Mockito.when(userService.create(request)).thenReturn(user);
    }

    @Test
    public void getRole() {
        Role roleWithId = roleRepository.getReferenceById(1L);
        Role roleWithName = roleRepository.findByName(RoleName.ROLE_CASHIER).get();

        Assert.assertEquals(roleWithId, roleWithName);
    }
}
