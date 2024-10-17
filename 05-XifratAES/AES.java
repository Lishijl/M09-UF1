/* Ara farem servir un sistema modern pel xifratge i el 
 * desxifratge que és l'AES, Advanced Encryption Standard.
 * Tipus simètric, que consisteix en xifrar per blocs de bits, 
 * en aquest cas de 128 bits. El seu algorisme 
 * 
 * Requerirem d'importar de llibreries i de subllibreries perquè
 * no es troben al mateix nivell tot, aportant una interfície de 
 * programació senzilla. 
 * 
 * Hi haurà dos mètodes com a mínim, un que retorna byte[] 
 * xifraAES(String msg, String password) i l'altre retorna un 
 * String desxifrat desxifraAES(byte[] bMsgXifrat, String password).
 * 
 * L'AES que farem servir és l'AES-CBC-256 i desxifra a un String a 
 * partir dels bits xifrats.
 * 
 * El resultat d'un Xifrat es a partir d'un vector Iv, d'un Hash 
 * generat a partir d'una clau i el missatge. */

public class AES {
    public static void main(String[] args) {
        String msgs[] = { "Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto" };
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.print
        }
    }
}