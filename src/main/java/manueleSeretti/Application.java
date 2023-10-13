package manueleSeretti;

import com.github.javafaker.Faker;
import manueleSeretti.Libreria.ElementoCatalogo;
import manueleSeretti.Libreria.Libro;
import manueleSeretti.Libreria.Periodicità;
import manueleSeretti.Libreria.Rivista;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();
        Supplier<Libro> libroSupplier = () -> new Libro(faker.book().title(), rndm.nextInt(1990, 2020), rndm.nextInt(20, 350), faker.book().author(), faker.book().genre());
        Supplier<Rivista> rivistaSupplier = () -> new Rivista(faker.book().title(), rndm.nextInt(1990, 2020), rndm.nextInt(20, 350), Periodicità.randomPeriodicita());

        List<ElementoCatalogo> listaLibri = new ArrayList<>();
        List<ElementoCatalogo> listaRiviste = new ArrayList<>();
        List<ElementoCatalogo> catalogo = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            listaLibri.add(libroSupplier.get());
        }
        for (int i = 0; i < 1; i++) {
            listaRiviste.add(rivistaSupplier.get());
        }

        //listaLibri.add(aggiungiLibro());
        // listaRiviste.add(aggiungiRivista());
        listaLibri.forEach(System.out::println);
        listaRiviste.forEach(System.out::println);


        //EliminaElementoIsbn(listaRiviste);

        //ricercaIsbn(listaRiviste);

        //ricercaAnno(listaRiviste);

        // ricercaAutore(listaLibri);

        try {
            saveToDisk(listaLibri, listaRiviste);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            catalogo = loadFromDisk();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        catalogo.forEach(System.out::println);
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


    public static void EliminaElementoIsbn(List<ElementoCatalogo> list) {

        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("inserisci codice");
                int isbn = Integer.parseInt(input.nextLine());
                List<ElementoCatalogo> c = list.stream().filter(e -> e.getCodIsbm() == isbn).toList();
                list.remove(c.get(0));
                list.forEach(System.out::println);
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                System.out.println("valore non accettato");
            }
        }

    }

    public static void ricercaIsbn(List<ElementoCatalogo> list) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("inserisci codice");
                int isbn = Integer.parseInt(input.nextLine());
                List<ElementoCatalogo> c = list.stream().filter(e -> e.getCodIsbm() == isbn).toList();
                System.out.println(c.get(0));
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                System.out.println("valore non accettato");
            }
        }
    }

    public static void ricercaAnno(List<ElementoCatalogo> list) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("inserisci anno di pubblicazione");
                int anno = Integer.parseInt(input.nextLine());
                List<ElementoCatalogo> c = list.stream().filter(e -> e.getAnno() == anno).toList();
                c.forEach(System.out::println);
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                System.out.println("valore non accettato");
            }
        }
    }

    public static void ricercaAutore(List<ElementoCatalogo> lista) {
        Scanner input = new Scanner(System.in);
        List<Libro> list = new ArrayList<>();
        lista.forEach(e -> list.add((Libro) e));
        while (true) {
            try {
                System.out.println("inserisci autore");
                String autore = input.nextLine();
                List<Libro> c = list.stream().filter(e -> e.getAutore().equalsIgnoreCase(autore)).toList();
                c.forEach(System.out::println);
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                System.out.println("valore non accettato");
            }
        }
    }

    public static void saveToDisk(List<ElementoCatalogo> lista1, List<ElementoCatalogo> lista2) throws IOException {
        String toWrite = "";
        List<Libro> listaLib = new ArrayList<>();
        lista1.forEach(e -> listaLib.add((Libro) e));
        List<Rivista> listaRiv = new ArrayList<>();
        lista2.forEach(e -> listaRiv.add((Rivista) e));

        for (Libro l : listaLib) {
            toWrite += l.getTitolo() + "@" + l.getCodIsbm() + "@" + l.getAnno() + "@" + l.getAutore() + "@" + l.getGenere() + "#";
        }
        toWrite += "fine-libri";
        for (Rivista r : listaRiv) {
            toWrite += r.getTitolo() + "@" + r.getCodIsbm() + "@" + r.getAnno() + "@" + r.getPeriodicità() + "#";
        }
        File file = new File("catalogo.txt");

        FileUtils.writeStringToFile(file, toWrite, "UTF-8");
    }

    public static List<ElementoCatalogo> loadFromDisk() throws IOException {
        File file = new File("catalogo.txt");
        List<Libro> listaLibri = new ArrayList<>();
        List<Rivista> listaRiviste = new ArrayList<>();
        String fileString = FileUtils.readFileToString(file, "UTF-8");

        List<String> splitTipo = Arrays.asList(fileString.split("fine-libri"));

        String[] libri = splitTipo.get(0).split("#");
        String[] riviste = splitTipo.get(1).split("#");

        for (String s : libri) {
            String[] dettagliLibro = s.split("@");
            listaLibri.add(new Libro(dettagliLibro[0], Integer.parseInt(dettagliLibro[1]), Integer.parseInt(dettagliLibro[2]), dettagliLibro[3], dettagliLibro[4]));
        }
        for (String s : riviste) {
            String[] dettagliRivista = s.split("@");
            listaRiviste.add(new Rivista(dettagliRivista[0], Integer.parseInt(dettagliRivista[1]), Integer.parseInt(dettagliRivista[2]), Periodicità.valueOf(dettagliRivista[3])));
        }

        List<ElementoCatalogo> catalogo = new ArrayList<>();
        catalogo.addAll(listaLibri);
        catalogo.addAll(listaRiviste);
        return catalogo;


    }
//        return splitElementiString.stream().map(stringa -> {
//
//            String[] productInfos = stringa.split("@");
//            return new Product(productInfos[0], productInfos[1], Double.parseDouble(productInfos[2]));
//        }).toList();


}
