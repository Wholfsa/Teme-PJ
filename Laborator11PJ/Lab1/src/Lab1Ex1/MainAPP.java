package Lab1Ex1;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        //Citim lungimea dreptunghiului
        System.out.print("Introduceti lungimea dreptunghiului: ");
        double lungime=scanner.nextDouble();
        //Citim latimea dreptunghiului
        System.out.print("Introduceti latimea dreptunghiului: ");
        double latime=scanner.nextDouble();

        //Calcul perimetru
        double perimetru=2*(lungime+latime);

        //Calculul ariei
        double aria=lungime*latime;

        //Afisare
        System.out.println("Perimetrul dreptunghiului este: "+perimetru);
        System.out.println("Aria dreptunghiului este: "+aria);

        scanner.close();
    }
}
