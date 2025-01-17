package lab3ex2;

import javax.management.monitor.StringMonitor;
import java.io.*;
import java.util.*;
import java.time.*;
import java.util.stream.Collectors;

public class MainAPP {
    public static void main(String[] args){
        List<Produs> produse=new ArrayList<>();
        //citirea produselor din lista
        try(BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\wholf\\IdeaProjects\\WorkSpacePJ\\lab3ex2\\src\\lab3ex2\\produse.csv"))){
            String line;
            while((line= br.readLine())!=null){
                String[] parts=line.split(",");
                String denumire=parts[0].trim();
                double  pret=Double.parseDouble(parts[1].trim());
                int cantitate=Integer.parseInt(parts[2].trim());
                LocalDate dataExpirare=LocalDate.parse(parts[3].trim());
                produse.add(new Produs(denumire,pret,cantitate,dataExpirare));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: "+e.getMessage());
            return;
        }
        Scanner scanner=new Scanner(System.in);
        //meniu
        while(true){
            System.out.println("\nMeniu");
            System.out.println("1.Afisarea produselor");
            System.out.println("2.Afisare produse expirate");
            System.out.println("3.Vanzarea unui produs");
            System.out.println("4.Afisarea produselor cu pretul minim");
            System.out.println("5.Salvarea produselor cu cantitate mica intr-un fisier");
            System.out.println("6.Iesire");
            System.out.println("Introduceti optiunea dvs.:");

            int opt=scanner.nextInt();
            scanner.nextLine();
            switch (opt){
                case 1:
                    System.out.println("Toate produsele:");
                produse.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Produse expirate:");
                    LocalDate azi=LocalDate.now();
                    produse.stream().filter(p->p.getDataExpirare().isBefore(azi)).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Introduceti denumirea produsului: ");
                String denumireVanzare= scanner.nextLine();
                    System.out.println("Introduceti cantitatea de vandut: ");
                    int cantitateVanzare= scanner.nextInt();
                    //findFirst returneaza Optional, e mai rapid decat verificarea manuala
                    Optional<Produs> produsDeVandut=produse.stream().filter(p->p.getDenumire().equalsIgnoreCase(denumireVanzare)).findFirst();

                    if(produsDeVandut.isPresent()) {
                        Produs produs = produsDeVandut.get();
                        if (produs.getCantitate() >= cantitateVanzare) {
                            produs.setCantitate(produs.getCantitate() - cantitateVanzare);
                            Produs.adaugaIncasari(produs.getPret() * cantitateVanzare);
                            System.out.println("Produsul a fost vandut");

                            if (produs.getCantitate() == 0) {
                                produse.remove(produs);
                                System.out.println("Nu mai exista produsul acesta!");
                            }
                        } else {
                            System.out.println("Stoc insuficient");
                        }
                    }else{
                        System.out.println("Produsul nu exista");
                    }
                    break;
                case 4:
                    //transforma fiecare element din flux într-un double utilizand o functie specificata->
                    //rezultatul este un flux de tip DoubleStream, care contine doar valorile numerice extrase

                    double pretMinim = produse.stream().mapToDouble(Produs::getPret).min().orElse(0);

                    System.out.println("Produse cu pretul minim:");
                    //identificam toate produsele cu pret minim si le afiseaza pe fiecare pe linie noua
                    produse.stream().filter(p -> p.getPret() == pretMinim).forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Introduceti valoarea cantitatii limita: ");
                    int limita = scanner.nextInt();

                    List<Produs> produseFiltrate = produse.stream().filter(p -> p.getCantitate() < limita).collect(Collectors.toList());
                    //se aplica un filtru pe fluxul de produse pentru a pastra doar acele produse a caror cantitate este mai mica decat valoarea variabilei limita
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("produse_filtrate.csv"))) {
                        for (Produs produs : produseFiltrate) {
                            bw.write(String.format("%s,%f,%d,%s%n",
                                    produs.getDenumire(), produs.getPret(), produs.getCantitate(), produs.getDataExpirare()));
                        }
                        System.out.println("Produsele au fost salvate în fișierul produse_filtrate.csv.");
                    } catch (IOException e) {
                        System.err.println("Eroare la scrierea fișierului: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Zi buna");
                    return; //return pentru a opri rularea dupa introducerea optiunii 6
                default:
                    System.out.println("Optiune invalida! \nAlegeti alta optiune");
            }
        }
    }
}
