/* Ara farem servir un sistema modern pel xifratge i el 
 * desxifratge que és l'AES, Advanced Encryption Standard.
 * Tipus simètric, que consisteix en xifrar per blocs de bits, 
 * en aquest cas de 128 bits.
 * 
 * Requerirem d'importar de llibreries i de subllibreries perquè
 * no es troben al mateix nivell tot, aportant una interfície de 
 * programació senzilla. 
 * imports: javax.crypto.*  ...Cipher/spect.IvParameterSpec/spec.SecretKeySpec
 * java.security.*          ...SecureRandom/MessageDigest
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

import javax.crypto.Cipher;                     // xifrarem/desxifrarem usant el seu mode
import javax.crypto.spec.SecretKeySpec;         // per definir la clau secreta que usarem en l'algoritme AES
import javax.crypto.spec.IvParameterSpec;       // el vector Inicialitzador que ocuparà per el mode CBC

import java.security.SecureRandom;              // per fer únic i aleatori l'IV
import java.security.MessageDigest;             // a on es generarà el hash sha-256

public class AES {
    // variables o definicions globals a nivell classe
    // Constants, tipus xifrat, tipus hash, tipus format
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
        
    // constant mida IV i de clau; array de byte iv de tamany 16; constant de clau
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    // la clau té una longitud variable, el que potser després el hash té 
    // que tenir el mateix tamany que l'IV
    private static final String CLAU = "Q9_?º<m1";

    // retorna el xifrat en array de bytes a partir d'una clau i un missatge,
    // mètode que pot generar una excepció, llançable de tipus general
    public static byte[] xifraAES(String msg, String password) throws Exception {
        // Obtenir els bytes de l’String, extret amb getBytes()
        byte[] bMsg = msg.getBytes();

        // Genera i inicialitza IvParameterSpec i usarem el SecureRandom perquè 
        // generi una secuencia de bytes per inicialitzar l'array de bytes de IV 
        // de forma aleatoria
        SecureRandom rdm = new SecureRandom();
        rdm.nextBytes(iv);          // reb l'argument iv per inicialitzar-la 
        IvParameterSpec ivParamS = new IvParameterSpec(iv);     // el paràmetre que contindrà l'IV

        // Genera hash, obté instància de 256 bits del MessageDigest amb la constant 
        // ALGORISME_HASH l'actualitzem amb l'array de bytes de la clau rebuda pel 
        // paràmetre del mètode, per desprès finalment generar el hash de la clau, 
        // copiant només els 16 primers bytes del hash generat, amb el mètode 
        // arrraycopy() de System.
        MessageDigest genH256B = MessageDigest.getInstance(ALGORISME_HASH);
        // actualitzem la instància de 256 bits amb l'array de la clau
        genH256B.update(password.getBytes("UTF-8"));
        // utilitzem 16 bytes com a longitud perquè és un AES128 bits, 
        // i tant la clau com l'IV són 128 bits, per tant 16 bytes
        byte[] hashPass16B = new byte[MIDA_IV];
        // usarem l'arraycopy() per limitar els bytes de la contraseña generada a 16 bytes
        // genH256B.digest() genera el hash de 256 bits que són 32 bytes, volem copiar 
        // desde la posició 0 d'aquesta, fins un nombre de 16 elements tipus byte a l'array
        // de passB desde l'index 0
        System.arraycopy(genH256B.digest(), 0, hashPass16B, 0, hashPass16B.length);
        // inicialitzem SecretKeySpec amb el hash i el tipus d'algorisme amb ALGORISME_XIFRAT "AES"
        SecretKeySpec secretKS = new SecretKeySpec(hashPass16B, ALGORISME_XIFRAT);

        // Encrypt. Obtindrem una instàcia del FORMAT_AES del Cipher, que l'inicialit-
        // zarem amb els arguments del mode d'encriptar o desenciptar, la SecretKeySpec
        // secretKS i el paràmetreSpec de l'IV.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKS, ivParamS);
        byte[] partXif = cipher.doFinal(bMsg);

        // Combinar IV i part xifrada. Creant un nou array de byte que contindrà totes 
        // les parts del xifrat, l'IV, l'encriptació del text amb l'iv i el hash 
        // generat de la clau, per això serà un array resultant de longitud de la suma 
        // de la longitud de l'iv i la longitud de la partXifrada
        byte[] ivPXResult = new byte[MIDA_IV + partXif.length];
        // copiarem per al nou array de bytes ivPXResult tant l'iv unic i aleatori com
        // també la partXifrada, com que els index a on es copien són 0, la segona part
        // del resultat l'index comença pel 16 fins la longitud de partXifrada
        System.arraycopy(iv, 0, ivPXResult, 0, MIDA_IV);
        System.arraycopy(partXif, 0, ivPXResult, MIDA_IV, partXif.length);

        // return iv+msgxifrat, en matriu de bytes
        return ivPXResult;
    }

    // retorna el desxifrat en String a partir del array de bytes xifrat amb
    // iv i missatge, i la clau, el mètode pot generar una excepció general
    // que serà llançable
    public static String desxifraAES(byte[] bIvIMsgXifrat, String password) throws Exception {

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
            
            // array de bytes que s'obtindrà del xifrat
            byte[] bXifrats = null;
            // s'assignarà el resultat de tornar del desxifrat
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