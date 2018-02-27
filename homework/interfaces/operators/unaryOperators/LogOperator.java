package homework.interfaces.operators.unaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.NumberOperand;
import homework.interfaces.operators.Operator;

/**
 * Clasa corespunzatoare operatorului unar "log" - logaritm.
 * @author Ilie Ana-Maria
 *
 */
public final class LogOperator extends Operator
        implements IUnaryOperator<Number> {

    private final int priority = 3;

    public LogOperator() {
        this.setPriority(priority);
        this.setSymbol("log");
    }

    @Override
    public IOperand<Number> calculate(final Number operand) {
        return new NumberOperand(null, Math.log((double) operand));
    }



}
