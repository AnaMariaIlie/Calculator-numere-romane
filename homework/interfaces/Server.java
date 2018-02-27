package homework.interfaces;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import homework.interfaces.brackets.IBracket;
import homework.interfaces.brackets.BracketsFactory;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.OperandsFactory;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.Operator;
import homework.interfaces.operators.OperatorsFactory;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;

/**
 * Clasa care implementeaza interfata IServer.
 * @author Ilie Ana-Maria
 *
 */
public final class Server implements IServer {

    private List<String> possibleOperators = new ArrayList<String>();
    private List<String> results = new ArrayList<String>();
    /**
     * Stiva de operatori, implementata ca ArrayDeque
     * pentru a face operatiile mai usor.
     */
    private Deque<IToken> operatorsStack = new ArrayDeque<IToken>();
    /**
     * Stiva de operanzi.
     */
    private Deque<IToken> operandsStack = new ArrayDeque<IToken>();
    /**
     * Instanta de tip Singleton.
     */
    private static Server server = null;

    private Server() {
    }

    public static Server getInstance() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    @Override
    public boolean canPublish(final String[] tokens) {

        for (String token : tokens) {

            Token iToken = new Token(token);
            if (OperatorsFactory.getInstance().isOperator(iToken)
                && !this.getPossibleOperators().contains(token)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void publish(final String command) {

        String[] tokens = command.split(" ");
        BracketsFactory bracketsFactory = BracketsFactory.getInstance();
        OperatorsFactory operatorsFactory = OperatorsFactory.getInstance();
        OperandsFactory operandsFactory = OperandsFactory.getInstance();
        Deque<IToken> intermediarResultsStack = new ArrayDeque<IToken>();
        ArrayList<IToken> reversedOperandsStack = new ArrayList<IToken>();

        this.getOperandsStack().removeAll(this.getOperandsStack());
        this.getOperatorsStack().removeAll(this.getOperatorsStack());

        for (String token : tokens) {

            Token iToken = new Token(token);
            if (operatorsFactory.isOperator(iToken)) {

                if (!this.canPublish(tokens)) {
                    this.getResults().add("IMPOSSIBRU");
                    return;
                } else {

                IOperator operator = operatorsFactory.createOperator(token);
                if (this.getOperatorsStack().peek() != null && operatorsFactory
                    .isOperator(new Token(this.getOperatorsStack().peek().
                        getSymbol()))) {

                    while (!this.getOperatorsStack().isEmpty()
                        && operatorsFactory.isOperator(new Token(
                        this.getOperatorsStack().peek().getSymbol()))
                        && operator.getPriority() <= ((Operator)
                        this.getOperatorsStack().peek()).getPriority()) {

                        this.getOperandsStack().push(this.
                        getOperatorsStack().pop());

                    }
                }

                this.getOperatorsStack().push(operator);
                }
            } else {

                IBracket bracket = bracketsFactory.createBracket(token);
                if (bracketsFactory.isBracket(bracket)) {

                    if (bracketsFactory.isOpenedBracket(bracket)) {

                        this.getOperatorsStack().push(bracket);
                    } else {

                        while (!bracketsFactory.isBracketPair(bracket,
                                bracketsFactory.createBracket(this.
                                getOperatorsStack().peek().getSymbol()))) {

                            this.getOperandsStack()
                                .push(this.getOperatorsStack().removeFirst());

                        }
                        this.getOperatorsStack().pop();
                    }
                } else {

                    IOperand<Number> operand = operandsFactory
                            .convertToArabNumber(token);
                    this.getOperandsStack().push(operand);

                }
            }
        }

        while (!this.getOperatorsStack().isEmpty()) {
            this.getOperandsStack().push(this.getOperatorsStack().pop());
        }


        for (IToken element : this.getOperandsStack()) {
            reversedOperandsStack.add(element);
        }

        Collections.reverse(reversedOperandsStack);

        for (IToken element : reversedOperandsStack) {

            Token iToken =  new Token(element.getSymbol());
            if (!operatorsFactory.isOperator(iToken)) {

                intermediarResultsStack.push(element);
            } else {

                if (operatorsFactory.isBinaryOperator(new Operator(element.
                    getSymbol()))) {

                    Number rightOperand = ((IOperand<Number>)
                        intermediarResultsStack.pop()).getSymbolValue();
                    Number leftOperand = ((IOperand<Number>)
                        intermediarResultsStack.pop()).getSymbolValue();

                    if (element.getSymbol().equals("/")
                            && (double) rightOperand == 0) {

                        results.add("IMPOSSIBRU");
                        return;

                    } else {

                        intermediarResultsStack.push(((IBinaryOperator<Number>)
                            element).calculate(leftOperand, rightOperand));
                    }
                } else {

                    Number operand = ((IOperand<Number>) intermediarResultsStack
                        .pop()).getSymbolValue();
                    intermediarResultsStack.push(((IUnaryOperator<Number>)
                        element).calculate(operand));
                }
            }
        }

        if ((double) ((IOperand<Number>) intermediarResultsStack.peek())
                .getSymbolValue() < 0) {

            double positive =  Math.floor((double) ((IOperand<Number>)
                intermediarResultsStack.pop()).getSymbolValue()) * (-1);
            this.getResults().add("- "
                + operandsFactory.convertToRomanNumber(positive).getSymbol());

        } else {

            this.getResults().add(operandsFactory.convertToRomanNumber(
                    ((IOperand<Number>) intermediarResultsStack.pop())
                    .getSymbolValue()).getSymbol());
        }


    }

    @Override
    public void subscribe(final String oper) {
        this.getPossibleOperators().add(oper);
    }

    @Override
    public List<String> getResults() {
        return results;
    }

    public Deque<IToken> getOperatorsStack() {
        return operatorsStack;
    }

    public void setOperatorsStack(final Deque<IToken> optsStack) {
        this.operatorsStack = optsStack;
    }

    public Deque<IToken> getOperandsStack() {
        return operandsStack;
    }

    public void setOperandsStack(final Deque<IToken> opdsStack) {
        this.operandsStack = opdsStack;
    }

    public List<String> getPossibleOperators() {
        return possibleOperators;
    }

    public void setPossibleOperators(final List<String> possOperators) {
        this.possibleOperators = possOperators;
    }

}
