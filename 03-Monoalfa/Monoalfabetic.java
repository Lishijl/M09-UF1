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

// import per poder manipular Llistes, ArrayList.
import java.util.*;

public class Monoalfabetic {
    public static final char[] UPPERCHARS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    public static char[] permutedChars;

    public static char[] permutaAlfabet(char[] original) {

        // genero un tipus de llista ArrayList que serà una llista de Caràcters, 
        // inicialitzant-lo sense tipus amb el seu builder per defecte.
        ArrayList<Character> resultList = new ArrayList<>();

        // amb el foreach, per cada lletra, ho afegim a la llista amb el mètode add()
        for (char letter : UPPERCHARS) { resultList.add(letter); }

        // tractem l'array rebut, i comptem amb el mètode Arrays.asList(arrayChar)
        // generem un new ArrayList<>(per això), finalment ho assignem a la llista tipus ArrayList<> de tipus Character

        // passem la llista al mètode shuffle.

        // aquí farem ús del mètode que ens ajudarà a permutar l'array original

        // al mètode de collections.shuffle -> li passarem un ArrayList de chars. typeofList<type> var
        // obligatoriament retornar en format array de char, y no usar list de list de OBJECTS!


        // iterar sobre LLISTA -> TOsTRING -> TOCHARaRRAY
        return null;
    }
    public static String xifraMonoAlfa(String text) {
        // per cada lletra guardat, -> el convertim en majuscula, dins del mètode troba posicio 
        // cridarem mètode adicional per rebre posició
        // després laltre metode que xifra per char
        return null;
    }
    public static String desxifraMonoAlfa(String textXifrat) {
        // cri
        return null;
    }
    public static void main(String[] args) {
        String[] tests = { "abc", "xyz", "Hola pinÜino, adióS. :D!" };
        permutedChars = permutaAlfabet(UPPERCHARS);
        for (String test : tests) {
            // tirarem aquí cada cadena a xifrar i desxifrar.
            // recorda tenir en compte que són CASE sensitive, 
            // per encriptar, caldrà passar-ho tot en MAYUS, però
            // prèviament, reservem aquesta lletra


            // mètode adicional, posesio, donat un array char, lletra, -> torna posicio de la lletra dins l'array. 

            // una vegada trobat la posició, decideixes, a partir, d'aquest int, quin ARRAY org, o gen, per encriptar o desencriptar. 
            // per lletra, dins de cada FUNCIÓ, reb CHAR RESULT. mira, CHAR ORIGINAL guardat prèviament

            // pregunta si es MAYUS. -> SI VERIFICA, APPEND. sino.
            // toLowerCase() <- append 
        }
    }
}