/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.commands;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class MultiplyCommand extends BaseCommand {

    public MultiplyCommand() {
        super("*");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        stack.addFirst(stack.removeFirst().multiply(stack.removeFirst(), MathContext.DECIMAL128));
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 2;
    }
}
