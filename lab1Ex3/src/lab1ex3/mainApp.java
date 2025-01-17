package lab1ex3;
import java.util.*;

public class mainApp {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        // citirea numarului de la tastatura
        System.out.print("Introduceti un numar natural: ");
        int n = scanner.nextInt();

        // validare pentru numere naturale
        if (n <= 0) {
            System.out.println("Numarul trebuie sa fie un numar natural pozitiv.");
            return;
        }

        // variabila pentru a verifica dacă numarul este prim
        boolean estePrim = true;

        System.out.println("Divizorii numarului " + n + " sunt:");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                if (i != 1 && i != n) {
                    estePrim = false; // Daca are mai multi divizori in afara de 1 si el insusi, nu este numar prim
                }
            }
        }

        System.out.println();

        // verificare daca numarul este prim
        if (estePrim && n > 1) {
            System.out.println("Numarul " + n + " este prim.");
        } else {
            System.out.println("Numarul " + n + " nu este prim.");
        }

        scanner.close();
    }
}
