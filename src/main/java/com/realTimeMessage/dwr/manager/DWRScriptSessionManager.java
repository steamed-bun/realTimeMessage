package com.realTimeMessage.dwr.manager;

import com.realTimeMessage.dwr.listener.DWRScriptSessionListener;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class DWRScriptSessionManager extends DefaultScriptSessionManager {

    public DWRScriptSessionManager() {
        this.addScriptSessionListener(new DWRScriptSessionListener());
    }
}
