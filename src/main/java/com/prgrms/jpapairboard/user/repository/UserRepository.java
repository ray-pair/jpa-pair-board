package com.prgrms.jpapairboard.user.repository;

import com.prgrms.jpapairboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
