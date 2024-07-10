package com.RabbitMQDemo.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/rabbit/{name}")
    public String rabbittest(@PathVariable String name){
        User user=new User(1L,name);
        rabbitTemplate.convertAndSend("Mobile",user);
        //rabbitTemplate.convertAndSend("Direct-Exchange", "boun", user);
        return "Başarılı";



    }

    @GetMapping("/rabiit/message/{name}")
    public String rabbitMessage(@PathVariable String name) throws IOException {
        User user=new User(1L,name);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutput output=new ObjectOutputStream(bos);
        output.flush();
        output.close();

        byte[] bytes= bos.toByteArray();
        bos.close();


        Message message= MessageBuilder.withBody(bytes).setHeader("istem1","mobile")
                .setHeader("item2","television").build();

        rabbitTemplate.send("Headers-Exchange","",message);


        return "Success";

    }
}
