/* Programa que a partir d'una paraula, l'encriptarà amb el xifrat Cèsar,
 * que substitueix una lletra per un altra que està a 13 posicions més a 
 * la dreta, alfabèticament i en arribar al final comença per l'inici.
 * 
 * El programa contindrà dues funcions, una per encriptar i l'altre per 
 * desencriptar.
 * 
 * En que el main farà les proves.
 */
public class Rot13 {

        public static final char[] lowerChar = { 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'à', 'á', 'è', 'é', 'ï', 'ì', 'í', 'ò', 'ó', 'ü', 'ù', 'ú' };
        public static final char[] upperChar = { 'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'À', 'Á', 'È', 'É', 'Ï', 'Ì', 'Í', 'Ò', 'Ó', 'Ü', 'Ù', 'Ú' };
    
    public static void main(String[] args) {

        String[] tests = { "abc", "xyz", "a", "z", "años", "úaloH", "aloH pinÜino, adióS. :D!" }; // 7 cadenes

        // longitud d'array
        for (int i = 0; i < tests.length; i++) {

            String xifrat = xifraRot13(tests[i]);
            String desxifrat = desxifratRot13(xifrat);

            System.out.println("Original: " + tests[i] + " -> " + "Encriptat: " + xifrat);
            System.out.println("Xifrat: " + xifrat + " -> " + "Desxifrat: " + desxifrat);

        }
    }

    public static String xifraRot13(String paraula) {

        // final d'encriptació acumulat
        String result = "";
        char letterToCompare;
        int actualPos = 0;
        int resultPos = 0;
        int finalPos = 0;

        if (!paraula.isBlank()) {

            // longitud de String
            for (int j = 0; j < paraula.length(); j++) {

                // treballem per iteració, caracter per caràcter
                char letter = paraula.charAt(j);

                // deduim si es lletra per decidir si usar l'array Majúscula o Minúscula
                if (Character.isLetter(letter)) {
                    // abc
                    if (Character.isLowerCase(letter)) {

                        // recorrem l'array Minúscula
                        for (int k = 0; k < lowerChar.length; k++) {

                            // itinerant l'array desde el primer
                            letterToCompare = lowerChar[k];

                            // en cas de que coincideixi, treballarem la lletra
                            if (letter == letterToCompare) {

                                actualPos = k;
                                // desplacem desde la posició original
                                resultPos = actualPos + 13;

                                // si supera o no la cadena abc
                                if (resultPos > lowerChar.length - 1) {
                                    // aquí s'aplicaria mòdul per deduir la posicio final
                                    // per exemple l'últim char d'índex 39, si s'aplica el mòdul a 40, 
                                    // al caràcter 39 + 1, dona l'índex 0 com a resultant correcte
                                    finalPos = resultPos % lowerChar.length; // % 40

                                    // podem concatenar la lletra encriptada al resultat
                                    result = result + lowerChar[finalPos];
                                } else {
                                    // càlcul normal, en cas de que la posició resultant, de la posició actual + 13, 
                                    // sigui igual o menor que el nombre de lletres de l'abecedari
                                    result = result + lowerChar[resultPos];
                                }
                                
                                // trenca el procés de seguir comparant si troba coincidencia i ja encriptada
                                break;
                            }
                            if (k == lowerChar.length - 1) {
                                // si no ha trencat previament per no coincidir i continua fins arribar a l'últim caràcter de abc, guarda la lletra Minúscula del que sigui sense encriptar
                                result = result + letter;
                            }
                        }
                    // ABC
                    } else {
                        for (int l = 0; l < upperChar.length; l++) {
                            letterToCompare = upperChar[l];
                            if (letter == letterToCompare) {
                                actualPos = l;
                                resultPos = actualPos + 13;
                                if (resultPos > upperChar.length - 1) {
                                    finalPos = resultPos % upperChar.length;
                                    result = result + upperChar[finalPos];
                                } else {
                                    result = result + upperChar[resultPos];
                                }
                                break;
                            }
                            if (l == upperChar.length - 1) {
                                result = result + letter;
                            }
                        }
                    }
                    // en cas de LLETRA que no hagi coincidit amb cap dels arrays MAYUS/Minus, 
                    // hauria d'imprimir el que sigui que hi ha, segons el cas prèviament a finalitzar quan es tracta de lletra.
                
                } else {
                    // si tampoc es lletra, però espais, signes de puntuació, etc, es queda igual, conservant-ho a result.
                    result = result + letter;
                }
                // torna a començar la mateixa iteració per el següent caràcter de la paraula.
            }
            // retorna la paraula tractada
            return result;
        }
        // no retorna res perquè no hi ha paraula
        return result;
    }
    public static String desxifratRot13(String paraula) {
        String result = "";
        char letterToCompare;
        int actualPos;
        int resultPos;
        int finalPos;
        if (!paraula.isBlank()) {
            // ves comprovant per charAt(i), si cada char coincideix, i llavors restar-li 13 posicions
            for (int j = 0; j < paraula.length(); j++) {
                // caràcter original
                char letter = paraula.charAt(j);
                if (Character.isLetter(letter)) {
                    // abc
                    if (Character.isLowerCase(letter)) {
                        for (int k = 0; k < lowerChar.length; k++) {
                            // la comparativa
                            letterToCompare = lowerChar[k];
                            if (letter == letterToCompare) {
                                actualPos = k;
                                resultPos = k - 13;
                                // en cas que la posició resultant es menor que 0, resta de nou pel final
                                if (resultPos < 0) {
                                    // a -> z, com que el resultant serà negatiu, apliquem una suma directament 40 chars + ( - resultPos)
                                    finalPos = lowerChar.length + resultPos;
                                    result = result + lowerChar[finalPos];
                                } else {
                                    // acumula la lletra transformada si es troba dins la longitud inicial de l'abc
                                    result = result + lowerChar[resultPos];
                                }
                                // trobat
                                break;
                            }
                            if (k == lowerChar.length - 1) {
                                // en cas de no sigui la lletra que busquem, acumula sense tractar
                                result = result + letter;
                            }
                        }
                    // ABC
                    } else {
                        for (int l = 0; l < upperChar.length; l++) {
                            // la comparativa
                            letterToCompare = upperChar[l];
                            if (letter == letterToCompare) {
                                actualPos = l;
                                resultPos = l - 13;
                                // en cas que la posició resultant es menor que 0, resta de nou pel final
                                if (resultPos < 0) {
                                    // a -> z, com que el resultant serà negatiu, apliquem una suma directament 40 chars + ( - resultPos)
                                    finalPos = upperChar.length + resultPos;
                                    result = result + upperChar[finalPos];
                                } else {
                                    // acumula la lletra transformada si es troba dins la longitud inicial de l'abc
                                    result = result + upperChar[resultPos];
                                }
                                // trobat
                                break;
                            }
                            if (l == upperChar.length - 1) {
                                // en cas de no sigui la lletra que busquem, acumula sense tractar
                                result = result + letter;
                            }
                        }
                    }
                } else {
                    // acumula el que sigui si no es lletra igualment a la cadena resultant
                    result = result + letter;
                }
            }
            // paraula ja desencriptada al finalitzar la itineració
            return result;
        }
        return result;
    }
}