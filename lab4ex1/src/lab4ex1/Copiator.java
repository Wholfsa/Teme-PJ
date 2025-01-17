package lab4ex1;

enum FormatCopiere{A3, A4}

public class Copiator extends Echipament{
    private int pTon;
    private FormatCopiere formatCopiere;

    public Copiator(String denumire, String nr_inv, double pret, String zona_mag, Stare stare,int pTon, FormatCopiere formatCopiere) {
        super(denumire, nr_inv, pret,zona_mag, stare);
        this.pTon = pTon;
        this.formatCopiere = formatCopiere;
    }

    public int getpTon() {
        return pTon;
    }

    public void setpTon(int pTon) {
        this.pTon = pTon;
    }

    public FormatCopiere getFormatCopiere() {
        return formatCopiere;
    }

    public void setFormatCopiere(FormatCopiere formatCopiere) {
        this.formatCopiere = formatCopiere;
    }

    @Override
    public void afiseazaDetalii() {
        System.out.println(toString() + "pTon=" + pTon + ", formatCopiere=" + formatCopiere + '}');
    }
}
