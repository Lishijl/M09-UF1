package iticbcn.xifratge;

public abstract class AlgorismeFactory {
    // retorna una instància de tipus Xifrador abstracte per a poder-se redefinir.
    public abstract Xifrador creaXifrador();
}
