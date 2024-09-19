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

        for (int i = 0; i <= paraules.length; i++) {

            String xifrat = xifraRot13(paraules[i]);
            String desxifrat = desxifratRot13(xifrat);

            System.out.println("Original: " + paraules[i] + " -> " + "Encriptat: " + xifrat);
            System.out.println("Xifrat: " + xifrat + " -> " + "Desxifrat: " + desxifrat);

        }
    }

    public static String xifraRot13(String paraula) {
        String result = "";
        if (!paraula.isBlank()) {
            // ves comprovant per charAt(i), si cada char coincideix, i llavors sumar-li 13 posicions
        
        }
        return "";
    }
    public static String desxifratRot13(String paraula) {
        String result = "";
        return "";
    }
}