package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Delivery;
import com.virtusa.dlvery.entities.DlveryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DlveryUserRepository extends JpaRepository<DlveryUser, UUID> {
    // You can define custom query methods here if needed
    // For example, findByUsername method
    Optional<DlveryUser> findByUsername(String username);
    Optional<DlveryUser> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
