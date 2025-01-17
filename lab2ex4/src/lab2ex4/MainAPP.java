package lab2ex4;

import java.util.*;
import java.io.*;
import java.time.*;

public class MainAPP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Persoana> persoane = new ArrayList<>();

        System.out.print("Introduceți numarul de persoane: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consumam linia ramasa

        for (int i = 0; i < n; i++) {
            System.out.printf("Introduceți numele persoanei %d: ", i + 1);
            String nume = scanner.nextLine();

            String cnp;
            while (true) {
                System.out.printf("Introduceti CNP-ul persoanei %d: ", i + 1);
                cnp = scanner.nextLine();
                if (esteCnpValid(cnp)) {
                    break;
                } else {
                    System.out.println("CNP invalid. Incercati din nou.");
                }
            }

            persoane.add(new Persoana(nume, cnp));
        }

        System.out.println("\nInformatiile introduse:");
        for (Persoana persoana : persoane) {
            System.out.println(persoana);
        }
    }

    private static boolean esteCnpValid(String cnp) {
        if (cnp.length() != 13) {  //trebuie sa aiba 13 cifre
            return false;
        }

        if (!cnp.matches("\\d+")) {
            return false;
        }

        char primaCifra = cnp.charAt(0);
        if (primaCifra != '1' && primaCifra != '2' && primaCifra != '5' && primaCifra != '6') { //trebuie sa inceapa cu 1,2,5 sau 6
            return false;
        }

        return verificaCifraControl(cnp);
    }

    private static boolean verificaCifraControl(String cnp) {
        int[] coeficienti = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        int suma = 0;

        for (int i = 0; i < 12; i++) {
            suma += (cnp.charAt(i) - '0') * coeficienti[i];   //-'0' converteste de la char la int
        }

        int cifraControlCalculata = suma % 11;
        if (cifraControlCalculata == 10) {
            cifraControlCalculata = 1;
        }

        int cifraControlCnp = cnp.charAt(12) - '0';
        return cifraControlCalculata == cifraControlCnp;
    }
}

