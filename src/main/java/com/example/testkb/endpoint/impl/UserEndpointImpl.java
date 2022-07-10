package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.mapper.UserViewMapper;
import com.example.testkb.service.BankService;
import com.example.testkb.service.RoleService;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserEndpointImpl implements UserEndpoint {

    private final UserService userService;
    private final UserViewMapper userViewMapper;
    private final RoleService roleService;
    private final BankService bankService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEndpointImpl(UserService userService,
                            UserViewMapper userViewMapper,
                            RoleService roleService,
                            BankService bankService,
                            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userViewMapper = userViewMapper;
        this.roleService = roleService;
        this.bankService = bankService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserView addCashier(@NonNull UserCreateRequest request,
                               @CurrentUser UserPrincipal currentUser) {
        User admin = userService.getById(currentUser.getId());
        Bank bank = bankService.getById(admin.getBank().getId());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Role role = roleService.getByName(RoleName.ROLE_CASHIER);
        User cashier = userService.create(request, bank, encodedPassword, role);

        return userViewMapper.toUserView(cashier);
    }

    @Override
    @Transactional
    public UserView updatePassword(@NonNull PasswordUpdateRequest request,
                                   @CurrentUser UserPrincipal currentUser) {
        return userViewMapper.toUserView(userService.changePassword(request, currentUser));
    }

    @Override
    public List<UserView> getCashiersByBank(Long bankId) {
        return userService.getAllCashiersByBankId(bankId).stream()
                .map(userViewMapper::toUserView)
                .collect(Collectors.toList());
    }
}
