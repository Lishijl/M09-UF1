package iticbcn.xifratge;

/* Classe Main que executarà tota la lógica del programa */
import java.security.KeyPair; 
// la llibreria importada ja està implementada en el pom.xml
import javax.xml.bind.DatatypeConverter; 

public class Main { 
    public static void main(String args[]) throws Exception { 
        ClauPublica cp = new ClauPublica(); 
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü"; 
        KeyPair parellClaus = cp.generaParellClausRSA(); 
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic()); 
        System.out.println("================================="); 
        System.out.print("Text xifrat: "); 
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat)); 
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate()); 
        System.out.println("================================="); 
        System.out.println("Text desxifrat: " + msgDesxifrat); 
        String strClauPub = DatatypeConverter.printHexBinary( 
        parellClaus.getPublic().getEncoded()); 
        String strClauPriv = DatatypeConverter.printHexBinary( 
        parellClaus.getPrivate().getEncoded()); 
        System.out.println("================================="); 
        System.out.println("Clau pública: " + strClauPub); 
        System.out.println("================================="); 
        System.out.println("Clau privada: " + strClauPriv); 
    } 
}