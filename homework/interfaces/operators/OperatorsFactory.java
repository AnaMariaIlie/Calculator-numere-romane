package homework.interfaces.operators;

/**
 * Clasa care implementeaza interfata IOperatorsFactory.
 * Fabrica de operatori.
 * @author Ilie Ana-Maria
 *
 */
import homework.interfaces.IToken;
import homework.interfaces.operators.unaryOperators.LogOperator;
import homework.interfaces.operators.unaryOperators.SqrtOperator;
import homework.interfaces.operators.binaryOperators.PlusOperator;
import homework.interfaces.operators.binaryOperators.MinusOperator;
import homework.interfaces.operators.binaryOperators.PowOperator;
import homework.interfaces.operators.binaryOperators.DivideOperator;
import homework.interfaces.operators.binaryOperators.MultiplicationOperator;

public final class OperatorsFactory implements IOperatorsFactory {

    private static OperatorsFactory operatorsFactory = null;

    private OperatorsFactory() {
    }

    public static OperatorsFactory getInstance() {
        if (operatorsFactory == null) {
            operatorsFactory = new OperatorsFactory();
        }
        return operatorsFactory;
    }

    @Override
    public IOperator createOperator(final String token) {

        switch (token) {
        case "+":
            return new PlusOperator();
        case "-":
            return new MinusOperator();
        case "*":
            return new MultiplicationOperator();
        case "/":
            return new DivideOperator();
        case "^":
            return new PowOperator();
        case "log":
            return new LogOperator();
        case "sqrt":
            return new SqrtOperator();
        default:
        }
        return null;
    }

    @Override
    public boolean isOperator(final IToken token) {
        if (token.getSymbol().equals("+")
                || token.getSymbol().equals("-")
                || token.getSymbol().equals("*")
                || token.getSymbol().equals("/")
                || token.getSymbol().equals("^")
                || token.getSymbol().equals("log")
                || token.getSymbol().equals("sqrt")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUnaryOperator(final IOperator operator) {
        if (operator.getSymbol().equals("log")
            || operator.getSymbol().equals("sqrt")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBinaryOperator(final IOperator operator) {
        if (operator.getSymbol().equals("+")
            || operator.getSymbol().equals("-")
            || operator.getSymbol().equals("*")
            || operator.getSymbol().equals("/")
            || operator.getSymbol().equals("^")) {
            return true;
        }
        return false;
    }

}

