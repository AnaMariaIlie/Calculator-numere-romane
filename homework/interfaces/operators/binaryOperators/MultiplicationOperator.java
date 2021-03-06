package homework.interfaces.operators.binaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.NumberOperand;
import homework.interfaces.operators.Operator;

/**
 * Clasa corespunzatoare operatorului binar "*" - inmultire.
 * @author Ilie Ana-Maria
 *
 */
public final class MultiplicationOperator extends Operator
        implements IBinaryOperator<Number> {

    private final int priority = 1;

    public MultiplicationOperator() {
        this.setPriority(priority);
        this.setSymbol("*");
    }

    @Override
    public IOperand<Number> calculate(final Number leftOperand,
                final Number rightOperand) {
        return new NumberOperand(null, (double)
            ((double) leftOperand * (double) rightOperand));
    }


}
