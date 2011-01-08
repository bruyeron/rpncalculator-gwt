package com.ekino.web.rpnCalculatorApplication.client;

import com.ekino.web.rpnCalculatorApplication.client.commands.*;
import com.ekino.web.rpnCalculatorApplication.client.event.CommandEvent;
import com.ekino.web.rpnCalculatorApplication.client.event.CommandEventHandler;
import com.ekino.web.rpnCalculatorApplication.client.event.StackStateChangeEvent;
import com.ekino.web.rpnCalculatorApplication.client.event.StackStateChangeEventHandler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class RpnCalculatorApplication implements EntryPoint {

    LinkedList<BigDecimal> stack = new LinkedList<BigDecimal>();
    TextArea display = new TextArea();
    TextBox input = new TextBox();
    Button enterButton = new Button("Enter");
    HorizontalPanel commandsPanel = new HorizontalPanel();

    EventBus eventBus = new SimpleEventBus();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        display.setReadOnly(true);
        display.setCharacterWidth(30);
        display.setVisibleLines(8);

        enterButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                inputBigDecimal();
            }
        });
        input.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    inputBigDecimal();
                }
            }
        });

        final VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(new Label("RPN Calculator"));
        mainPanel.add(display);

        final HorizontalPanel inputPanel = new HorizontalPanel();
        inputPanel.add(input);
        inputPanel.add(enterButton);
        mainPanel.add(inputPanel);

        commandsPanel.add(new CommandButton(new PlusCommand(), eventBus));
        commandsPanel.add(new CommandButton(new MinusCommand(), eventBus));
        commandsPanel.add(new CommandButton(new MultiplyCommand(), eventBus));
        commandsPanel.add(new CommandButton(new DivideCommand(), eventBus));
        commandsPanel.add(new CommandButton(new DupCommand(), eventBus));
        commandsPanel.add(new CommandButton(new SwapCommand(), eventBus));
        commandsPanel.add(new CommandButton(new DropCommand(), eventBus));
        mainPanel.add(commandsPanel);

        eventBus.addHandler(StackStateChangeEvent.TYPE, new StackStateChangeEventHandler() {
            @Override
            public void onStackStateChange(StackStateChangeEvent event) {
                updateButtonsStates();
            }
        });
        eventBus.addHandler(CommandEvent.TYPE, new CommandEventHandler() {
            @Override
            public void onCommand(CommandEvent event) {
                try {
                    event.getCommand().execute(stack);
                    display.setText(displayStack());
                    eventBus.fireEvent(new StackStateChangeEvent());
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        RootPanel.get().add(mainPanel);
    }

    private void updateButtonsStates() {
        int stackDepth = stack.size();
        for (Iterator<Widget> it = commandsPanel.iterator(); it.hasNext();) {
            Widget w = it.next();
            if (w instanceof CommandButton) {
                CommandButton b = (CommandButton) w;
                b.setEnabled(stackDepth >= b.getCommand().minimumStackDepth());
            }
        }
    }

    private void inputBigDecimal() {
        stack.addFirst(new BigDecimal(input.getText()));
        input.setText("");
        display.setText(displayStack());
        eventBus.fireEvent(new StackStateChangeEvent());
    }

    private String displayStack() {
        StringBuilder sb = new StringBuilder();
        boolean started = false;
        for (BigDecimal entry : stack) {
            if (started) {
                sb.insert(0, "\n");
            } else {
                started = true;
            }
            sb.insert(0, entry);
        }
        return sb.toString();
    }

}
