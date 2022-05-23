package com.prgrms.jpapairboard.user.service;

import com.prgrms.jpapairboard.user.entity.User;
import com.prgrms.jpapairboard.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class DefaultUserService implements UserService{
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        Assert.notNull(user, "User should not be null");

        userRepository.save(user);
    }
}
