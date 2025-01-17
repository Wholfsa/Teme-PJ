package lab4ex1;

enum SistemOperare {Windows, Linux}

public class SistDeCalcul extends Echipament{
    private String tipMon;
    private double vitProc;
    private int cHdd;
    private SistemOperare sistemOperare;

    public SistDeCalcul(String denumire, String nr_inv, double pret, String zona_mag, Stare stare, String tipMon, double vitProc, int cHdd, SistemOperare sistemOperare) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.tipMon = tipMon;
        this.vitProc = vitProc;
        this.cHdd = cHdd;
        this.sistemOperare = sistemOperare;
    }

    public String getTipMon() {
        return tipMon;
    }

    public void setTipMon(String tipMon) {
        this.tipMon = tipMon;
    }

    public double getVitProc() {
        return vitProc;
    }

    public void setVitProc(double vitProc) {
        this.vitProc = vitProc;
    }

    public int getcHdd() {
        return cHdd;
    }

    public void setcHdd(int cHdd) {
        this.cHdd = cHdd;
    }

    public SistemOperare getSistemOperare() {
        return sistemOperare;
    }

    public void setSistemOperare(SistemOperare sistemOperare) {
        this.sistemOperare = sistemOperare;
    }

    @Override
    public void afiseazaDetalii() {
        System.out.println(toString()+ "tip Monitor='" + tipMon + '\'' + ", viteza Procesor=" + vitProc + ", GHz, Capacitate HDD=" + cHdd + ", sistem de Operare=" + sistemOperare + '}');
    }

}
