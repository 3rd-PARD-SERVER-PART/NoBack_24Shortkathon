package com.example.demo.User.Repo;

import com.example.demo.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
