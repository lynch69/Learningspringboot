package com.example.rest.controller.restcontroller.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.controller.restcontroller.users.users;

public interface PostRepository extends JpaRepository<users, Integer>{

}
