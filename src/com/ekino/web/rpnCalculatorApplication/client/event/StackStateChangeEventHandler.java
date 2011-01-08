/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public interface StackStateChangeEventHandler extends EventHandler {

    void onStackStateChange(StackStateChangeEvent event);
}
