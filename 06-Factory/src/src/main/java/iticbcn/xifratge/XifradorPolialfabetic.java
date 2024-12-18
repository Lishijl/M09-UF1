// declaració del paquet
package iticbcn.xifratge;

import java.util.Random;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Collections;

public class XifradorPolialfabetic implements Xifrador {

    //private static final int SECRETKEY = 91059;
    private Random valueSequence;

    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    public char[] permutedChars;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // intenta convertir en un long la clau String
            long clauL = Long.parseLong(clau);
            // inicialitzem el random amb la llavor abans de xifrar
            initRandom(clauL);
            // xifrem el missatge per cada caracter es permuta l'alfabet
            String msgXifrat = xifraPoliAlfa(msg);
            // obtenim l'array de bytes a partir d'una conversió amb StandardCharsets.UTF_8
            byte[] xifratB = msgXifrat.getBytes(StandardCharsets.UTF_8);
            // retorna una nova instància de TextXifrat amb l'array de bytes del text xifrat convertit amb StandardCharsets.UTF_8
            return new TextXifrat(xifratB);

        } catch (NumberFormatException e) {
            // captura l'excepció de la conversió i llença missatge personalitzat per l'excepció de tipus ClauNoSuportada
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long clauL = Long.parseLong(clau);
            // inicialitzem el random amb la llavor abans de xifrar
            initRandom(clauL);
            // retorno el resultat de desxifrar el xifrat obtingut del TextXifrat amb el getBytes(), de passar-li l'String
            // que ha convertit l'array de bytes en new String() amb StandardCharsets.UTF_8
            return desxifraPoliAlfa(new String(xifrat.getBytes(), StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

    // mètode que inicialitza la classe Random a partir d'una llavor
    public void initRandom(long seat) {
        valueSequence = new Random(seat);
    }
    // mètode amb efecte colateral, en l'array de alfabet permutat de la variable global.
    // serà cridat per cada lletra que es vulgui xifrar o desxifrar
    public void permutaAlfabet() {
        List<Character> resultList = new ArrayList<>();
        for (char letter : UPPERCHARS) { resultList.add(letter); }
        // el random anirà proporcionant noves barreges amb el shuffle cada vegada que 
        // es crida permutaAlfabet()
        Collections.shuffle(resultList, valueSequence);
        char[] charResult = new char[resultList.size()];
        // finalment afegim directament el valor de la llista a un array de chars
        for (int i = 0; i < resultList.size(); i++) {
            charResult[i] = resultList.get(i);
        }
        // asignem a la global perquè és void.
        permutedChars = charResult; 
    }
    // troba la posició en l'array rebut, sigui l'original o la permutada
    public int trobaPosicio(char character, char[] arrayOrigen) {
        for (int i = 0; i < arrayOrigen.length; i++) {
            if (arrayOrigen[i] == character) return i;
        }
        // retorna negatiu si no troba cap coincidencia
        return -1;
    }
    public char convertChar(int position, char[] inResultArray) {
        // cada char serà convertit, sigui per xifrar o desxifrar, a l'array permutat o 
        // array original segons el cas
        return inResultArray[position];
    }
    // xifra cadena rebuda, amb polialfabet permutats
    public String xifraPoliAlfa(String msg) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < msg.length(); i++) {
            // gestionem per lletra
            char originalCh = msg.charAt(i);
            // permuta arrayOriginal per cada lletra
            permutaAlfabet();
            // busca posició amb el caracter mayus, de l'array original
            int position = trobaPosicio(Character.toUpperCase(originalCh), UPPERCHARS);
            // si troba coincidencia
            if (position > -1) {
                if (Character.isUpperCase(originalCh)) {
                    // append de la conversió directe al array permutat al ser majuscula originalCh
                    result.append(convertChar(position, permutedChars));
                } else {
                    // minusculitza abans d'afegir amb l'append
                    result.append(Character.toLowerCase(convertChar(position, permutedChars)));
                }

            } else {
                // recordem que sempre que no es troba, no el xifrem
                result.append(originalCh);
            }
        }
        // retornem text per cada lletra xifrat amb una nova permutació
        return result.toString();
    }
    // desxifra text xifrat amb polialfabet permutats
    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < msgXifrat.length(); i++) {
            // gestionem per lletra
            char originalCh = msgXifrat.charAt(i);
            // permuta arrayOriginal per cada lletra
            permutaAlfabet();
            // busca posició amb el caracter mayus, de l'array permutat
            int position = trobaPosicio(Character.toUpperCase(originalCh), permutedChars);
            // si troba coincidencia
            if (position > -1) {
                if (Character.isUpperCase(originalCh)) {
                    // afegim directe lletra desencriptada amb l'array original
                    result.append(convertChar(position, UPPERCHARS));
                } else {
                    // afegim minusculitzat, la lletra desencriptada amb l'array original
                    result.append(Character.toLowerCase(convertChar(position, UPPERCHARS)));
                }
            } else {
                // recordem que sempre que no es troba, no el xifrem
                result.append(originalCh);
            }
        }
        // retornem text per cada lletra xifrat amb una nova permutació
        return result.toString();
    }
    /*
    public static void main(String[] args) {
        // bateria de proves, per cada cadena, 1 polialfa generat de nou permutats
        String msgsTest[] = {"Test 01: àrbitre, coixí, Perímetre",
        "Test 02: Taüll, DÍA, año",
        "Test 03: Peça, Òrrius, Bòvila"};
        // variable d'array de Strings que s'inicialitza amb la longitud d'elements de msgsTest
        String msgsXifrats[] = new String[msgsTest.length];
        
        System.out.println("Xifratge:\n--------");
        // gestionem cada test 1x1
        for (int i = 0; i < msgsTest.length; i++) {
            // inicialitzem el random amb una clauSecreta com a llavor per a cada test,
            // en que aquest mateix Random permet permutar l'alfabet per cada lletra, 
            // amb valors generats secuencialment, i que conserva el mateixa secuencia 
            // de valors generats per cada test o crida al mètode xifraPoliAlfa()
            initRandom(SECRETKEY);
            // assignem per cada posició de la llista de text resultant, de cridar el 
            // xifraPoliAlfa() per test
            msgsXifrats[i] = xifraPoliAlfa(msgsTest[i]);
            // impresió de resultat amb format mostrant el text original prèviament
            System.out.printf("%-34s -> %s%n", msgsTest[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge:\n--------");
        // recorre msgsTest, perquè té el mateix nombre d'elements que msgsXifrats
        for (int i = 0; i < msgsTest.length; i++) {
            initRandom(SECRETKEY);
            String msgDesxifrat = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msgDesxifrat);
        }
    }
    */
}