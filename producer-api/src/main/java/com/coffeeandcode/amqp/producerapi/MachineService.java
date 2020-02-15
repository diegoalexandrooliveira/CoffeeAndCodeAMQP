package com.coffeeandcode.amqp.producerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {


    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Machine save(Machine machine) {
        Machine machineSaved = machineRepository.save(machine);
        sendMachineToRabbit(machineSaved);
        return machineSaved;
    }

    public void sendMachineToRabbit(Machine machine) {
        try {
            String json = new ObjectMapper().writeValueAsString(machine);
            rabbitTemplate.convertAndSend(MachineAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
