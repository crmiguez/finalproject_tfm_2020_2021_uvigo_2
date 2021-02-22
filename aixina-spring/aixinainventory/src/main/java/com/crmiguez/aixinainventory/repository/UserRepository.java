package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(Long userId);
    User findByUserName(String username);
}
