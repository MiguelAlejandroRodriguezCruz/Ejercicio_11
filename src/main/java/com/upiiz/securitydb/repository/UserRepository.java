package com.upiiz.securitydb.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upiiz.securitydb.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    // entiende quiery methods
    Optional<UserEntity> findUserEntityByUserName(String username);
}
