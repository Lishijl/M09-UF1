/* Creem una interficie Xifrador que serà implementat per totes les classes que
 * es troben dins del package iticbcn.xifratge que es troba dins del recurs "src"
 * de la carpeta del projecte. Aquesta interficie implementa dos mètodes xifra() i
 * desxifra(). Que poden generar i tirar excepció de tipus ClauNoSuportada. */

public interface Xifrador {
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
    public String desxifra(Textxifrat xifrat, String clau) throws ClauNoSuportada;
}