package com.example.testkb.service.impl;

import com.example.testkb.entity.User;
import com.example.testkb.exception.LogicException;
import com.example.testkb.repository.UserRepository;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new LogicException(format("User with username: %s has existed yet", user.getUsername()));
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(@NonNull Long id) {
        return Optional.of(userRepository.getReferenceById(id)).
                orElseThrow(() -> new EntityNotFoundException(format("User with id: %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(@NonNull String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }
}
