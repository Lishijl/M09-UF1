package src.iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    // sobreescriptura de mètode abstracta segons el tipus d'algorisme del xifratge
    @Override
    public Xifrador creaXifrador() {
        // l'algorismeMonoalfabetic, crea una nova instància del XifradorMonoalfabetic amb el mètode
        // creaXifrador() sobreescrit que retorna nova instància creada segons el 
        // tipus de xifratge
        return new XifradorMonoalfabetic();
    }
}
