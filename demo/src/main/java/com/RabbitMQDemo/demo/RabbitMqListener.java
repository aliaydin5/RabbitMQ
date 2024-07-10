package com.RabbitMQDemo.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
@EnableRabbit
public class RabbitMqListener {
    @RabbitListener(queues = "Mobile")
    public void getMessage(User user){
        System.out.println(user.getName());


    }

    @RabbitListener(queues = "Mobile")
    public void readMessage(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis=new ByteArrayInputStream(message);
        ObjectInput in=new ObjectInputStream(bis);
        User user=(User) in.readObject();
        in.close();
        bis.close();
        System.out.println(user.getName());
    }


}
