package lab1Ex2;
import java.io.*;
import java.util.*;

public class MainAPP {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\Lab1Ex2\\src\\lab1Ex2\\in.txt";
        String outputFile = "C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\Lab1Ex2\\src\\lab1Ex2\\out.txt";

        try (Scanner scanner = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new File(outputFile))) {

            // lista pentru stocarea numerelor
            List<Integer> numere = new ArrayList<>();

            // citirea numerelor din fișier
            while (scanner.hasNextInt()) {
                numere.add(scanner.nextInt());
            }

            if (numere.isEmpty()) {
                System.out.println("Fisierul de intrare este gol.");
                writer.println("Fisierul de intrare este gol.");
                return;
            }

            // calcularea sumei,minimului,maximului si a mediei
            int suma = 0;
            int minim = Integer.MAX_VALUE;
            int maxim = Integer.MIN_VALUE;

            for (int num : numere) {
                suma += num;
                if (num < minim) minim = num;
                if (num > maxim) maxim = num;
            }

            double media = (double) suma / numere.size();

            // afisarea rezultatelor pe ecran
            System.out.println("Suma: " + suma);
            System.out.println("Media aritmetică: " + media);
            System.out.println("Minimul: " + minim);
            System.out.println("Maximul: " + maxim);

            // scrierea rezultatelor in fisierul de iesire
            writer.println("Suma: " + suma);
            writer.println("Media aritmetică: " + media);
            writer.println("Minimul: " + minim);
            writer.println("Maximul: " + maxim);

        } catch (FileNotFoundException e) {
            System.err.println("Fișierul " + inputFile + " nu a fost găsit.");
        } catch (IOException e) {
            System.err.println("A apărut o eroare la citirea sau scrierea fișierului.");
        }
    }
}

