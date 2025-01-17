package Lab1Ex4;
import java.util.*;

public class MainAPP {
    public static void main(String[] args){
        Random random = new Random();

        // generarea a doua numere aleatoare intre 1 si 30
        int numar1 = random.nextInt(30) + 1; // nextInt(30) genereaza un numar tntre 0 si 29, deci adaugam 1
        int numar2 = random.nextInt(30) + 1;

        // afisarea numerelor generate
        System.out.println("Numere generate: ");
        System.out.println("Numar 1: " + numar1);
        System.out.println("Numar 2: " + numar2);

        // calcularea CMMDC folosind algoritmul lui Euclid
        int cmmdc = calculeazaCmmdc(numar1, numar2);

        // afisarea rezultatului
        System.out.println("CMMDC-ul celor două numere este: " + cmmdc);
    }

    // calcularea CMMDC utilizand algoritmul lui Euclid
    public static int calculeazaCmmdc(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
