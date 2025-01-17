package Lab2Ex1;

import java.util.*;
import java.io.*;

public class MainApp {
    public static void main(String[] args) {

        // citirea judetelor din fisier
        List<String> judeteList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\Lab2Ex1\\src\\Lab2Ex1\\judete_in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                judeteList.add(line.trim()); // adaugam judetele în lista, eliminand spatiile suplimentare
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        // conversia listei la tablou si sortarea acestuia
        String[] judete = judeteList.toArray(new String[0]);
        Arrays.sort(judete);

        // afisarea listei sortate
        System.out.println("Lista județelor sortate:");
        for (String judet : judete) {
            System.out.println(judet);
        }

        // citirea judetului introdus
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți numele județului pe care doriți să îl căutați: ");
        String judetCautat = scanner.nextLine().trim();

        // cautarea binara si afisarea judetului cautat
        int pozitie = Arrays.binarySearch(judete, judetCautat);
        if (pozitie >= 0) {
            System.out.println("Județul " + judetCautat + " se află pe poziția " + pozitie + " în lista sortată.");
        } else {
            System.out.println("Județul " + judetCautat + " nu a fost găsit în listă.");
        }

        scanner.close();
    }
}
