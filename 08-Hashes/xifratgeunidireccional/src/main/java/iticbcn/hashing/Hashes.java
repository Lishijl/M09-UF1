package iticbcn.hashing;

import java.util.HexFormat;

public class Hashes {

    int npass = 0;

    // dos mètodes hashing que genera un hash del password amb un salt amb dos tipus d'algorismes SHA-512 i PBKDF2
    public String getSHA512AmbSalt (String pw, String salt) {
        // a partir de l'array de bytes de la contrasenya, es genera un hash amb salt i el retorna en String Hexadecimal
        HexFormat hex = HexFormat.of(); 
        // String hash = hex.formatHex(bytes);
        return "";
    }
    public String getPBKDF2AmbSalt (String pw, String salt) { return ""; }
    public String forcaBruta (String alg, String hash, String salt) {
        // amb l'ajuda del charset simplificat, farem la força bruta perquè no duri molt llarga la comprobació
        // bucle aniuat, que comproba per cada lletra, de la posició 0 o 1 fins 5 o 6.
        // password en char[]
        return "";
    }
    public String getInterval(long t1, long t2) { return ""; }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);    // variable de classe h "npass"
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
    }
}