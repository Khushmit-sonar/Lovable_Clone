package com.khushmitsonar.projects.lovable_clone.repository;

import com.khushmitsonar.projects.lovable_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
