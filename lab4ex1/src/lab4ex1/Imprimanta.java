package lab4ex1;
enum ModTiparire{Color, Alb_negru}

public class Imprimanta extends Echipament{
    private int ppm;
    private int dpi;
    private int pCar;
    private ModTiparire modtiparire;

    public Imprimanta(String denumire, String nr_inv, double pret, String zona_mag,Stare stare,int ppm, int dpi, int pCar, ModTiparire modtiparire) {
        super(denumire ,nr_inv,pret,zona_mag,stare);
        this.ppm = ppm;
        this.dpi = dpi;
        this.pCar = pCar;
        this.modtiparire = modtiparire;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getpCar() {
        return pCar;
    }

    public void setpCar(int pCar) {
        this.pCar = pCar;
    }



    public ModTiparire getModtiparire() {
        return modtiparire;
    }

    public void setModtiparire(ModTiparire modtiparire) {
        this.modtiparire = modtiparire;
    }

    @Override
    public void afiseazaDetalii() {
        System.out.println(toString() +  "ppm=" + ppm + ", dpi=" + dpi + ", pCar=" + pCar + ", modtiparire=" + modtiparire + '}');
    }


}
