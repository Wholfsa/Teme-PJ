package Lab1Ex1;
import java.util.*;
public class MainAPP {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        //citirea lungimii dreptunghiului
        System.out.print("Introduceti lungimea dreptunghiului: ");
        double lungime=scanner.nextDouble();
        //citim latimii dreptunghiului
        System.out.print("Introduceti latimea dreptunghiului: ");
        double latime=scanner.nextDouble();

        //calcularea perimetrului
        double perimetru=2*(lungime+latime);

        //calcularea ariei
        double aria=lungime*latime;

        //afisare
        System.out.println("Perimetrul dreptunghiului este: "+perimetru);
        System.out.println("Aria dreptunghiului este: "+aria);

        scanner.close();
    }
}
