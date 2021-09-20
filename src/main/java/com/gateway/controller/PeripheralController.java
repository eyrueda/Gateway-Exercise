package com.gateway.controller;

import com.gateway.exception.ResponseErrorException;
import com.gateway.model.Gateway;
import com.gateway.model.Peripheral;
import com.gateway.repository.IGatewayRepository;
import com.gateway.repository.IPeripheralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/peripheral")
public class PeripheralController {

    @Autowired
    IPeripheralRepository peripheralRepository;

    @Autowired
    IGatewayRepository gatewayRepository;

    /**
     * @param gateway_id
     * @return list of peripheral for the gateway id given by param
     */
    @GetMapping("/{gateway_id}")
    public List<Peripheral> getAllPeripheralsByGateway(@PathVariable UUID gateway_id) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            return peripheralRepository.findPeripheralsByGateway(gateway.get());
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    /**
     * Create a new Peripheral for a Gateway
     *
     * @param gateway_id
     * @param peripheral
     * @return
     */
    @PostMapping("/{gateway_id}")
    public Peripheral createPeripheralForGateway(@PathVariable UUID gateway_id, @Valid @RequestBody Peripheral peripheral) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            if (gateway.get().getPeripheral().size() < 10) {
                peripheral.setGateway(gateway.get());
                return peripheralRepository.save(peripheral);
            } else {
                throw new ResponseErrorException("The gateway:" + gateway_id + " can not add more peripheral");
            }
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    /**
     * @param gateway_id
     * @param id
     * @return Peripheral by gateway_id and peripheral id
     */
    @GetMapping("/get/{gateway_id}/peripheral/{id}")
    public Peripheral getSpecificPeripheralForGateway(@PathVariable UUID gateway_id, @PathVariable long id) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            Optional<Peripheral> peripheral = peripheralRepository.findById(id);
            if (peripheral.isPresent()) {
                return peripheral.get();
            } else {
                throw new ResponseErrorException("Peripheral id:" + id + " was not found");
            }
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }

    /**
     * @param gateway_id
     * @param id
     * @return
     */

    @DeleteMapping("/delete/{gateway_id}/peripheral/{id}")
    public String deleteSpecificPeripheralForGateway(@PathVariable UUID gateway_id, @PathVariable long id) {
        Optional<Gateway> gateway = gatewayRepository.findById(gateway_id);
        if (gateway.isPresent()) {
            Optional<Peripheral> peripheral = peripheralRepository.findById(id);
            if (peripheral.isPresent()) {
                peripheralRepository.delete(peripheral.get());
                return "Peripheral id:" + id + " was removed succesfully";
            } else {
                throw new ResponseErrorException("Peripheral id:" + id + " was not found");
            }
        } else {
            throw new ResponseErrorException("Gateway id: " + gateway_id + " was not found");
        }
    }
}
