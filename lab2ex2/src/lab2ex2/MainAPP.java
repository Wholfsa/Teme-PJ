package lab2ex2;

import java.util.*;
import java.io.*;

public class MainAPP {
        public static void main(String[] args) {
            // configurari
            String inputFile = "C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\lab2ex2\\src\\lab2ex2\\cantec_in.txt";
            String outputFile = "cantec_out.txt";
            String grupareLitere = "ow"; // grupare aleasa
            Random random = new Random();

            // citirea versurilor din fisierul de intrare
            List<Vers> versuri = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    versuri.add(new Vers(line));
                }
            } catch (IOException e) {
                System.err.println("Eroare la citirea fisierului: " + e.getMessage());
                return;
            }

            // scrierea fisierului de iesire
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                for (Vers vers : versuri) {
                    String originalText = vers.getText();
                    int numarCuvinte = vers.numarCuvinte();
                    int numarVocale = vers.numarVocale();

                    // verificare pentru steluta
                    String lineOutput = originalText;
                    if (vers.seIncheieCu(grupareLitere)) {
                        lineOutput += " *";
                    }

                    // verificare pentru majuscule
                    if (random.nextDouble() < 0.1) {
                        lineOutput = lineOutput.toUpperCase();
                    }

                    // adaugare informatii suplimentare
                    lineOutput += String.format(" | Cuvinte: %d | Vocale: %d", numarCuvinte, numarVocale);

                    // scrierea randului in fisier
                    bw.write(lineOutput);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("Eroare la scrierea fisierului: " + e.getMessage());
            }

            System.out.println("Fisierul de iesire a fost generat cu succes: " + outputFile);
        }
   // }
}
