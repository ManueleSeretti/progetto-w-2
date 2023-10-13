package manueleSeretti.Libreria;

public class Rivista extends ElementoCatalogo {
    private Periodicità periodicità;

    public Rivista(String titolo, int anno, int nPag, Periodicità periodicità) {
        super(titolo, anno, nPag);
        this.periodicità = periodicità;
    }


    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicità=" + periodicità +
                ", codIsbm=" + codIsbm +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", nPag=" + nPag +
                '}';
    }
}
