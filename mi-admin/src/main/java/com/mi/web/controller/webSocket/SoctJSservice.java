package com.mi.web.controller.webSocket;

import com.mi.system.service.ISysUserOnlineService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SoctJSservice {
    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ISysUserOnlineService userOnlineService;
    @RabbitListener(queues = "userMsg")
    public void test(UserMessage msg){//MQ推给tomcat
       // List<SysUserOnline> onlines = userOnlineService.selectUserOnlineList(null);
//        System.err.println(onlines);
//        System.err.println(msg);
//        simpMessagingTemplate.convertAndSend("/server/msg",test);
//        ShiroUtils.getUserId();
        simpMessagingTemplate.convertAndSendToUser(msg.getUserId(),"/toUser",msg);
    }
//    @Scheduled(cron="0/5 * *  * * ? ")
//    public void test(){//这里退给MQ
//        System.out.println(rabbitTemplate);
//    }
//
//    @Scheduled(cron="0/3 * *  * * ? ")
//    public void test1(){
//        UserMessage u1=new UserMessage();
//        u1.setUserId(9+"");
//        u1.setContext("this .is message for user who`s id is 9");
//        rabbitTemplate.convertAndSend("userMsg.direct","userMsg",u1);
//
//    }
}
