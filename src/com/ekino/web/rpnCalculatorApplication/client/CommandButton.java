/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client;

import com.ekino.web.rpnCalculatorApplication.client.event.CommandEvent;
import com.ekino.web.rpnCalculatorApplication.client.event.StackStateChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class CommandButton extends Button {

    final Command command;

    public CommandButton(final Command command, final EventBus eventBus) {
        super(command.getLabel(), new ClickHandler(){
            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new CommandEvent(command));
            }
        });
        this.command = command;
        setEnabled(false);
    }

    public Command getCommand() {
        return command;
    }
}
