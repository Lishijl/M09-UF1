package src.iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {
    // sobreescriptura de mètode abstracta segons el tipus d'algorisme del xifratge
    @Override
    public Xifrador creaXifrador() {
        // l'argorismePolialfabetic, crea una nova instància del XifradorPolialfabetic amb el mètode
        // creaXifrador() sobreescrit que retorna nova instància creada segons el 
        // tipus de xifratge
        return new XifradorPolialfabetic();
    }
}
