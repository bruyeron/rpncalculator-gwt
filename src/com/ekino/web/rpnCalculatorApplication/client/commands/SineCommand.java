/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client.commands;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public class SineCommand extends BaseCommand {

    public SineCommand() {
        super("SIN");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        BigDecimal v = stack.removeFirst();
        double cos = Math.sin(v.doubleValue());
        stack.addFirst(new BigDecimal(cos));
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 1;
    }
}
