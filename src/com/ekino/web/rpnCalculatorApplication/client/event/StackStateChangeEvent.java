/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class StackStateChangeEvent extends GwtEvent<StackStateChangeEventHandler> {

    public final static GwtEvent.Type<StackStateChangeEventHandler> TYPE = new GwtEvent.Type<StackStateChangeEventHandler>();

    @Override
    public Type<StackStateChangeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(StackStateChangeEventHandler handler) {
        handler.onStackStateChange(this);
    }
}
