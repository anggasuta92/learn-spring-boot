package com.test.demo.repository;
import org.springframework.stereotype.Repository;
import com.test.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
