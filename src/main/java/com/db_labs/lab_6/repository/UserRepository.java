package com.db_labs.lab_6.repository;

import com.db_labs.lab_6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
