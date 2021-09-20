package com.gateway;

import com.gateway.model.Gateway;
import com.gateway.model.Peripheral;
import com.gateway.model.PeripheralStatus;
import com.gateway.repository.IGatewayRepository;
import com.gateway.repository.IPeripheralRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class GatewayApplicationTests {

    @Autowired
    IGatewayRepository gatewayRepository;
    @Autowired
    IPeripheralRepository peripheralRepository;

    /**
     * Test add new gateway
     */
    @Test
    public void addNewGatewayTest() {
        Gateway newGateway = new Gateway();
        newGateway.setName("gatewayTest1");
        newGateway.setIp("192.168.1.1");
        Gateway createdGateway = gatewayRepository.save(newGateway);
        assertTrue(newGateway.getName().equals(createdGateway.getName()));
        assertTrue(newGateway.getIp().equals(createdGateway.getIp()));
    }

    /**
     * Test add new Peripheral for a gateway
     */

    @Test
    public void addNewPeripheralTest() {
        Gateway newGateway = new Gateway();
        newGateway.setName("gatewayTest1");
        newGateway.setIp("192.168.1.1");
        Gateway createdGateway = gatewayRepository.save(newGateway);

        Peripheral newPeripheral = new Peripheral();
        newPeripheral.setVendor("vendor1");
        newPeripheral.setStatus(PeripheralStatus.offline);
        newPeripheral.setGateway(createdGateway);
        Peripheral createdPeripheral = peripheralRepository.save(newPeripheral);

        assertTrue(newPeripheral.getVendor().equals(createdPeripheral.getVendor()));
        assertTrue(newPeripheral.getStatus().equals(createdPeripheral.getStatus()));
    }

}

