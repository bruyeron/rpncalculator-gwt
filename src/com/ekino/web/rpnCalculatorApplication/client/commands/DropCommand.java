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
public class DropCommand extends BaseCommand {

    public DropCommand() {
        super("DROP");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        checkStackState(stack);
        stack.removeFirst();
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 1;
    }
}
