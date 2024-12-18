// declaració del paquet
package iticbcn.xifratge;

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
 * xifraAES(String msg, String clau) i l'altre retorna un 
 * String desxifrat desxifraAES(byte[] bMsgXifrat, String clau).
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;             // a on es generarà el hash sha-256

public class XifradorAES implements Xifrador {
    // variables o definicions globals a nivell classe
    // Constants, tipus xifrat, tipus hash, tipus format
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
        
    // constant mida IV i de clau; array de byte iv de tamany 16; constant de clau
    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    // la clau té una longitud variable, el que potser després el hash té 
    // que tenir el mateix tamany que l'IV
    //private static final String CLAU = "Q9_?º<m1";

    // retorna el TextXifrat que conté l'array de bytes a partir d'una clau i un missatge,
    // mètode que pot generar una excepció tipus ClauNoSuportada llançable
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
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
            genH256B.update(clau.getBytes(StandardCharsets.UTF_8));
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
            System.arraycopy(iv, 0, ivPXResult, 0, MIDA_IV);        // l'iv afegit després l'extraurem pel desencriptat
            System.arraycopy(partXif, 0, ivPXResult, MIDA_IV, partXif.length);

            // return iv+msgxifrat, en matriu de bytes
            return new TextXifrat(ivPXResult);
        } catch (Exception e) { 
            throw new ClauNoSuportada("Error de xifrat amb AES: " + e.getMessage());
            // per acabar l'execució del programa en cas de que dongui error mostrant l'error prèviament
            // System.exit(1);
        }
    }

    // retorna desxifrat en String el missatge xifrat a partir del TextXifrat 
    // que conté l'array de bytes del missatge xifrat amb iv i missatge, 
    // i la clau, el mètode pot generar una excepció tipus ClauNoSuportada que serà llançable
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // extreiem amb el getter del TextXifrat l'array de bytes del missatge xifrat
            byte[] bIvIMsgXifrat = xifrat.getBytes();

            // Extreure l'IV. S'obté a partir de l'array de bytes del missatge 
            // Xifrat resultant que rep pel paràmetre, generem una nova variable iv, perquè 
            // la global ha quedat inicialitzada amb el SecureRandom.nextBytes().
            byte[] iv = new byte[MIDA_IV];  // l'inicialitzem amb 16 espais reservats
            // copiem només 16 elements desde la posició 0 de l'array de bytes rebut pel paràmetre
            // i el copiem a l'iv desde l'index 0
            System.arraycopy(bIvIMsgXifrat, 0, iv, 0, iv.length);
            IvParameterSpec ivParamS = new IvParameterSpec(iv);
            
            // Extreure la part xifrada.
            byte[] partXif = new byte[bIvIMsgXifrat.length - MIDA_IV];
            // ara enlloc de agafar de l'array desde l'index 0, l'agafem desde la posicio 16
            // perque a partir d'aquí es troba el missatge encriptat i no previ perque és l'iv
            System.arraycopy(bIvIMsgXifrat, MIDA_IV, partXif, 0, partXif.length);

            // Fer hash de la clau
            byte[] hashPass16B = new byte[MIDA_IV];
            MessageDigest genH256B = MessageDigest.getInstance(ALGORISME_HASH);
            genH256B.update(clau.getBytes("UTF-8"));
            System.arraycopy(genH256B.digest(), 0, hashPass16B, 0, hashPass16B.length);
            SecretKeySpec secretKS = new SecretKeySpec(hashPass16B, ALGORISME_XIFRAT);
    
            // Desxifrar.
            Cipher cipherDes = Cipher.getInstance(FORMAT_AES);
            cipherDes.init(Cipher.DECRYPT_MODE, secretKS, ivParamS);
            byte[] desxifrat = cipherDes.doFinal(partXif);
    
            // return String desxifrat
            return new String(desxifrat);
        } catch (Exception e) { 
            throw new ClauNoSuportada("Error de desxifrat amb AES: " + e.getMessage());
        }
    }
}