package com.example.javashopspring.domain.resources;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.apache.commons.lang3.Validate;
import com.example.javashopspring.enums.pc.CPU;
import com.example.javashopspring.enums.pc.GPU;
import com.example.javashopspring.enums.pc.RAM;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("COMPUTER")
public class Computer extends Electronics {
    @Enumerated(EnumType.STRING)
    CPU cpu;
    @Enumerated(EnumType.STRING)
    GPU gpu;
    @Enumerated(EnumType.STRING)
    RAM ram;
    public  Computer(){}
    public Computer(String id, String name, BigDecimal price, int quantity, CPU cpu, GPU gpu, RAM ram) {
        super(id, name, price, quantity);
        Validate.notNull(cpu.name(), "Please select valid CPU");
        Validate.notNull(gpu.name(), "Please select valid GPU");
        Validate.notNull(ram.name(), "Please select valid RAM");
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;

    }
}
