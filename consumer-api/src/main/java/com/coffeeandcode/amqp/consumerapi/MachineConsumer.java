package com.coffeeandcode.amqp.consumerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MachineConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @RabbitListener(queues = MachineAMQPConfig.QUEUE)
//    public void consumer(Message message) {
//        simpMessagingTemplate.convertAndSend(MachineWebSocketConfiguration.BROKER,
//                new String(message.getBody()));
//    }
}
