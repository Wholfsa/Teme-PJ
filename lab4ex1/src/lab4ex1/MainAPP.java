package lab4ex1;

import java.io.*;
import java.util.*;

public class MainAPP {
    private static List<Echipament> echipamente = new ArrayList<>();

    public static void afisMeniu() {
        System.out.println("\nMeniu:");
        System.out.println("1. Afișează toate echipamentele");
        System.out.println("2. Afișează imprimantele");
        System.out.println("3. Afișează copiatoarele");
        System.out.println("4. Afișează sistemele de calcul");
        System.out.println("5. Modifică starea unui echipament");
        System.out.println("6. Setează un mod de tipărire pentru o imprimantă");
        System.out.println("7. Setează un format de copiere pentru un copiator");
        System.out.println("8. Instalează un sistem de operare pentru un sistem de calcul");
        System.out.println("9. Afișează echipamentele vândute");
        System.out.println("10. Salvează echipamentele în fișier");
        System.out.println("0. Ieșire");
        System.out.print("Alegeți opțiunea: ");
    }

    public static void afisToateEchip() {
        for (Echipament e : echipamente)
            e.afiseazaDetalii();
    }


    private static void afisImprimantele() {
        for (Echipament e : echipamente) {
            if (e instanceof Copiator) {
                e.afiseazaDetalii();
            }
        }
    }

    private static void afisCopiatoarele(){
        for(Echipament e: echipamente){
            if(e instanceof Copiator){
                e.afiseazaDetalii();
            }
        }
    }

    private static void afiseazaSistemeCalcul() {
        for (Echipament e : echipamente) {
            if (e instanceof SistDeCalcul) {
                e.afiseazaDetalii();
            }
        }
    }


    private static void modificaStareEchip(Scanner scanner){
        System.out.print("Introduceti numarul de inventar al echipamentului: ");
        String nrInv = scanner.nextLine();
        Echipament echipament = cautaEchipament(nrInv);
        if (echipament != null) {
            System.out.print("Introduceti noua stare (Achizitionat, Expus, Vandut): ");
            String stare = scanner.nextLine();
            echipament.setStare(Stare.valueOf(stare.toUpperCase()));
        } else {
            System.out.println("Echipament nespecificat.");
        }

    }
    private static Echipament cautaEchipament(String nrInv) {
        for (Echipament e : echipamente) {
            if (e.nr_inv.equals(nrInv)) {
                return e;
            }
        }
        return null;
    }

    private static void seteazaModTiparire(Scanner scanner){
        System.out.print("Introduceti nr de inventar al imprimantei: ");
        String nrInv=scanner.nextLine();
        Echipament echipament=cautaEchipament(nrInv);
        if(echipament instanceof Imprimanta){
            System.out.print("introduceti noul mod de tiparire: ");
            String mod=scanner.nextLine();
            ((Imprimanta)echipament).setModtiparire(ModTiparire.valueOf(mod.toUpperCase()));
        }else{
            System.out.println("Nu este o imprimanta");
        }
    }

    private static void seteazaFormatCopiere(Scanner scanner) {
        System.out.print("Introduceți numărul de inventar al copiatorului: ");
        String nrInv = scanner.nextLine();
        Echipament echipament = cautaEchipament(nrInv);
        if (echipament instanceof Copiator) {
            System.out.print("Introduceți noul format de copiere (A3, A4): ");
            String format = scanner.nextLine();
            ((Copiator) echipament).setFormatCopiere(FormatCopiere.valueOf(format.toUpperCase()));
        } else {
            System.out.println("Nu este un copiator.");
        }
    }

    private static void instaleazaSistemOperare(Scanner scanner) {
        System.out.print("Introduceți numărul de inventar al sistemului de calcul: ");
        String nrInv = scanner.nextLine();
        Echipament echipament = cautaEchipament(nrInv);
        if (echipament instanceof SistDeCalcul) {
            System.out.print("Introduceți noul sistem de operare (WINDOWS, LINUX): ");
            String so = scanner.nextLine();
            ((SistDeCalcul) echipament).setSistemOperare(SistemOperare.valueOf(so.toUpperCase()));
        } else {
            System.out.println("Nu este un sistem de calcul.");
        }
    }

    private static void afiseazaEchipamenteVandute() {
        for (Echipament e : echipamente) {
            if (e.getStare() == Stare.Vandut) {
                e.afiseazaDetalii();
            }
        }
    }

    // Metode pentru serializare și deserializare

    private static void salveazaEchipamenteInFisier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("echip.bin"))) {
            oos.writeObject(echipamente);
            System.out.println("Echipamente salvate cu succes.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void incarcaEchipamenteDinFisier() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\lab4ex1\\src\\lab4ex1\\echipamente.txt"))) {
            echipamente = (List<Echipament>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        incarcaEchipamenteDinFisier();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            afisMeniu();
            int opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 1:afisToateEchip();
                    break;
                case 2:afisImprimantele();
                    break;
                case 3:afisCopiatoarele();
                    break;
                case 4:afiseazaSistemeCalcul();
                    break;
                case 5:modificaStareEchip(scanner);
                    break;
                case 6:seteazaModTiparire(scanner);
                    break;
                case 7:seteazaFormatCopiere(scanner);
                    break;
                case 8:instaleazaSistemOperare(scanner);
                    break;
                case 9:afiseazaEchipamenteVandute();
                    break;
                case 10:salveazaEchipamenteInFisier();
                    break;
                case 0:
                    System.out.println("Zi buna");
                    return; //iesire din program
                default:
                    System.out.println("Optiune invalida! \n Incearca alta optiune");
            }
        }
    }
}


