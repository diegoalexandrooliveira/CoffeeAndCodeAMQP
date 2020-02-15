package com.coffeeandcode.amqp.producerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping
    public ResponseEntity<Machine> save(@RequestBody Machine machine) {
        Machine machineSaved = machineService.save(machine);
        System.out.println(String.format("Machine saved: %s", machineSaved.toString()));
        return ResponseEntity.ok(machineSaved);
    }
}
