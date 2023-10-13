package manueleSeretti;

import com.github.javafaker.Faker;
import manueleSeretti.Libreria.Libro;
import manueleSeretti.Libreria.Periodicità;
import manueleSeretti.Libreria.Rivista;

import java.util.*;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();
        Supplier<Libro> libroSupplier = () -> new Libro(faker.book().title(), rndm.nextInt(1990, 2020), rndm.nextInt(20, 350), faker.book().author(), faker.book().genre());
        Supplier<Rivista> rivistaSupplier = () -> new Rivista(faker.book().title(), rndm.nextInt(1990, 2020), rndm.nextInt(20, 350), Periodicità.randomPeriodicita());

        List<Libro> listaLibri = new ArrayList<>();
        List<Rivista> listaRiviste = new ArrayList<>();

//        for (int i = 0; i < 20; i++) {
//            listaLibri.add(libroSupplier.get());
//        }
//        for (int i = 0; i < 20; i++) {
//            listaRiviste.add(rivistaSupplier.get());
//        }

        //listaLibri.add(aggiungiLibro());
        listaRiviste.add(aggiungiRivista());
        listaLibri.forEach(System.out::println);
        listaRiviste.forEach(System.out::println);

    }

    public static Libro aggiungiLibro() {
        Scanner input = new Scanner(System.in);
        String titolo;
        String autore;
        String genere;
        int anno;
        int pag;
        String title;
        while (true) {
            try {
                System.out.println("inserisci titolo");
                title = input.nextLine();
                System.out.println("inserisci anno di pubblicazione");
                anno = Integer.parseInt(input.nextLine());
                System.out.println("inserisci numero di pagine");
                pag = Integer.parseInt(input.nextLine());
                System.out.println("inserisci autore");
                autore = input.nextLine();
                System.out.println("inserisci genere");
                genere = input.nextLine();
                input.close();
                break;
            } catch (Exception ex) {
                System.out.println("Il valore inserito non è accettabile!!!");
                System.err.println(ex.getMessage());
            }
        }
        return new Libro(title, anno, pag, autore, genere);
    }

    public static Rivista aggiungiRivista() {
        Scanner input = new Scanner(System.in);
        int anno;
        int pag;
        String title;
        while (true) {
            try {
                System.out.println("inserisci titolo");
                title = input.nextLine();
                System.out.println("inserisci anno di pubblicazione");
                anno = Integer.parseInt(input.nextLine());
                System.out.println("inserisci numero di pagine");
                pag = Integer.parseInt(input.nextLine());

                input.close();
                break;
            } catch (Exception ex) {
                System.out.println("Il valore inserito non è accettabile!!!");
                System.err.println(ex.getMessage());
            }
        }
        return new Rivista(title, anno, pag, Periodicità.randomPeriodicita());
    }
}
