package manueleSeretti.Libreria;

public class Libro extends ElementoCatalogo {

    private String autore;
    private String genere;

    public Libro(String titolo, int anno, int nPag, String autore, String genere) {
        super(titolo, anno, nPag);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", codIsbm=" + codIsbm +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", nPag=" + nPag +
                '}';
    }
}
