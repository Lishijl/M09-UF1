package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;


// longitud de clau 2048 bits
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
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        return null;
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        return null;
    }
}