package lab1ex5;
import java.util.*;

public class mainApp {
    public static void main(String[] args){
        Random random = new Random();

        // generarea unui numar aleatoriu intre 0 si 20
        int numar = random.nextInt(21); // nextInt(21) genereaza valori intre 0 si 20

        // afisarea numarului generat
        System.out.println("Numarul generat este: " + numar);

        // verificarea daca numarul apartine sirului lui Fibonacci
        if (esteFibonacci(numar)) {
            System.out.println("Numarul " + numar + " apartine sirului lui Fibonacci.");
        } else {
            System.out.println("Numarul " + numar + " NU apartine sirului lui Fibonacci.");
        }
    }

    // verificarea apartenentei la sirul lui Fibonacci
    public static boolean esteFibonacci(int n) {
        // (un numar apartine sirului Fibonacci daca 5*n^2 + 4 sau 5*n^2 - 4 este un patrat perfect
        return estePatratPerfect(5 * n * n + 4) || estePatratPerfect(5 * n * n - 4);
    }

    // verificarea unui patrat perfect
    public static boolean estePatratPerfect(int x) {
        int sqrt = (int) Math.sqrt(x);
        return sqrt * sqrt == x;
    }
}
