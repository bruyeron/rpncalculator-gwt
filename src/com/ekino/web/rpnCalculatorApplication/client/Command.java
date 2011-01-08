/**
 * Created with IntelliJ IDEA.
 */
package com.ekino.web.rpnCalculatorApplication.client;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author Renaud Bruyeron
 * @version $Id$
 */
public interface Command {

    String execute(LinkedList<BigDecimal> stack) throws IllegalStateException;

    String getLabel();

    int minimumStackDepth();
}
