package com.coffeeandcode.amqp.producerapi;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class MachineRepository {

    private List<Machine> machines;

    public MachineRepository() {
        this.machines = new ArrayList<>();
    }

    public Machine save(Machine machine) {
        machines.add(machine);
        return machine;
    }

}
