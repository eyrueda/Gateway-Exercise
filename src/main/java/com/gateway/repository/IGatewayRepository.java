package com.gateway.repository;

import com.gateway.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IGatewayRepository extends JpaRepository<Gateway, UUID> {
}
