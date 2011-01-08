package com.ekino.web.rpnCalculatorApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RpnCalculatorApplicationService")
public interface RpnCalculatorApplicationService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use RpnCalculatorApplicationService.App.getInstance() to access static instance of RpnCalculatorApplicationServiceAsync
     */
    public static class App {
        private static RpnCalculatorApplicationServiceAsync ourInstance = GWT.create(RpnCalculatorApplicationService.class);

        public static synchronized RpnCalculatorApplicationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
