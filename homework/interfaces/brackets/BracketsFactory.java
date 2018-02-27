package homework.interfaces.brackets;
import homework.interfaces.IToken;

/**
 * Clasa care implementeaza IBracketsFactory.
 * Fabrica de paranteaze.
 * @author Ilie Ana-Maria
 *
 */
public final class BracketsFactory implements IBracketsFactory {

    private static BracketsFactory bracketsFactory = null;

    private BracketsFactory() {
    }

    public static BracketsFactory getInstance() {
        if (bracketsFactory == null) {
            bracketsFactory = new BracketsFactory();
        }
        return bracketsFactory;
    }

    @Override
    public IBracket createBracket(final String token) {
        return new Bracket(token);
    }

    @Override
    public boolean isBracket(final IToken token) {

    if (token.getSymbol().equals("(")
            || token.getSymbol().equals(")")
            || token.getSymbol().equals("[")
            || token.getSymbol().equals("]")
            || token.getSymbol().equals("{")
            || token.getSymbol().equals("}")) {

                return true;
        }
        return false;
    }

    @Override
    public boolean isOpenedBracket(final IBracket bracket) {

        if (bracket.getSymbol().equals("(")
                || bracket.getSymbol().equals("[")
                || bracket.getSymbol().equals("{")) {

                return true;
        }
        return false;
    }

    @Override
    public boolean isClosedBracket(final IBracket bracket) {

        if (bracket.getSymbol().equals(")")
                || bracket.getSymbol().equals("]")
                || bracket.getSymbol().equals("}")) {

                return true;
        }
        return false;
    }

    @Override
    public boolean isBracketPair(final IBracket openBracket,
                    final IBracket closeBracket) {

        if ((openBracket.getSymbol().equals("(")
                && closeBracket.getSymbol().equals(")"))
                || (openBracket.getSymbol().equals("[")
                && closeBracket.getSymbol().equals("]"))
                || (openBracket.getSymbol().equals("{")
                && closeBracket.getSymbol().equals("}"))
                || (openBracket.getSymbol().equals(")")
                && closeBracket.getSymbol().equals("("))
                || (openBracket.getSymbol().equals("]")
                && closeBracket.getSymbol().equals("["))
                || (openBracket.getSymbol().equals("}")
                && closeBracket.getSymbol().equals("{"))) {

            return true;
        }
        return false;
    }

}
