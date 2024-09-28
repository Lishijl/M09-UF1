/* Ara el programa enlloc de moure 13 posicions, pots introduïr un paràmetre 
 * extra per a una posició diferent, ficarem 4 posicions possibles pel testing
 * i a més de contenir els dos mètodes xifraRotX i desxifraRotX, hi haurà
 * un tercer que farà el mateix que desxifraRotX però forcaBrutaRotX ho farà
 * per força bruta, en que mira i mostra per totes les posicions possibles, en
 * que les possibilitats són la longitud de l'abecedari. El main contindrà totes
 * les proves.
 * 
 * DAM2B: Lishi JL */

public class RotX {
    public static final char[] LOWERCHARS = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    public static String xifraRotX(String paraula, int position) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < paraula.length(); i++) {
            char originalCh = paraula.charAt(i);
            int posMin = trobaPosicio(LOWERCHARS, originalCh);
            int posMay = trobaPosicio(UPPERCHARS, originalCh);
            int finalPos;
            if (posMin > -1) {
                finalPos = (posMin + position) % LOWERCHARS.length;
                result.append(LOWERCHARS[finalPos]);
            } else if (posMay > -1) {
                finalPos = (posMay + position) % UPPERCHARS.length;
                result.append(UPPERCHARS[finalPos]);
            } else {
                result.append(originalCh);
            }
        }
        return result.toString();
    }
    public static String desxifraRotX(String xifrat, int position) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < xifrat.length(); i++) {
            char originalCh = xifrat.charAt(i);
            int posMin = trobaPosicio(LOWERCHARS, originalCh);
            int posMay = trobaPosicio(UPPERCHARS, originalCh);
            int resultPos;
            int finalPos;
            if (posMin > -1) {
                resultPos = (posMin - position) % LOWERCHARS.length;
                if (resultPos < 0) {
                    finalPos = LOWERCHARS.length + resultPos;
                    result.append(LOWERCHARS[finalPos]);
                } else {
                    result.append(LOWERCHARS[resultPos]);
                }
            } else if (posMay > -1) {
                resultPos = (posMay - position) % UPPERCHARS.length;
                if (resultPos < 0) {
                    finalPos = UPPERCHARS.length + resultPos;
                    result.append(UPPERCHARS[finalPos]);
                } else {
                    result.append(UPPERCHARS[resultPos]);
                }
            } else {
                result.append(originalCh);
            }
        }
        return result.toString();
    }
    public static String forcaBrutaRotX(String xifrat) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i <= LOWERCHARS.length; i++) {
            result.append("Posició - " + i + ":      " + desxifraRotX(xifrat, i) + "\n");
        }
        return result.toString();
    }
    public static int trobaPosicio(char[] array, char character) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == character) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String[] tests = { "abc", "xyz", "Hola pinÜino, adióS. :D!" };
        int[] positions = { 2, 6, 10 };

        for (String test : tests) {
            for (int position : positions) {
                String xifrat = xifraRotX(test, position);
                String desxifrat = desxifraRotX(xifrat, position);
                System.out.printf("%-19s%s%12s%24s%d%-20s%s%n","Original:", test, "->", "Encriptat amb ", position, " posicions:", xifrat);
                System.out.printf("%-19s%s%12s%24s%d%-20s%s%n%n","Xifrat:", xifrat, "->", "Desxifrat amb ", position, " posicions:", desxifrat);
                System.out.printf("%-19s%s%n%s%n%n%s%n","Ara desxifrem:", xifrat, "Amb força bruta:",forcaBrutaRotX(xifrat));
            }
        }
    }
}