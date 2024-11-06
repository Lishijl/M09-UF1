package iticbcn.xifratge;

public class TextXifrat {

    private byte[] textXifrat;

    // constructor personalitzat
    public TextXifrat(byte[] xifrat) { this.textXifrat = xifrat; }

    // retornem l'instància de textXifrat en format String, reconvertit amb
    // el constructor de String fent new String()
    @Override
    public String toString() { return new String(textXifrat); }

    public byte[] getBytes() { return textXifrat; }
}
