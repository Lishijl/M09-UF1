package iticbcn.xifratge;
/* Test proporcionat que provarà els algorismes de cada xifrador. */



public class TestXifratge {
    public static void main(String[] args) {
    
        // array d'algorismesFactory, que crea noves instàncies segons el tipus de xifratge
        AlgorismeFactory[] aFactory = { new AlgorismeAES(), 
                                        new AlgorismeMonoalfabetic(), 
                                        new AlgorismePolialfabetic(), 
                                        new AlgorismeRotX() };
        
        // noms dels tipus de xifratge
        String[] aNames = { "AES", "Monoalfabètic", "Polialfabètic", "RotX" };

        // array de missatges de testeig
        String[] msgs = { "Test 01: Àlgid, Ülrich, Vàlid",
                            "Test 02: Caràcters especials ¡!¿?*-123[]{}@#" };

        // matriu de claus
        String[][] claus = { { "Clau Secreta 01" }, 
                            { "ErrorClau", null },
                            { "ErrorClau2", "123456" }, 
                            { "-1", "13", "1000", "Errorclau" } };

        // per a cada xifratge, recorrem l'array d'algorismeFactory.
        for (int i = 0; i < aFactory.length; i++) {

            // algorisme instanciat
            AlgorismeFactory alg = aFactory[i];
            // nom obtingut corresponent del xifratge
            String nom = aNames[i];
            // interficie xifrador, que reb instància del xifrador creat segons el tipus de xifratge, AES, mono, poli, rotX
            Xifrador xifrador = alg.creaXifrador();

            // separador que es mostra per pantalla
            System.out.println("==========================");

            // recorrem els missatges de test
            for (String msg : msgs) {

                // recorrem per cada missatge de test, la seva clau corresponent del xifratge de que li correspon
                for (String clau : claus[i]) {

                    // mostra misatge original, en quin algorisme es xifrara, i la clau utilitzada.
                    System.out.println("msg: " + msg);
                    System.out.println("Algorisme: " + nom);
                    System.out.println("Clau: " + clau);
                    // declara i inicialitza TextXifrat com a null
                    TextXifrat tx = null;

                    // amb try-catch capturem segons el tipus d'excepció generada, de ClauNoSuportada, 
                    // que imprimeix l'error del missatge a on es localitza.
                    try {
                        // assignem al TextXifrat el resultat de cridar en la interficie de Xifrador que
                        // utilitza el mètode xifra que retorna una instància de TextXifrat que conté un array de bytes del text Xifrat
                        tx = xifrador.xifra(msg, clau);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    // indiferentment del try-catch generi o no excepció, imprimeix el resultat del text xifrat
                    System.out.println("TextXifrat: " + tx);
                    // declara i inicialitza el text desxifrat com a null
                    String desxifrat = null;

                    // amb try-catch captura el tipus d'excepció ClauNoSuportada si es genera i imprimeix
                    // l'error del missatge a on es localitza.
                    try {
                        // assignem a desxifrat el resultat de cridar el desxifra de la interficie xifrador que
                        // retorna el text xifrat desxifrat segons el tipus de xifratge i clau
                        desxifrat = xifrador.desxifra(tx, clau);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    // indiferent de si es genera o no l'excepció, imprimeix el text desxifrat
                    System.out.println("Desxifrat: " + desxifrat);
                    System.out.println("------------------");
                }
            }
        }
    }
}