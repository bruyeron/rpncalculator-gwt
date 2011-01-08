package com.ekino.web.rpnCalculatorApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ekino.web.rpnCalculatorApplication.client.RpnCalculatorApplicationService;

public class RpnCalculatorApplicationServiceImpl extends RemoteServiceServlet implements RpnCalculatorApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}