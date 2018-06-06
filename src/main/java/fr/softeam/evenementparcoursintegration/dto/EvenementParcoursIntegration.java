package fr.softeam.evenementparcoursintegration.dto;

import java.util.List;

public class EvenementParcoursIntegration {
    private String nom;
    private String description;
    private String typeDeclancheur;
    private Integer valeurDeclancheur;
    private String type;
    private boolean cycle;
    private String typeRecurrence;
    private Integer valeurRecurrence;
    private Integer nbJourAvantRappel;
    private List<String> roles;

    public EvenementParcoursIntegration() {
    }

    public EvenementParcoursIntegration(String nom, String description, String typeDeclancheur, Integer valeurDeclancheur, String type, boolean cycle, String typeRecurrence, Integer valeurRecurrence, Integer nbJourAvantRappel, List<String> roles) {
        this.nom = nom;
        this.description = description;
        this.typeDeclancheur = typeDeclancheur;
        this.valeurDeclancheur = valeurDeclancheur;
        this.type = type;
        this.cycle = cycle;
        this.typeRecurrence = typeRecurrence;
        this.valeurRecurrence = valeurRecurrence;
        this.nbJourAvantRappel = nbJourAvantRappel;
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeDeclancheur() {
        return typeDeclancheur;
    }

    public void setTypeDeclancheur(String typeDeclancheur) {
        this.typeDeclancheur = typeDeclancheur;
    }

    public Integer getValeurDeclancheur() {
        return valeurDeclancheur;
    }

    public void setValeurDeclancheur(Integer valeurDeclancheur) {
        this.valeurDeclancheur = valeurDeclancheur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public String getTypeRecurrence() {
        return typeRecurrence;
    }

    public void setTypeRecurrence(String typeRecurrence) {
        this.typeRecurrence = typeRecurrence;
    }

    public Integer getValeurRecurrence() {
        return valeurRecurrence;
    }

    public void setValeurRecurrence(Integer valeurRecurrence) {
        this.valeurRecurrence = valeurRecurrence;
    }

    public Integer getNbJourAvantRappel() {
        return nbJourAvantRappel;
    }

    public void setNbJourAvantRappel(Integer nbJourAvantRappel) {
        this.nbJourAvantRappel = nbJourAvantRappel;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


}
