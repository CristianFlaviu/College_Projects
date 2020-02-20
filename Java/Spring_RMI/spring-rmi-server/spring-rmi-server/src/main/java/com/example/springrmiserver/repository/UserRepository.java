package com.example.springrmiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrmiserver.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>  {

}
