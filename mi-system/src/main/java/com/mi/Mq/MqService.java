package com.mi.Mq;

import com.mi.common.utils.spring.SpringUtils;
import com.mi.system.service.ISysUserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MqService {
    @Autowired
    JavaMailSender javaMailSender;
    ISysUserService userService= SpringUtils.getBean(ISysUserService.class);
    /**
     * 采购通知邮件
     * @param mailMsg
     */
    @RabbitListener(queues = "mail")
    public void sendEmailToUser(MailMsg mailMsg){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject(mailMsg.getSubject());
        mailMessage.setText(mailMsg.getText());
        mailMessage.setFrom(mailMsg.getFrom());
        mailMessage.setTo(mailMsg.getTo());
        javaMailSender.send(mailMessage);
    }
    @RabbitListener(queues = "allMail")
    public void sendEmailToAllUser(MailMsg mailMsg){
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//        SysUser user=new SysUser();
//
//
//        List<SysUser> sysUsers = userService.selectUserList(user);
//        for (SysUser s : sysUsers) {
//            mailMessage.setSubject(mailMsg.getSubject());
//            mailMessage.setText(mailMsg.getText());
//            mailMessage.setFrom(mailMsg.getFrom());
//            mailMessage.setTo(s.getEmail());
//            javaMailSender.send(mailMessage);
//        }


    }
}
