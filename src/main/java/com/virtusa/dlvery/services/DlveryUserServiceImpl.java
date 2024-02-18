package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DlveryUser;
import com.virtusa.dlvery.exceptions.DuplicateEmailException;
import com.virtusa.dlvery.exceptions.DuplicateUsernameException;
import com.virtusa.dlvery.exceptions.UserNotFoundException;
import com.virtusa.dlvery.repositories.DlveryUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DlveryUserServiceImpl implements DlveryUserService {

    private final Logger logger = LoggerFactory.getLogger(DlveryUserServiceImpl.class);

    @Autowired
    private DlveryUserRepository userRepository;

    @Override
    public List<DlveryUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public DlveryUser getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public DlveryUser createUser(DlveryUser user) {
        validateUniqueConstraints(user);
        user.setPassword(encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public DlveryUser updateUser(UUID userId, DlveryUser user) {
        DlveryUser existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        validateUniqueConstraints(user);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setOtherDetails(user.getOtherDetails());
        existingUser.setPassword(encodePassword(user.getPassword()));

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
        logger.info("User deleted with ID: {}", userId);
    }

    private void validateUniqueConstraints(DlveryUser user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    if (!existingUser.getUserID().equals(user.getUserID())) {
                        throw new DuplicateUsernameException("Username already exists: " + user.getUsername());
                    }
                });

        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    if (!existingUser.getUserID().equals(user.getUserID())) {
                        throw new DuplicateEmailException("Email already exists: " + user.getEmail());
                    }
                });
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
