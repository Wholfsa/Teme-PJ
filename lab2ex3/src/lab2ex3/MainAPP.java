package lab2ex3;

import java.util.*;

public class MainAPP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // citire date pentru inserare
        System.out.println("Introduceti sirul initial:");
        StringBuilder sir = new StringBuilder(scanner.nextLine());

        System.out.println("Introduceti sirul de inserat:");
        String sirDeInserat = scanner.nextLine();

        System.out.println("Introduceti pozitia la care doriti sa inserati sirul:");
        int pozitieInserare = scanner.nextInt();
        scanner.nextLine(); // consuma newline ramas

        // validare pozitie si inserare
        if (pozitieInserare < 0 || pozitieInserare > sir.length()) {
            System.out.println("Pozitia de inserare este invalida.");
        } else {
            sir.insert(pozitieInserare, " " + sirDeInserat + " ");
            System.out.println("sirul dupa inserare: "  +  sir);
        }

        // citire date pentru stergere
        System.out.println("Introduceti pozitia de inceput pentru stergere:");
        int pozitieStergere = scanner.nextInt();

        System.out.println("Introduceti numarul de caractere de sters:");
        int numarCaractereStergere = scanner.nextInt();

        // validare pozitie si stergere
        if (pozitieStergere < 0 || pozitieStergere >= sir.length() || pozitieStergere + numarCaractereStergere > sir.length()) {
            System.out.println("Pozitia sau numarul de caractere pentru stergere este invalid.");
        } else {
            sir.delete(pozitieStergere, pozitieStergere + numarCaractereStergere);
            System.out.println("Sirul dupa stergere este: " + sir);
        }

        scanner.close();
    }
}
