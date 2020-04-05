package gentle.controller;

import gentle.rocketmq.MqProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("gentle")
public class TestController {

    @Resource
    private MqProducer producer;

    @RequestMapping("/first")
    private String first(String name, String word) {
    	System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return name + "，这是一个名字。 " + word + "这是一句描述。";
    }
}
