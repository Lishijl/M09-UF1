package iticbcn.xifratge;

// imports principals de la classe actual
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

// imports pel xifratge amb clau RSA
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;

public class ClauPublica {
    // genera parell de claus RSA, la publica per encriptar i la privada per desencriptar
    public KeyPair generaParellClausRSA() throws Exception {
        // obté una instància d'un generador de claus tipus RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        // tamany de la clau de 2048 bits
        keyGen.initialize(2048);
        // retorna directament la parella de claus generades amb el mètode generateKeyPair() de KeyPairGenerator
        return keyGen.generateKeyPair();
    }
    // els paràmetres de les claus rebudes son a partir dels getters de la parella de claus que hem retornat en el bloc anterior
    // xifratge del msg amb clau publica
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        // instància amb xifratge d'un mode i tamany que acabarà d'omplir de tipus RSA
        Cipher cipher = Cipher.getInstance("RSA");
        // arguments de xifratge mode i la clau publica per xifrar
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        // passem el msg String a array de byte amb el charset UTF-8 estandaritzat
        byte[] msgB = msg.getBytes(StandardCharsets.UTF_8);
        // retornem el missatge xifrat executant el mètode doFinal()
        return cipher.doFinal(msgB);
    }
    // desxifratge amb clau privada
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        // ara en mode desencriptar i amb clau privada
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        // retornem l'array de byte desxifrat del array de byte de msgXifrat, embolicat a un now String() 
        // amb l'extra del standard charset UTF-8 per assegurar-nos la reconversió entre array de byte a String
        return new String(cipher.doFinal(msgXifrat), StandardCharsets.UTF_8);
    }
}