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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class RpnCalculatorApplication implements EntryPoint {

    LinkedList<BigDecimal> stack = new LinkedList<BigDecimal>();
    //TextArea display = new TextArea();
    DisplayPanel display;
    TextBox input = new TextBox();
    Button enterButton = new Button("Enter");
    List<CommandButton> commandButtons = new ArrayList<CommandButton>();

    EventBus eventBus = new SimpleEventBus();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
/*
        display.setReadOnly(true);
        display.setCharacterWidth(35);
        display.setVisibleLines(8);
*/
        this.display = new DisplayPanel(stack, eventBus);

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

        HorizontalPanel hp = new HorizontalPanel();
        hp.add(display);
        VerticalPanel vp = new VerticalPanel();
        CommandButton cb = new CommandButton(new ClearCommand(), eventBus);
        commandButtons.add(cb);
        vp.add(cb);
        cb = new CommandButton(new DropCommand(), eventBus);
        commandButtons.add(cb);
        vp.add(cb);
        cb = new CommandButton(new SwapCommand(), eventBus);
        commandButtons.add(cb);
        vp.add(cb);
        cb = new CommandButton(new DupCommand(), eventBus);
        commandButtons.add(cb);
        vp.add(cb);
        hp.add(vp);
        mainPanel.add(hp);

        final HorizontalPanel inputPanel = new HorizontalPanel();
        input.setVisibleLength(35);
        inputPanel.add(input);
        inputPanel.add(enterButton);
        mainPanel.add(inputPanel);

        HorizontalPanel commandsPanel = new HorizontalPanel();
        cb = new CommandButton(new PlusCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        cb = new CommandButton(new MinusCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        cb = new CommandButton(new MultiplyCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        cb = new CommandButton(new DivideCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        cb = new CommandButton(new SineCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        cb = new CommandButton(new CosineCommand(), eventBus);
        commandButtons.add(cb);
        commandsPanel.add(cb);
        mainPanel.add(commandsPanel);

        eventBus.addHandler(StackStateChangeEvent.TYPE, new StackStateChangeEventHandler() {
            @Override
            public void onStackStateChange(StackStateChangeEvent event) {
                //display.setText(displayStack());
                updateButtonsStates();
            }
        });
        eventBus.addHandler(CommandEvent.TYPE, new CommandEventHandler() {
            @Override
            public void onCommand(CommandEvent event) {
                try {
                    event.getCommand().execute(stack);
                    eventBus.fireEvent(new StackStateChangeEvent());
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        RootPanel.get("rpncalculator").add(mainPanel);
    }

    private void updateButtonsStates() {
        int stackDepth = stack.size();
        for (CommandButton b : commandButtons) {
            b.setEnabled(stackDepth >= b.getCommand().minimumStackDepth());
        }
    }

    private void inputBigDecimal() {
        stack.addFirst(new BigDecimal(input.getText()));
        input.setText("");
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
