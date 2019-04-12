package nl.wderoode.app.model;

import java.time.LocalDate;
import java.util.Objects;

public class Klant {

    private Long id;

    private String naam;

    private String bsn;

    private LocalDate geboortedatum;

    public Klant() {
    }

    public Klant(String naam, String bsn, LocalDate geboortedatum) {
        this.naam = naam;
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klant klant = (Klant) o;
        return id.equals(klant.id) &&
                naam.equals(klant.naam) &&
                bsn.equals(klant.bsn) &&
                geboortedatum.equals(klant.geboortedatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam, bsn, geboortedatum);
    }

    @Override
    public String toString() {
        return "Klant{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", bsn='" + bsn + '\'' +
                ", geboortedatum=" + geboortedatum +
                '}';
    }
}
