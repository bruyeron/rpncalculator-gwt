/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.commands;

import com.ekino.web.rpnCalculatorApplication.client.Command;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public abstract class BaseCommand implements Command {

    protected final String label;

    protected BaseCommand(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    protected void checkStackState(LinkedList<BigDecimal> stack) throws IllegalStateException {
        if(stack.size() < minimumStackDepth()){
            throw new IllegalStateException("not enough argument for command " + label);
        }
    }
}
