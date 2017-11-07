package com.realTimeMessage.dwr.controller;

import com.realTimeMessage.dwr.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestCntroller {

    @RequestMapping(value = "ttttt")
    @ResponseBody
    public User test(){
        return new User("三生诺","woman");
    }

}
