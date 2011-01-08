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
public class DivideCommand extends BaseCommand {

    public DivideCommand() {
        super("/");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        BigDecimal v1 = stack.removeFirst();
        BigDecimal v2 = stack.removeFirst();
        stack.addFirst(v2.divide(v1, MathContext.DECIMAL128));
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 2;
    }
}
