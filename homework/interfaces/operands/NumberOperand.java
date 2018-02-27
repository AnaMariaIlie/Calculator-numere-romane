package homework.interfaces.operands;

/**
 * Clasa care implementeaza interfata Ioperand.
 * Reprezinta un numar, care contine detine o reprezentare si o valoare.
 * @author Ilie Ana-Maria
 *
 */
public final class NumberOperand implements IOperand<Number> {

    private String symbol;
    private double value;

    public NumberOperand(final String string, final double number) {
        super();
        this.symbol = string;
        this.value = number;
    }

    public NumberOperand() {

    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void setSymbol(final String string) {
        this.symbol = string;
    }

    @Override
    public Number getSymbolValue() {
        return this.value;
    }

    @Override
    public void setSymbolValue(final Number number) {
        this.value =  (double) number;
    }

}
