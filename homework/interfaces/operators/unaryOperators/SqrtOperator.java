package homework.interfaces.operators.unaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.NumberOperand;
import homework.interfaces.operators.Operator;

/**
 * Clasa corespunzatoare operatorului unar "sqrt" - radical.
 * @author Ilie Ana-Maria
 *
 */
public final class SqrtOperator extends Operator
        implements IUnaryOperator<Number> {

    private final int priority = 3;

    public SqrtOperator() {
        this.setPriority(priority);
        this.setSymbol("sqrt");
    }

    @Override
    public IOperand<Number> calculate(final Number operand) {
        return new NumberOperand(null,  Math.sqrt((double) operand));
    }

}
