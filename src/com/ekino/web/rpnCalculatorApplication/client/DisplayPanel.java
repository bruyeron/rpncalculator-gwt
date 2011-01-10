/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client;

import com.ekino.web.rpnCalculatorApplication.client.event.StackStateChangeEvent;
import com.ekino.web.rpnCalculatorApplication.client.event.StackStateChangeEventHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class DisplayPanel extends Composite {

    private final ScrollPanel scrollPanel;
    private final Grid grid;

    private final EventBus eventBus;

    private final LinkedList<BigDecimal> stack;


    public DisplayPanel(LinkedList<BigDecimal> stack, EventBus eventBus){
        this.eventBus = eventBus;
        this.stack = stack;
        this.grid = new Grid(5,2);
        this.scrollPanel = new ScrollPanel(grid);
        this.scrollPanel.setSize("15em", "6em");

        initWidget(this.scrollPanel);

        this.scrollPanel.scrollToBottom();

        this.eventBus.addHandler(StackStateChangeEvent.TYPE, new StackStateChangeEventHandler() {
            @Override
            public void onStackStateChange(StackStateChangeEvent event) {
                renderStack();
            }
        });
    }

    private void renderStack(){
        this.grid.clear(true);
        if(stack.size() > this.grid.getRowCount()){
            this.grid.resizeRows(stack.size());
        } else if(stack.size() <= 5 && this.grid.getRowCount() > 5){
            this.grid.resizeRows(5);
        }
        int pos = 1;
        for(BigDecimal v : stack){
            this.grid.setText(this.grid.getRowCount()-pos++, 1, v.toString());
        }
    }
}
