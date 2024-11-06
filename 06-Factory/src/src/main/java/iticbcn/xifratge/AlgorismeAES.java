package iticbcn.xifratge;


public class AlgorismeAES extends AlgorismeFactory {
    // sobreescriptura de mètode abstracta segons el tipus d'algorisme del xifratge
    @Override
    public Xifrador creaXifrador() {
        // l'argorismeAES, crea una nova instància del XifradorAES amb el mètode
        // creaXifrador() sobreescrit que retorna nova instància creada segons el 
        // tipus de xifratge
        return new XifradorAES();
    }
}
