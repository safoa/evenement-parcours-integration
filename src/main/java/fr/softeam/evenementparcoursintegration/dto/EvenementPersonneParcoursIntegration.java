package fr.softeam.evenementparcoursintegration.dto;

import javax.validation.constraints.NotNull;

public class EvenementPersonneParcoursIntegration {

    @NotNull
    private String idPersonne;

    @NotNull
    private int idEvenement;

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }
}
