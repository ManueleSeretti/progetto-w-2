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

        for (int i = 0; i < 3; i++) {
            listaLibri.add(libroSupplier.get());
        }
        for (int i = 0; i < 3; i++) {
            listaRiviste.add(rivistaSupplier.get());
        }
        listaLibri.forEach(System.out::println);
        listaRiviste.forEach(System.out::println);

        //listaLibri.add(aggiungiLibro());

        loop:
        while (true) {
            try {
                System.out.println("SCEGLI L'OPRAZIONE DA FATE O DIGITA 0 PER CHIUDERE IL PROGRAMMA");
                System.out.println("1-Aggiungi un elemento:");
                System.out.println("2-Rimuovi un elemento:");
                System.out.println("3-Ricerca un elemento:");
                System.out.println("4-Consulta l'archivio:");
                int scelta = Integer.parseInt(input.nextLine());
                ciclo:
                switch (scelta) {
                    case 0: {
                        break loop;
                    }
                    case 1: {
                        System.out.println("cosa vuoi aggiungere?");
                        System.out.println("1-Libro");
                        System.out.println("2-Rivista");
                        int s = Integer.parseInt(input.nextLine());
                        scelta0:
                        switch (s) {
                            case 1: {
                                listaLibri.add(aggiungiLibro());
                                break scelta0;
                            }
                            case 2: {
                                listaRiviste.add(aggiungiRivista());
                                break scelta0;
                            }
                            default:
                                break scelta0;
                        }
                        break ciclo;
                    }
                    case 2: {
                        System.out.println("cosa vuoi eliminare?");
                        System.out.println("1-Libro");
                        System.out.println("2-Rivista");
                        int s = Integer.parseInt(input.nextLine());
                        scelta2:
                        switch (s) {
                            case 1: {
                                EliminaElementoIsbn(listaLibri);
                                break scelta2;
                            }
                            case 2: {
                                EliminaElementoIsbn(listaRiviste);
                                break scelta2;

                            }
                            default:
                                break scelta2;

                        }
                        break ciclo;
                    }
                    case 3: {
                        System.out.println("fai la ricerca in base:");
                        System.out.println("1- per codice isbn:");
                        System.out.println("2- per anno di publicazione:");
                        System.out.println("3- per Autore");
                        int s = Integer.parseInt(input.nextLine());
                        scelta3:
                        switch (s) {
                            case 1: {
                                System.out.println("cosa vuoi cercare?");
                                System.out.println("1-Libro");
                                System.out.println("2-Rivista");
                                int c = Integer.parseInt(input.nextLine());
                                switch (c) {
                                    case 1: {
                                        ricercaIsbn(listaLibri);
                                        break scelta3;
                                    }
                                    case 2: {
                                        ricercaIsbn(listaRiviste);
                                        break scelta3;
                                    }
                                    default:
                                        break scelta3;
                                }
                            }
                            case 2: {
                                System.out.println("cosa vuoi cercare?");
                                System.out.println("1-Libro");
                                System.out.println("2-Rivista");
                                int x = Integer.parseInt(input.nextLine());
                                switch (x) {
                                    case 1: {
                                        ricercaAnno(listaLibri);
                                        break scelta3;
                                    }
                                    case 2: {
                                        ricercaAnno(listaRiviste);
                                        break scelta3;
                                    }
                                    default:
                                        break scelta3;
                                }

                            }
                            case 3: {
                                ricercaAutore(listaLibri);
                                break scelta3;
                            }
                            default:
                                break scelta3;
                        }
                        break ciclo;
                    }
                    case 4: {
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
                        break ciclo;
                    }
                    default: {
                        System.out.println("codice inserito non valido!!!");
                        break ciclo;
                    }
                }

            } catch (Exception x) {
                System.err.println(x);
            }


        }


    }

    public static Libro aggiungiLibro() {
        Scanner input = new Scanner(System.in);
        String titolo;
        String autore;
        String genere;
        int anno;
        int pag;
        String title;


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
            toWrite += l.getTitolo() + "@" + l.getAnno() + "@" + l.getnPag() + "@" + l.getAutore() + "@" + l.getGenere() + "#";
        }
        toWrite += "fine-libri";
        for (Rivista r : listaRiv) {
            toWrite += r.getTitolo() + "@" + r.getAnno() + "@" + r.getnPag() + "@" + r.getPeriodicità() + "#";
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


}
