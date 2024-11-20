package iticbcn.hashing;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Hashes {
    // nombre de proves per algorisme
    int npass;

    // dos mètodes hashing que genera un hash del password amb un salt amb dos tipus d'algorismes SHA-512 i PBKDF2
    public String getSHA512AmbSalt (String pw, String salt) {
        // valor que es retornarà del hash en hexadecimal
        String hash512 = null;
        try {
            // array de bytes de salt i contrasenya obtinguts amb getBytes() usant l'standardcharset UTF-8
            byte[] saltB = salt.getBytes(StandardCharsets.UTF_8);
            byte[] pwB = pw.getBytes(StandardCharsets.UTF_8);

            // instància del MessageDigest de tipus SHA-512 que ens servirà per generar el hash amb aquest algorisme
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // afegim primer el salt en bytes
            md.update(saltB);
            // i després obtenim el hash que conté el salt i la contrasenya encriptat amb SHA-512
            byte[] hashB = md.digest(pwB);
            HexFormat hex = HexFormat.of(); 
            // a partir de l'array de bytes[] del hash de la contrasenya i el salt 
            // el retorna en Hexadecimal tipus String fora del bloc try-catch
            hash512 = hex.formatHex(hashB);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash512;
    }

    public String getPBKDF2AmbSalt (String pw, String salt) {
        // valor que es retornarà del hash en hexadecimal
        String hashPBKDF2 = null;
        try {
            // contrasenya i salt convertits en array de char i byte per a pasar-li al PBEKeySpec
            char[] pwCh = pw.toCharArray();
            byte[] saltB = salt.getBytes(StandardCharsets.UTF_8);
            // vegades que itera generar el hash
            int iteracions = 65536;
            // tamany del hash a generar de 128 bits
            int hashSize = 128;

            // PBEKeySpec a partir dels arguments rebuts indica a l'algoritme la manera de generar el hash
            PBEKeySpec spec = new PBEKeySpec(pwCh, saltB, iteracions, hashSize);
            // instància de l'algoritme PBKDF2 per generar el hash a partir dels paràmetres del spec
            SecretKeyFactory scrKF = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            // a partir del SecretKeyFactory, generem el hash amb l'spec i amb getEncoded() pasem el hash en array de byte[]
            byte[] hashB = scrKF.generateSecret(spec).getEncoded();

            HexFormat hex = HexFormat.of();
            // convertim l'array de bytes del hash en hexadecimal llegible
            hashPBKDF2 = hex.formatHex(hashB);
        // capturem els tipus d'excepcions per mostrar-los el fil d'execucions
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        // retorna el hash sigui null o s'hagi generat en el bloc del try
        return hashPBKDF2;
    }
    public String forcaBruta (String alg, String hash, String salt) {
        // reinicia el comptador de vegades que s'ha provat les contrasenya per cada algorisme
        npass = 0;
        // charset que utilitzarem per comprovar per cada posició de la contrasenya si el hash coincideix
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        // màxim longitud de clau a trobar de 6 caràcters
        char[] pwTrobat = new char[6];
        // StringBuffer que per cada longitud de characters diferents, que s'hagin buscat
        // el seu hash, si no retorna null, es guarda en el StringBuffer.
        String pw = null;

        // iterem 6 vegades per cada posició de la contrasenya a trobar
        for (int pos = 1; pos <= pwTrobat.length; pos++) {
            // per diferents longituds de contrasenya, amb fors aninuats verifiquem si hi ha hash coincident
            for (int i = 0; i < charset.length; i++) {
                // per a la posició 0, comprobarem amb tots els caràcters del charset
                pwTrobat[0] = charset[i];
                // comproba si s'ha trobat el hash coincident, en cas de que si, retorna el resultat trencant el bucle
                // si es la posició actual de la lletra, i no ha trobat resultat i pw és null, continúa els bucles anidats
                if (pos == 1 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                for (int j = 0; j < charset.length; j++) {
                    pwTrobat[1] = charset[j];
                    if (pos == 2 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                    for (int k = 0; k < charset.length; k++) {
                        pwTrobat[2] = charset[k];
                        if (pos == 3 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                        for (int l = 0; l < charset.length; l++) {
                            pwTrobat[3] = charset[l];
                            if (pos == 4 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                            for (int m = 0; m < charset.length; m++) {
                                pwTrobat[4] = charset[m];
                                if (pos == 5 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                                for (int n = 0; n < charset.length; n++) {
                                    pwTrobat[5] = charset[n];
                                    if (pos == 6 && (pw = testPw(new String(pwTrobat, 0, pos), alg, hash, salt)) != null) return pw;
                                }   // 6
                            }   // 5
                        }   // 4
                    }   // 3
                }   // 2
            }   // 1
        }

        // amb l'ajuda del charset simplificat, farem la força bruta perquè no duri molt llarga la comprobació
        // bucle aniuat, que comproba per cada lletra, de la posició 0 o 1 fins 5 o 6.
        // password en char[]
        return pw;
    }
    // mètode privat que per cada cadena de String rebut, tipus d'algorisme, el hash original amb el que comparar i el salt per construir
    // un nou hash a partir del String rebut com a contrasenya de testeig, si troba coincidencia, retorna l'String rebut original com a contrasenya
    // trobada
    private String testPw(String pwTest, String alg, String hash, String salt) {
        String hashTrobat = null;
        String result = null;
        if (alg.equals("SHA-512")) {
            // li passem la contrasenya generada en força bruta per extreure un hash amb la que comparar amb el hash original
            hashTrobat = getSHA512AmbSalt(pwTest, salt);
            if (hashTrobat != null && hashTrobat.equals(hash)) result = pwTest;
        } else if (alg.equals("PBKDF2")) {
            hashTrobat = getPBKDF2AmbSalt(pwTest, salt);
            if (hashTrobat != null && hashTrobat.equals(hash)) result = pwTest;
        }
        // intents de hashos comparats
        npass++;
        // si troba retorna pwTest, sinó null
        return result;
    }
    
    // mètode que calcula el temps en millis
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        // segons totals
        long seg = millis/1000;
        // li aplico el modul i es reasigna a millis els millis sobrants
        millis %= 1000;
        // minuts totals
        long min = seg / 60;
        // reasigno els segons que sobren de treure-li els minuts
        seg %= 60;
        // hores totals
        long hores = min / 60;
        // reasigno minutus sobrants després de deduir les hores
        min %= 60;
        // dies totals
        long dies = hores / 24;
        // reasigno hores sobrants després de deduir els dies
        hores %= 24;
        // retorno la cadena de string formatejat
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, min, seg, millis);
    }

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
            // nom d'algorisme
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            // hash en hexadecimal de la contrasenya amb salt en byte[]
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            // temps abans d'iniciar de l'execució de força bruta
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            // temps en millis final que en fer la diferencia ens dona el total de temps que ha tardat en trobar la contrasenyaa
            
            System.out.printf("Pass : %s\n", pwTrobat);
            // variable de classe h "npass", vegades de contrasenyes trobades
            System.out.printf("Provats: %d\n", h.npass);
            //  resultat del temps que s'han tardat amb format, any, mes, setmana, dies, hores, minuts, segons, millis
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}