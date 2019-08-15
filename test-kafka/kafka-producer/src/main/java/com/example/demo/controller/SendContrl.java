package com.example.demo.controller;

import com.example.demo.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xulei on 2019/8/6.
 */
@RestController
@RequestMapping("/SendContrl")
public class SendContrl {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/sendMessageToKafka")
    public  String sendMessageToKafka() {
        String taskid = "123456";
//kakfa的推送消息方法有多种，可以采取带有任务key的，也可以采取不带有的（不带时默认为null）
        kafkaSender.send("testTopic", taskid, "success");
        return "hi guy!";
    }
    @RequestMapping("/sendMessageTokafka1")
    public String sendMessageTokafka1(){
        String taskid="1";
        kafkaSender.send("testTopic1",taskid,"testTopic1");
        return "success";
    }
}
