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
    // variables o definicions globals a nivell classe
    // Constants, tipus xifrat, tipus hash, tipus format
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
        
    // constant mida IV, array de byte iv de tamany 16 i constant de clau
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    // retorna el xifrat en array de bytes a partir d'una clau i un missatge,
    // mètode que pot generar una excepció, llançable de tipus general
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l’String

        // Genera IvParameterSpec

        // Genera hash

        // Encrypt.

        // Combinar IV i part xifrada.

        // return iv+msgxifrat
        return null;
    }

    // retorna el desxifrat en String a partir del array de bytes xifrat amb
    // iv i missatge, i la clau, el mètode pot generar una excepció general
    // que serà llançable
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV.

        // Extreure la part xifrada.

        // Fer hash de la clau

        // Desxifrar.

        // return String desxifrat
        return "";
    }

    // main que executa la lógica del programa
    public static void main(String[] args) {
        // proves
        String msgs[] = { "Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto" };
        // iteració per cada prova
        for (int i = 0; i < msgs.length; i++) {
            // obtenció d'1 text per provar
            String msg = msgs[i];
            
            // array de bytes que s¡obtindrà del xifrat
            byte[] bXifrats = null;
            // resultat de tornar del desxifrat
            String desxifrat = "";

            // gestionem possibles excepcions
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                // mostrem i imprimim l'error capturat, i obtenim la localització del missatge
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            // mostrem la sortida, el missatge original, l'encriptat de tornar l'array de bytes 
            // en una nova instància de tipus String amb el seu builder i també el missatge desxifrat
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println(desxifrat);
        }
    }
}