package homework.interfaces.brackets;

/**
 * Clasa care implementeaza interfata IBracket corespunzatoare unei paranteze.
 * @author Ilie Ana-Maria
 *
 */
public final class Bracket implements IBracket {

    private String symbol;

    public Bracket(final String string) {
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
