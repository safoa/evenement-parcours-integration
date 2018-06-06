package fr.softeam.evenementparcoursintegration.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Personne {

    @NotNull
    private String idPersonne;

    @NotNull
    @Size(min=10,max=10)
    private String dateArrivee;

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Personne() {
    }
}
