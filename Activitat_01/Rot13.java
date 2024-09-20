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

        String[] paraules = { "abc", "xyz", "a", "z", "años", "úaloH" }; // 6 paraules

        // longitud d'array
        for (int i = 0; i <= paraules.length; i++) {

            String xifrat = xifraRot13(paraules[i]);
            String desxifrat = desxifratRot13(xifrat);

            System.out.println("Original: " + paraules[i] + " -> " + "Encriptat: " + xifrat);
            System.out.println("Xifrat: " + xifrat + " -> " + "Desxifrat: " + desxifrat);

        }
    }

    public static String xifraRot13(String paraula) {

        // final d'encriptació acumulat
        String result = "";

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
                            char letterToCompare = lowerChar[k];

                            // en cas de que coincideixi, treballarem la lletra
                            if (letter == letterToCompare) {

                                int actualPos = k;
                                // desplacem desde la posició original
                                int resultPos = actualPos + 13;

                                // si supera o no la cadena abc
                                if (resultPos > lowerChar.length) {
                                    // aquí s'aplicaria mòdul per deduir la posicio final
                                    // per exemple l'últim char d'índex 39, si s'aplica el mòdul a 40, 
                                    // al caràcter 39 + 1, dona l'índex 0 com a resultant correcte
                                    int finalPos = resultPos % 40;

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
                            if (k == 39) {
                                // si no ha trencat previament per no coincidir i continua fins arribar a l'últim caràcter de abc, guarda la lletra Minúscula del que sigui sense encriptar
                                result = result + letter;
                            }
                        }
                    // ABC
                    } else {
                        for (int l = 0; l < upperCharChar.length; l++) {
                            
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
        }
        // perquè funcioni de moment
        return "";
    }
    public static String desxifratRot13(String paraula) {
        String result = "";
        if (!paraula.isBlank()) {
            // ves comprovant per charAt(i), si cada char coincideix, i llavors sumar-li 13 posicions
        
        }
        return "";
    }
}