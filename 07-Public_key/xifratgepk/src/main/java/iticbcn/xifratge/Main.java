package iticbcn.xifratge;

/* Classe Main que executarà tota la lógica del programa */
import java.security.KeyPair; 
// la llibreria importada ja està implementada en el pom.xml
import javax.xml.bind.DatatypeConverter; 

public class Main { 
    public static void main(String args[]) throws Exception { 
        // instància de la classe ClauPublica
        ClauPublica cp = new ClauPublica(); 
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü"; 
        // obtenció de la parella de claus rsa
        KeyPair parellClaus = cp.generaParellClausRSA(); 
        // array de bytes del msg xifrat
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic()); 
        System.out.println("================================="); 
        System.out.print("Text xifrat: "); 
        // converteix el text xifrat d'array de bytes en String que representa hexadecimalment el missatge xifrat
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat)); 
        // text desxifrat a partir de l'array de bytes del msg xifrat
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate()); 
        System.out.println("================================="); 
        System.out.println("Text desxifrat: " + msgDesxifrat); 
        // converteix la clau publica en un String en base 64 o hexadecimal
        String strClauPub = DatatypeConverter.printHexBinary( 
        parellClaus.getPublic().getEncoded()); 
        // converteix la clau privada en un String en base 64 o hexadecimal
        String strClauPriv = DatatypeConverter.printHexBinary( 
        parellClaus.getPrivate().getEncoded()); 
        System.out.println("================================="); 
        System.out.println("Clau pública: " + strClauPub); 
        System.out.println("================================="); 
        System.out.println("Clau privada: " + strClauPriv); 
    } 
}