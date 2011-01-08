package com.ekino.web.rpnCalculatorApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpnCalculatorApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
