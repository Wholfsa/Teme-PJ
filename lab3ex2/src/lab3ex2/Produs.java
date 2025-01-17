package lab3ex2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirare;
    private static double incasari=0;

    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirare = dataExpirare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public static double getIncasari() {
        return incasari;
    }

    public static void setIncasari(double incasari) {
        Produs.incasari = incasari;
    }
    public static void adaugaIncasari(double suma) {
        incasari += suma;
    }

    @Override
    public String toString() {
        return "Produs{" + "denumire='" + denumire + '\'' + ", pret=" + pret + ", cantitate=" + cantitate + ", dataExpirare=" + dataExpirare + '}';
    }
}
