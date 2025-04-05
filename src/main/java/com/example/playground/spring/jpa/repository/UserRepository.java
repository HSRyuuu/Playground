package com.example.playground.spring.jpa.repository;

import com.example.playground.spring.jpa.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByName(String name);

    @Query("SELECT u FROM AppUser u LEFT JOIN FETCH u.orders LEFT JOIN FETCH u.payments WHERE u.name = :name")
    Optional<AppUser> findWithOrdersAndPaymentsByName(@Param("name") String name);
}
