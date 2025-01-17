package lab3ex1;

import java.io.*;
import java.util.*;

public class MainAPP {
    public static void main(String[] args) {
        // lista pentru stocarea parabolelor
        List<Parabola> parabole = new ArrayList<>();

        // citirea datelor din fișier
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\lab3ex1\\src\\lab3ex1\\parabole.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coef = line.split(" ");
                int a = Integer.parseInt(coef[0]);
                int b = Integer.parseInt(coef[1]);
                int c = Integer.parseInt(coef[2]);
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        // afisarea informatiilor despre fiecare parabola
        for (Parabola parabola : parabole) {
            double[] VfParabola = parabola.getVfParabola();
            System.out.printf("%s, Varful parabolei: (%.2f, %.2f)%n", parabola, VfParabola[0], VfParabola[1]);
        }

        // calcularea si afisarea mijlocului segmentului si lungimii daca exista cel putin doua parabole
        if (parabole.size() >= 2) {
            double[] v1 = parabole.get(0).getVfParabola();
            double[] v2 = parabole.get(1).getVfParabola();

            double[] mijlocSegment = Parabola.getMijloculSegmentului(v1, v2);
            double lungimeSegment = Parabola.getLungimeaSegmentului(v1, v2);

            System.out.printf("Mijlocul segmentului: (%.2f, %.2f)%n", mijlocSegment[0], mijlocSegment[1]);
            System.out.printf("Lungimea segmentului: %.2f%n", lungimeSegment);
        }
    }
}
