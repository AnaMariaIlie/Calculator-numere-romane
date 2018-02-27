package homework.interfaces.operators.binaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.NumberOperand;
import homework.interfaces.operators.Operator;

/**
 * Clasa corespunzatoare operatorului binar "^" - ridicare la putere.
 * @author Ilie Ana-Maria
 *
 */
public final class PowOperator extends Operator
        implements IBinaryOperator<Number> {

    private final int priority = 2;

    public PowOperator() {
        this.setPriority(priority);
        this.setSymbol("^");
    }

    @Override
    public IOperand<Number> calculate(final Number leftOperand,
            final Number rightOperand) {
        return new NumberOperand(null, (double) Math.pow((double)
            leftOperand, (double) rightOperand));
    }
}

