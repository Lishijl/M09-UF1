package iticbcn.xifratge;

// classe concreta AlgorismeRotX que exten de classe abstracta AlgorismeFactory
public class AlgorismeRotX extends AlgorismeFactory {

    // sobreescriptura de mètode abstracta segons el tipus d'algorisme del xifratge
    @Override
    public Xifrador creaXifrador() {
        // l'argorismeRotX, crea una nova instància del XifradorRotX amb el mètode
        // creaXifrador() sobreescrit que retorna nova instància creada segons el 
        // tipus de xifratge
        return new XifradorRotX();
    }
}
