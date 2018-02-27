package homework.interfaces.operands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clasa care implementeaza interfata IOperandsFactory.
 * Fabrica de operanzi cu metodele pentru convertirea lor: din numere romane in
 * numere arabe si invers.
 * @author Ilie Ana-Maria
 *
 */
public final class OperandsFactory implements IOperandsFactory<Number> {

    private final int m = 1000;
    private final int cm = 900;
    private final int d = 500;
    private final int cd = 400;
    private final int c = 100;
    private final int xc = 90;
    private final int l = 50;
    private final int xl = 40;
    private final int x = 10;
    private final int ix = 9;
    private final int v = 5;
    private final int iv = 4;
    private final int i = 1;

    @SuppressWarnings("serial")
    private final TreeMap<Integer, String> arabicMap
        = new TreeMap<Integer, String>(Collections.reverseOrder()) {{
        put(m, "M");
        put(cm, "CM");
        put(d, "D");
        put(cd, "CD");
        put(c, "C");
        put(xc, "XC");
        put(l, "L");
        put(xl, "XL");
        put(x, "X");
        put(ix, "IX");
        put(v, "V");
        put(iv, "IV");
        put(i, "I"); }};

    @SuppressWarnings("serial")
    private final Map<Character, Integer> romanMap
        = new HashMap<Character, Integer>(7) {{
        put('I', i);
        put('V', v);
        put('X', x);
        put('L', l);
        put('C', c);
        put('D', d);
        put('M', m); }};

    private static OperandsFactory operandsFactory = new OperandsFactory();

    private OperandsFactory() {
    }

    public static OperandsFactory getInstance() {
        return operandsFactory;
    }

    @Override
    public IOperand<Number> createOperand(final String token) {
        return new NumberOperand();
    }

    @Override
    public IOperand<Number> convertToRomanNumber(final Number arabNumber) {

        Number arab = arabNumber;
        NumberOperand romanNumber = (NumberOperand) operandsFactory.
                                    createOperand("");
        String roman = "";

        for (Integer it: arabicMap.keySet()) {
            for (int j = 1; j <= (double) arab / it; j++) {

                roman += arabicMap.get(it);
            }
            arab = (double) arab % it;
        }

        romanNumber.setSymbolValue(arabNumber);
        romanNumber.setSymbol(roman);

        return romanNumber;
    }

    @Override
    public IOperand<Number> convertToArabNumber(final String romanNumber) {

        String roman = romanNumber;
        NumberOperand arabicNumber = (NumberOperand) operandsFactory.
                                     createOperand("");
        double sum = 0;
        double len = roman.length() - 1;

        for (int it = 0; it < len; it++) {
            if (romanMap.get(roman.charAt(it))
                < romanMap.get(roman.charAt(it + 1))) {

                sum -= romanMap.get(roman.charAt(it));
            } else {

                sum += romanMap.get(roman.charAt(it));
            }
        }

        sum += (double) romanMap.get(roman.charAt((int) len));
        arabicNumber.setSymbol(roman);
        arabicNumber.setSymbolValue(sum);

        return arabicNumber;
    }

}
