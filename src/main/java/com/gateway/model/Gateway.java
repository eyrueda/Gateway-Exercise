package com.gateway.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "gateway")
@Validated
public class Gateway implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID serialNumber;

    private String name;

    /**
     * ipV4 address validation
     */
    @NotNull
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$", message = "Incorrect ipV4 address")
    private String ip;

    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(max = 10)
    @Valid
    private List<Peripheral> peripheral;

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Peripheral> getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(List<Peripheral> peripheral) {
        this.peripheral = peripheral;
    }
}
