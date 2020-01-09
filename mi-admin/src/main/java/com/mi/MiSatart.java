package com.mi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 * 
 * @author lhp
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
@EnableRabbit
public class MiSatart
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MiSatart.class, args);
        System.out.println(" 系统启动成功_______  _______  _______ \n" +
                "(  ___  )(  ___  )(  ___  )\n" +
                "| (   ) || (   ) || (   ) |\n" +
                "| |   | || (___) || |   | |\n" +
                "| |   | ||  ___  || |   | |\n" +
                "| | /\\| || (   ) || | /\\| |\n" +
                "| (_\\ \\ || )   ( || (_\\ \\ |\n" +
                "(____\\/_)|/     \\|(____\\/_)");
    }
}