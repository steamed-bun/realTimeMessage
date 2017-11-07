package com.realTimeMessage.dwr.controller;

import com.realTimeMessage.dwr.entity.User;
import com.realTimeMessage.dwr.listener.DWRScriptSessionListener;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@RequestMapping(value = "/dWR")
@RemoteProxy(name = "dWRTestController")
public class DWRTestController {

    @RequestMapping(value = "/test.html")
    @RemoteMethod
    @ResponseBody
    public String test(){
        return "hello";
    }

    @RemoteMethod
    @RequestMapping(value = "/sendMsg",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User sendMsg(final String funcName){
        final User user  = new User("达拉崩吧","man");
        user.setName("kanakn");
        Runnable run = new Runnable(){
            private ScriptBuffer script = new ScriptBuffer();
            public void run() {
                //设置要调用的 js及参数
                script.appendCall(funcName , user);
                //得到所有ScriptSession
                Collection<ScriptSession> sessions = DWRScriptSessionListener.getScriptSessions();
                //遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript( script);
                }
            }
        };
        //执行推送
        Browser. withAllSessions(run);
        return user;
    }


}
