package com.auth2.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth2.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
   
    Optional<Role> findByAuthority(String authority);
   
}