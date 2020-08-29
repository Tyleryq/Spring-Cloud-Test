package com.test.springcloud.messagerec;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@EnableBinding(Sink.class)  //设置为消息接收者
public class ReceiveMessage {
    @StreamListener(Sink.INPUT) //进行消息监听
    public void input(Message<String> message){
        System.out.println("消息消费者收到:"+message.getPayload());
    }
}
