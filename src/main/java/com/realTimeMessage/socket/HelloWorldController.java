package com.realTimeMessage.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class HelloWorldController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 广播
     * @param temp 消息
     */
    @MessageMapping(value = "/send")
    public void send(String temp){
        System.out.println("广播..." + temp );
        simpMessagingTemplate.convertAndSend("/topic",temp);
    }

    /**
     * 使用 convertAndSend 实现点对点传送消息
     * @param receive 接收方
     */
    @MessageMapping(value = "/send1")
    public void send1(String receive){
        System.out.println("向" + receive +"传送消息...");
        simpMessagingTemplate.convertAndSend(
                "/queue/position-updates" + "/" + receive,
                "使用 convertAndSend 实现点对点传送消息");
    }

    /**
     * 使用 @SendToUser 实现拉取自己的所数据
     * @param temp 可传可不传 主要是测试可以传输数据
     * @return temp
     */
    @MessageMapping(value = "/send2")
    @SendToUser("/queue/position-updates")
    public String send2(String temp){
        System.out.println("使用 @SendToUser 实现拉取自己的所数据" + temp);
        return temp;
    }

    /**
     * 使用 convertAndSendToUser 拉取自己的数据 有问题 需要传输 header
     * 发现即时使用 @SendToUser 也是自己内部在重构 header
     * @param temp 消息
     * @param headers 请求头信息
     * @param simpSessionId 自动生成的标识 以此区分不同的连接
     */
    @MessageMapping(value = "/send3")
    public void send3(String temp, @Headers Map<String, Object> headers, @Header String simpSessionId){
        System.out.println(temp +"*****************");
        System.out.println("@Header" + simpSessionId);
        System.out.println(MyChannelInterceptor.getPool().get(temp) + "......................");
        simpMessagingTemplate.convertAndSendToUser(simpSessionId,"/queue/position-updates",temp, headers);

/*
        simpMessagingTemplate.convertAndSendToUser(temp,"/queue/position-updates",temp,message.getHeaders());
        headers.put("simpSessionId", MyChannelInterceptor.getPool().get(temp));
        simpMessagingTemplate.convertAndSendToUser(MyChannelInterceptor.getPool().get(temp),"/queue/position-updates",temp);
*/
    }

}
