/* Ara el programa enlloc de moure 13 posicions, pots introduir un paràmetre 
 * extra per a una posició diferent, ficarem 4 posicions possibles pel testing
 * i a més de contenir els dos mètodes xifraRotX i desxifratRotX, hi haurà
 * un tercer que farà el mateix que desxifraRotX però forcaBrutaRotX ho farà
 * per força bruta, en que mira i mostra per totes les posicions possibles, en
 * que les possibilitats són la longitud de l'abecedari. El main contindrà totes
 * les proves */

public class RotX {
    public static final char[] LOWERCHARS = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        String[] tests = { "abc", "xyz", "a", "z", "Medio", "aloH pinÜino, adióS. :D!" };
        int[] positions = { 2, 6, 3, 10 };

        for (String test : tests) {
            System.out.println("Original: " +  + " -> Encriptat amb " +  + " posicions: " + );
            System.out.println("Xifrat: " +  + " -> Desxifrat també amb " +  + " posicions: " + );
            System.out.println("Ara amb força bruta: " + forcaBrutaRotX(/* passem el resultat de xifra(s,x) */));
        }
    }
    public static String xifraRotX(String paraula, int postion) {

    }
    public static String desxifraRotX(String xifrat, int position) {

    }
    public static String forcaBrutaRotX(String xifrat) {
        // contindrà el resultat sencer a retornar
        StringBuffer result = new StringBuffer();
        // all positions
        for (int i = 0; i < LOWERCHARS.length; i++) {
            result.append(desxifraRotX(xifrat, i));
        }
        return result;
    }
}