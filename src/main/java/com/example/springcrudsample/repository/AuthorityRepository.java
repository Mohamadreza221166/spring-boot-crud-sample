package com.example.springcrudsample.repository;

import com.example.springcrudsample.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {}
