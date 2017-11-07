package com.realTimeMessage.dwr.listener;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DWRScriptSessionListener implements ScriptSessionListener {

    /**
     * 用来保存 scriptSession
     */
    public static final Map<String, ScriptSession> scriptSessionMap = new HashMap<String, ScriptSession>();


    /**
     * 创建 scriptSession 时 执行
     * @param ev 创建 scriptSession 事件
     */
    public void sessionCreated(ScriptSessionEvent ev) {
        WebContext webContext = WebContextFactory.get();
        HttpSession session = webContext.getSession();
        ScriptSession scriptSession = ev.getSession();
        scriptSessionMap.put(session.getId(),scriptSession);
    }

    /**
     * 销毁 scriptSession 时 执行
     * @param ev 销毁 scriptSession 事件蚮
     */
    public void sessionDestroyed(ScriptSessionEvent ev) {
        WebContext webContext = WebContextFactory.get();
        HttpSession session = webContext.getSession();
        scriptSessionMap.remove(session.getId());
    }

    public static Collection<ScriptSession> getScriptSessions(){
        return scriptSessionMap.values();
    }

}
