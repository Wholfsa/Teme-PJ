package lab6ex1;

import java.time.*;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class MainAPP {
    public static void main(String[] args) {
        List<Angajat> angajati = Arrays.asList(
                new Angajat("Ion Popescu", "director", LocalDate.of(2020, Month.JANUARY, 15), 5000),
                new Angajat("Maria Ionescu", "sef echipa", LocalDate.of(2021, Month.APRIL, 10), 4000),
                new Angajat("Vasile Gheorghe", "analist", LocalDate.of(2022, Month.JULY, 5), 2500),
                new Angajat("Andrei Vasilescu", "programator", LocalDate.of(2023, Month.AUGUST, 12), 3000)
        );

        // 1. afisarea listei de angajati folosind referinte la metode
        System.out.println("Lista angajatilor:");
        angajati.forEach(System.out::println);

        // 2. afisarea angajatilor care au salariul peste 2500 RON
        System.out.println("\nAngajatii cu salariul peste 2500 RON:");
        angajati.stream().filter(ang -> ang.getSalariu() > 2500).forEach(System.out::println);

        // 3. crearea unei liste cu angajatii din aprilie anul trecut cu functii de conducere
        int anulCurent = LocalDate.now().getYear();
        List<Angajat> angajatiAprilie = angajati.stream().filter(ang -> ang.getDataAngajarii().getMonth() == Month.APRIL &&
                        ang.getDataAngajarii().getYear() == anulCurent - 1 && (ang.getPost().contains("sef") || ang.getPost().contains("director"))).collect(Collectors.toList());
        //collectors.tolist -> transforma rezultatul unui flux de date (Stream) intr-o colectie de tip List.

        System.out.println("\nAngajatii din aprilie anul trecut cu functii de conducere:");
        angajatiAprilie.forEach(System.out::println);

        // 4. afisarea angajatilor fara functii de conducere in ordine descrescatoare a salariilor
        System.out.println("\nAngajatii fara functii de conducere in ordine descrescatoare a salariilor:");
        angajati.stream().filter(ang -> !ang.getPost().contains("sef") && !ang.getPost().contains("director")).sorted((a1, a2) -> Float.compare(a2.getSalariu(), a1.getSalariu())).forEach(System.out::println);

        // 5. extragerea numelor angajaților scrise cu majuscule
        System.out.println("\nNumele angajatilor scrise cu majuscule:");
        List<String> numeAngajati = angajati.stream().map(ang -> ang.getNume().toUpperCase()).collect(Collectors.toList());
        numeAngajati.forEach(System.out::println);

        // 6. afisarea salariilor < de 3000 RON
        System.out.println("\nSalariile mai mici de 3000 RON:");
        angajati.stream().map(Angajat::getSalariu).filter(sal -> sal < 3000).forEach(System.out::println);

        // 7. afisarea datelor primului angajat al firmei
        System.out.println("\nPrimul angajat al firmei:");
        angajati.stream().min(Comparator.comparing(Angajat::getDataAngajarii)).ifPresentOrElse(
                        System.out::println, () -> System.out.println("Nu exista angajati.")
                );

        // 8. afisarea de statistici referitoare la salariu
        System.out.println("\nStatistici despre salarii:");
        var stats = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariu));
        System.out.println("Salariul mediu: " + stats.getAverage());
        System.out.println("Salariul minim: " + stats.getMin());
        System.out.println("Salariul maxim: " + stats.getMax());

        // 9. verificarea existentei unui angajat numit Ion
        System.out.println("\nExistenta unui angajat numit Ion:");
        angajati.stream().map(Angajat::getNume).filter(nume -> nume.startsWith("Ion")).findAny().ifPresentOrElse(
                        name -> System.out.println("Firma are cel puțin un Ion angajat."),
                        () -> System.out.println("Firma nu are nici un Ion angajat.")
                );

        // 10. numarul de persoane angajate vara anului precedent
        System.out.println("\nNumarul de persoane angajate vara anului precedent:");
        long angajatiVara = angajati.stream().filter(ang -> {
                    LocalDate data = ang.getDataAngajarii();
                    int an = anulCurent - 1;
                    return data.getYear() == an &&
                            (data.getMonth() == Month.JUNE || data.getMonth() == Month.JULY || data.getMonth() == Month.AUGUST);
                }).count();
        System.out.println("Angajati vara anului trecut: " + angajatiVara);
    }
}
