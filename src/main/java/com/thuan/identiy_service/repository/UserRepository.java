package com.thuan.identiy_service.repository;

import com.thuan.identiy_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
