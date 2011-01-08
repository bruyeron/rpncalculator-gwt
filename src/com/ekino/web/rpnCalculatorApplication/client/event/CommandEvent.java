/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.event;

import com.ekino.web.rpnCalculatorApplication.client.Command;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class CommandEvent extends GwtEvent<CommandEventHandler> {

    public final static Type<CommandEventHandler> TYPE = new Type<CommandEventHandler>();

    private final Command command;

    public CommandEvent(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public Type<CommandEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CommandEventHandler handler) {
        handler.onCommand(this);
    }
}
