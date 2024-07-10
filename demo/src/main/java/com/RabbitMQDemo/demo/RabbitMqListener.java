package com.RabbitMQDemo.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMqListener {
    @RabbitListener(queues = "Mobile")
    public void getMessage(User user){
        System.out.println(user.getName());


    }

}
