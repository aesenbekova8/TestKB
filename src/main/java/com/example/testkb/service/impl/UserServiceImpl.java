package com.example.testkb.service.impl;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.exception.LogicException;
import com.example.testkb.repository.UserRepository;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(@NonNull UserCreateRequest request,
                       @NonNull Bank bank,
                       @NonNull String encodedPassword,
                       @NonNull Role role) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new LogicException(format("User with username: %s has existed yet", request.getUsername()));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setBank(bank);
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User changePassword(@NonNull PasswordUpdateRequest request,
                               @CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getReferenceById(currentUser.getId());
        user.setPassword(request.getNewPassword());

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return Optional.of(userRepository.getReferenceById(id)).
                orElseThrow(() -> new EntityNotFoundException(format("User with id: %s not found", id)));
    }

    @Override
    public User getByUsername(@NonNull String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }

    @Override
    public List<User> getAllCashiersByBankId(@NonNull Long bankId) {
        return userRepository.findAllByBankId(bankId);
    }
}
