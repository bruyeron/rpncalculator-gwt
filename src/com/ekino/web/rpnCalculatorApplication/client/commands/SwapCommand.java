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
public class SwapCommand extends BaseCommand {

    public SwapCommand() {
        super("SWAP");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        BigDecimal v1 = stack.removeFirst();
        BigDecimal v2 = stack.removeFirst();
        stack.addFirst(v1);
        stack.addFirst(v2);
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 2;
    }
}
