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
public class ClearCommand extends BaseCommand {

    public ClearCommand(){
        super("CLEAR");
    }

    @Override
    public String execute(LinkedList<BigDecimal> stack) throws IllegalStateException {
        stack.clear();
        return null;
    }

    @Override
    public int minimumStackDepth() {
        return 0;
    }
}
