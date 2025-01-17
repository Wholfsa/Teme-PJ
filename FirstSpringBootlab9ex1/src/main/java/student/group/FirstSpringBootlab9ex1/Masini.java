package student.group.FirstSpringBootlab9ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Masini {
    // @Autowired
    private String nr_matricol;
    //@Autowired
    private String marca;
    private int an_fabricatie;
    private int culoare;
    private int nr_km;

    public Masini(){}

    public Masini(String nr_matricol, String marca, int an_fabricatie, int culoare, int nr_km) {
        this.nr_matricol = nr_matricol;
        this.marca = marca;
        this.an_fabricatie = an_fabricatie;
        this.culoare = culoare;
        this.nr_km = nr_km;
    }

    public String getNr_matricol() {
        return nr_matricol;
    }

    public void setNr_matricol(String nr_matricol) {
        this.nr_matricol = nr_matricol;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAn_fabricatie() {
        return an_fabricatie;
    }

    public void setAn_fabricatie(int an_fabricatie) {
        this.an_fabricatie = an_fabricatie;
    }

    public int getCuloare() {
        return culoare;
    }

    public void setCuloare(int culoare) {
        this.culoare = culoare;
    }

    public int getNr_km() {
        return nr_km;
    }

    public void setNr_km(int nr_km) {
        this.nr_km = nr_km;
    }

    @Override
    public String toString() {
        return "Masini{" +
                "nr_matricol='" + nr_matricol + '\'' +
                ", marca='" + marca + '\'' +
                ", an_fabricatie=" + an_fabricatie +
                ", culoare=" + culoare +
                ", nr_km=" + nr_km +
                '}';
    }
}

