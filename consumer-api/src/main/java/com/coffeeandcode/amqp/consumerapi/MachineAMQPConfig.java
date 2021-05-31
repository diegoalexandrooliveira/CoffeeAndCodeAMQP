package com.coffeeandcode.amqp.consumerapi;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Configuration
public class MachineAMQPConfig {

    public static final String QUEUE = "machines-created";
    public static final String EXCHANGE_NAME = "Machines";
    public static final String ROUTING_KEY = "";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


//    @Bean
//    public Exchange declareExchange() {
//        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
//                .durable(true)
//                .build();
//    }

//    @Bean
//    public Queue declareQueue() {
//        return QueueBuilder.durable(QUEUE)
//                .build();
//    }

//    @Bean
//    public Binding declareBinding(Exchange exchange, Queue queue) {
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(ROUTING_KEY)
//                .noargs();
//    }

    @Bean
    public Connection connectionFactory() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("<protocolo>://<host>:<porta>>");
        factory.setUsername("<usr>>");
        factory.setPassword("<pwd>");

        Connection connection = factory.newConnection();
//        boolean autoAck = false;
        Channel channel = connection.createChannel();

//        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, EXCHANGE_NAME, "");
//
//        channel.queueDeclare(QUEUE, true, false, false, null);
//
//        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            simpMessagingTemplate.convertAndSend(MachineWebSocketConfiguration.BROKER,
                new String(message));
        };
        channel.basicConsume("machines-created", true, deliverCallback, consumerTag -> {


        });

        return connection;
    }


}
