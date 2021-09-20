package com.gateway.repository;

import com.gateway.model.Gateway;
import com.gateway.model.Peripheral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeripheralRepository extends JpaRepository<Peripheral, Long> {

    /**
     * @param gateway
     * @return list of all peripheral belong to a gateway given by parameter
     */
    public List<Peripheral> findPeripheralsByGateway(Gateway gateway);
}
