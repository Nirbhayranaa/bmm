package com.nirbhay.bmm.usermangement.service;

import com.nirbhay.bmm.model.usermanagement.AppUser;
import com.nirbhay.bmm.usermangement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<AppUser> fetchAllUsers(Pageable pageable) {
        log.info("Fetching all users with pagination: {}", pageable);
        return userRepository.findAll(pageable);
    }

    @Override
    public AppUser saveUser(AppUser appUser) throws Exception {
        log.info("Saving user: {}", appUser);
        // Implement logic for saving the user, including validation, password encoding, etc.
        return userRepository.save(appUser);
    }

    @Override
    public AppUser fetchUserById(long id) throws Exception {
        log.info("Fetching user by ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }
}
