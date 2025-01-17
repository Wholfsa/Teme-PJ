package lab2ex4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Persoana {
    private String nume;
    private String cnp;

    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta() {
        // extragem anul, luna si ziua din CNP
        int an = Integer.parseInt(cnp.substring(1, 3));
        int luna = Integer.parseInt(cnp.substring(3, 5));
        int zi = Integer.parseInt(cnp.substring(5, 7));

        // determinam secolul in functie de prima cifra
        int secol = switch (cnp.charAt(0)) {
            case '1', '2' -> 1900;
            case '5', '6' -> 2000;
            default -> throw new IllegalArgumentException("CNP invalid");
        };

        an += secol;

        // calculam varsta
        LocalDate birthDate = LocalDate.of(an, luna, zi);
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d ani", nume, cnp, getVarsta());
    }
}