package com.realTimeMessage.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class HelloWorldController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/send")
    @SendToUser("/queue/position-updates")
    public String send(String temp){
        System.out.println(temp +"*****************");
//        simpMessagingTemplate.convertAndSend("/queue/position-updates",temp);
//        simpMessagingTemplate.convertAndSendToUser("username_1","/queue/position-updates",temp);
        return temp;
    }

}
