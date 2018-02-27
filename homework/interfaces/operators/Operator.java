package homework.interfaces.operators;

/**
 * Clasa care implementeaza interfata IOperator.
 * Clasa care va fi extinsa de catre toate clasele ce reprezinta operatori.
 * @author Ilie Ana-Maria
 *
 */
public class Operator implements IOperator {

    private int priority;
    private String symbol;

    public Operator(final String string) {
        super();
        this.symbol = string;
    }

    public Operator() {
    }

    /**
     * Returneaza simbolul operatorului.
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Seteaza simbolul operatorului.
     */
    @Override
    public void setSymbol(final String string) {
        this.symbol = string;
    }

    /**
     * Returneaza prioritatea operatorului.
     */
    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * Seteaza prioritatea unui operator.
     * @param prior
     *          prioritatea operatorului.
     */
    public void setPriority(final int prior) {
        this.priority = prior;
    }

}

