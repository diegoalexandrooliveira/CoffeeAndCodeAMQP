package com.coffeeandcode.amqp.producerapi;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Configuration
public class MachineAMQPConfig {

    public static String EXCHANGE_NAME = "Machines";

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Connection connectionFactory() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("<protocolo>://<host>:<porta>>");
        factory.setUsername("<usr>>");
        factory.setPassword("<pwd>");

//        factory.setVirtualHost("localhost");
//        factory.setVirtualHost("amqps://b-c40bae49-4470-483d-9f2a-653c83a12eb1.mq.us-east-1.amazonaws.com");
//        factory.setPort(5671);
//        factory.isSSL();

        return factory.newConnection();
    }
}
