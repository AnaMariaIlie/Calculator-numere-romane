package homework.interfaces;

/**
 * Clasa care implementeaza interfata IToken.
 * @author Ilie Ana-Maria
 *
 */
public final class Token implements IToken {

    private String symbol;

    public Token(final String string) {
        super();
        this.symbol = string;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void setSymbol(final String string) {
        this.symbol = string;
    }

}
