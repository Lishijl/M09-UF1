// declaracio de paquet
package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

/* Ara el programa enlloc de moure 13 posicions, pots introduïr un paràmetre 
 * extra per a una posició diferent, ficarem 4 posicions possibles pel testing
 * i a més de contenir els dos mètodes xifraRotX i desxifraRotX, hi haurà
 * un tercer que farà el mateix que desxifraRotX però forcaBrutaRotX ho farà
 * per força bruta, en que mira i mostra per totes les posicions possibles, en
 * que les possibilitats són la longitud de l'abecedari. El main contindrà totes
 * les proves.
 * 
 * DAM2B: Lishi JL */

public class XifradorRotX implements Xifrador {
    public static final char[] LOWERCHARS = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // comprovem que la clau sigui convertible a numèric sencer, sinó ho captura l'excepció amb el catch
            int position = Integer.parseInt(clau);
            // comprovem que la clau estigui entre els valors vàlids 0-40 inclosos, sinó també llença la mateixa excepció personalitzada ClauNoSuportada
            if (position < 0 || position > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            // encriptem el missatge amb el xifraRotX()
            String msgXifrat = xifraRotX(msg, position);
            // retorna una nova instància de TextXifrat que conté l'array de bytes del text xifrat obtingut desde getBytes amb StandardCharsets.UTF_8
            return new TextXifrat(msgXifrat.getBytes(StandardCharsets.UTF_8));

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // comprovem que la clau sigui convertible a numèric sencer, sinó ho captura l'excepció amb el catch
            int position = Integer.parseInt(clau);
            // comprovem que la clau estigui entre els valors vàlids 0-40 inclosos, sinó també llença la mateixa excepció personalitzada ClauNoSuportada
            if (position < 0 || position > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            // retorno el resultat de desxifrar el xifrat obtingut del TextXifrat amb el getBytes(), de passar-li l'String
            // que ha convertit l'array de bytes en new String() amb StandardCharsets.UTF_8 i que position representa la clau convertit en enter
            return desxifraRotX(new String(xifrat.getBytes(), StandardCharsets.UTF_8), position);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX(String paraula, int position) {
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
    public String desxifraRotX(String xifrat, int position) {
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
    public String forcaBrutaRotX(String xifrat) {
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
    /*
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
    */
}