package com.gateway.controller;

import com.gateway.exception.ResponseErrorException;
import com.gateway.model.Gateway;
import com.gateway.repository.IGatewayRepository;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class GatewayController {

    @Autowired
    IGatewayRepository gatewayRepository;

    /**
     * @return List of all Gateway
     */
    @GetMapping("/gateway")
    public List<Gateway> getAllGateway() {
        return gatewayRepository.findAll();
    }

    /**
     * @param gateway_id
     * @return Gateway by id
     */
    @GetMapping("/gateway/{gateway_id}")
    public Gateway getGatewayById(@PathVariable UUID gateway_id) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            return gateway.get();
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    /**
     * Create new Gateway
     *
     * @param gateway
     * @return
     */
    @PostMapping("/gateway")
    public Gateway createGateway(@Valid @RequestBody Gateway gateway) {
            return gatewayRepository.save(gateway);
    }

    /**
     * Update Gateway by id
     *
     * @param gateway_id
     * @param gateway
     * @return
     */

    @PutMapping("/gateway/{gateway_id}")
    public Gateway updateGatewayById(@PathVariable UUID gateway_id, @Valid @RequestBody Gateway gateway) {
        Optional<Gateway> previousGateway = gatewayRepository.findById(gateway_id);
        if (previousGateway.isPresent()) {
            Gateway tempGateway = previousGateway.get();
            tempGateway.setName(gateway.getName());
            tempGateway.setIp(gateway.getIp());
            return gatewayRepository.save(tempGateway);
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    /**
     * Delete Gateway by id
     *
     * @param gateway_id
     * @return
     */
    @DeleteMapping("/gateway/{gateway_id}")
    public String deleteGatewayById(@PathVariable UUID gateway_id) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            gatewayRepository.delete(gateway.get());
            return "Gateway id:" + gateway_id + " was removed succesfully";
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    @GetMapping("")
    public String testAPI(){
        return "Welcome to Gateway API !!!!!";
    }


}
