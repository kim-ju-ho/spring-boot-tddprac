package com.sparta.deliveryapi.repository.user;

import com.sparta.deliveryapi.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
