package com.coffeeandcode.amqp.producerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MachineService {


    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Connection connection;

    public MachineService(Connection connection){
        this.connection = connection;
    }

    public Machine save(Machine machine) throws IOException {
        Machine machineSaved = machineRepository.save(machine);
//        sendMachineToRabbit(machineSaved);
        sendMachine(machineSaved);
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

    public void sendMachine(Machine machine) throws IOException {
        byte[] messageBodyBytes = "{\"brand\": \"volkswagen\",\"model\": \"golf\",\"year\": 2020}".getBytes();
        Channel channel = connection.createChannel();
//        channel.exchangeDeclare("Machines", "direct");
        channel.basicPublish("Machines", "",
                null,
                messageBodyBytes);
    }
}
