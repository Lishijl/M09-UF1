// declaració del paquet
package iticbcn.xifratge;

/* Ara el programa enlloç de xifrar-lo amb un array de chars ordenats,
 * ho voldrem fer amb un mono-alfabètic que consisteix en permutar 
 * l'abecedari original ja que és una miqueta més segur.
 * 
 * Substituint el caràcter de la posició de l'abecedari original 
 * per a la mateixa posició en l'abecedari generat amb la permutació.
 * 
 * Utilitzarem els mètodes permutaAlfabet(char[]) que retorna el mono-alfabet,
 * el xifraMonoAlfa(String) que retorna la cadena xifrada amb el mono-alfabet 
 * generat i el desxifraMonoAlfa(String) que el retorna desxifrat.
 * 
 * DAM2B: Lishi JL */

import java.nio.charset.StandardCharsets;
// import per poder manipular Llistes, ArrayList.
import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    // variables globals per a que sigui accessible per consultar desde qualsevol mètode
    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    public char[] permutedChars;
    
    // constructor per inicialitzar la permutació abans del xifratge
    public XifradorMonoalfabetic() {
        // hem fet a la crida de permutaAlfabet() per barrejar l'ordre de l'abecedari i ho guardem a la variable global
        this.permutedChars = permutaAlfabet(UPPERCHARS);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // verificació primerament que la clau no és null, llança una excepció personalitzada ClauNoSuportada
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }
        // retorna missatge xifrat en String
        String textXifrat = xifraMonoAlfa(msg);

        // obtenim l'array de bytes a partir d'una conversió amb StandardCharsets.UTF_8
        byte[] xifratB = textXifrat.getBytes(StandardCharsets.UTF_8);

        // retorna una nova instància de TextXifrat amb l'array de bytes del text xifrat convertit amb StandardCharsets.UTF_8
        return new TextXifrat(xifratB);
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        // obtenim els l'array de bytes xifrats de l'objecte TextXifrat
        byte[] xifratB = xifrat.getBytes();

        // convertim l'array de bytes al String de nou amb StandardCharsets.UTF_8
        String textXifrat = new String(xifratB, StandardCharsets.UTF_8);

        // desxifrem l'String del textXifrat cridant a desXifraMonoAlfa()
        return desxifraMonoAlfa(textXifrat);
    }

    // mètode per permutar UPPERCHARS
    public char[] permutaAlfabet(char[] original) {

        // ArrayList, llista de tipus objecte Characters, 
        // inicialitzant-lo sense tipus amb el seu builder per defecte.
        ArrayList<Character> resultList = new ArrayList<>();

        // amb el foreach, per cada lletra del abecedari, ho afegim a la 
        // llista amb el mètode add()
        for (char letter : UPPERCHARS) { resultList.add(letter); }

        // passem la llista de Chars al mètode shuffle() de la classe Collections,
        // per permutar l'ordre de dels elements de la llista original
        Collections.shuffle(resultList);

        // declarem i inicialitzem la variable permutat de tipus StringBuffer amb una
        // nova instància creada amb el seu constructor per defecte, on acumularem cada
        // caràcter en un array de Strings
        StringBuffer permutat = new StringBuffer();

        // bucle for que iterarà sobre l'arrayList de tipus Character Objecte, que
        // recorre la longitud dels elements d'aquesta llista
        for (int i = 0; i < resultList.size(); i++) {

            // acumulem a la variable de StringBuffer amb el mètode append() per fer un
            // array de String indirectament, on li passem com a argument l'obtenció de
            // l'element del ArrayList de Characters amb l'accessor get() que li passem
            // com a argument l'índex i
            permutat.append(resultList.get(i));
        }

        // retornem el resultat permutat sense crear variable extra que conté l'array 
        // de chars permutat, primer convertint el "permutat" de StringBuffer a String 
        // amb la funció toString() i el convertim de nou a array de char com a format 
        // correcte per retornar amb la funció de toCharArray()
        return permutat.toString().toCharArray();
    }

    // xifrem el text amb l'array permutat
    public String xifraMonoAlfa(String text) {
        return convert(text, UPPERCHARS, permutedChars);
    }

    // desxifrem el text xifrat amb l'array original UPPERCHARS
    public String desxifraMonoAlfa(String textXifrat) {
        return convert(textXifrat, permutedChars, UPPERCHARS);
    }
    
    // mètode que buscarem la posició de la lletra per l'array 
    // d'abecedari rebut sigui l'original o el permutat per 
    // després la conversió, retornarem -1 per a que poguem també
    // afegir caràcters sense convertir
    public int trobaPosicio(char[] array, char character) {

        // itera sobre l'array rebut i mira si coincideix els 
        // caràcters per retornar 1 si es verifica
        for (int i = 0; i < array.length; i++) {
            if (array[i] == character) {
                return i;
            }
        }
        return -1;
    }

    // mètode senzill que retorna el caràcter corresponent a la conversió
    public char convertChar(int position, char[] inResultArray) {
        return inResultArray[position];
    }

    // mètode de conversió de text general, ja sigui per xifrar o desxifrar, 
    // dependrà de l'ordre especificat en els paràmetres
    public String convert(String textToConvert, char[] originalA, char[] resultA) {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < textToConvert.length(); i++) {
            char originalCh = textToConvert.charAt(i);

            // busca en el l'array original, amb el caràcter majusculitzat sempre
            int position = trobaPosicio(originalA, Character.toUpperCase(originalCh));
            
            // perquè el 0 també pot ser una posició de l'array, entra a la condició si 
            // la posició ha estat trobada
            if (position > -1) {

                // comproba que abans de ser majusculitzat, per afegir el caràcter majuscula convertit directament
                if (Character.isUpperCase(originalCh)) {
                    result.append(convertChar(position, resultA));
                // si resulta que originalment no era majuscula, el torna a convertir a minuscula el caracter trobat per la posicio
                } else {
                    result.append(Character.toLowerCase(convertChar(position, resultA)));
                }
            // si no es troba cap coincidencia, mantenim el caràcter original sense convertir
            } else {
                result.append(originalCh);
            }
        }

        // retornem el StringBuffer en format string amb la funció toString()
        return result.toString();
    }
    /*
    // mètode principal d'execució en que posarà els textos de test per veure que funciona
    // després permuta l'array original, i finalment passa els test per tots els mètodes 
    // per cada iteració.
    public static void main(String[] args) {
        
        String[] tests = { "abc", "xyz", "Hola pinÜino, adióS. :D!" };
        permutedChars = permutaAlfabet(UPPERCHARS);
        
        // iteració sobre cada text de testeig i passa el text per tots els mètodes per iteració
        for (String test : tests) {
            
            // per test, passa pels 2 mètodes
            String xifrat = xifraMonoAlfa(test);
            String desxifrat = desxifraMonoAlfa(xifrat);
            
            // mostrem els resultats amb formats
            System.out.printf("%-15s%-34s%-12s%-15s%s%n","Original:", test, "->", "Encriptat:", xifrat);
            System.out.printf("%-15s%-34s%-12s%-15s%s%n%n","Xifrat:", xifrat, "->", "Desxifrat:", desxifrat);
        }
    }
    */
}