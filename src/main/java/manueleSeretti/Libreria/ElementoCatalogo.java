package manueleSeretti.Libreria;

import java.util.Random;

public abstract class ElementoCatalogo {
    protected int codIsbm;
    protected String titolo;
    protected int anno;
    protected int nPag;

    public ElementoCatalogo(String titolo, int anno, int nPag) {
        Random rndm = new Random();
        this.codIsbm = rndm.nextInt(10000, 99999);
        this.titolo = titolo;
        this.anno = anno;
        this.nPag = nPag;
    }

    public int getCodIsbm() {
        return codIsbm;
    }

    public void setCodIsbm(int codIsbm) {
        this.codIsbm = codIsbm;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getnPag() {
        return nPag;
    }

    public void setnPag(int nPag) {
        this.nPag = nPag;
    }


}
