package com.db_labs.lab_6.repository;

import com.db_labs.lab_6.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
