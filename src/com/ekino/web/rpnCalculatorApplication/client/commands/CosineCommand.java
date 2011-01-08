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
public class CosineCommand extends BaseCommand {

    public CosineCommand() {
        super("COS");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        BigDecimal v = stack.removeFirst();
        double cos = Math.cos(v.doubleValue());
        stack.addFirst(new BigDecimal(cos));
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 1;
    }
}
