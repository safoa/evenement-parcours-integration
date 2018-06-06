package fr.softeam.evenementparcoursintegration.service;

import fr.softeam.evenementparcoursintegration.config.YamlConfigEvenementParcoursIntegration;
import fr.softeam.evenementparcoursintegration.dao.EvenementPersonneParcoursIntegrationDao;
import fr.softeam.evenementparcoursintegration.dto.EvenementGenerique;
import fr.softeam.evenementparcoursintegration.dto.EvenementParcoursIntegration;
import fr.softeam.evenementparcoursintegration.dto.EvenementPersonneParcoursIntegration;
import fr.softeam.evenementparcoursintegration.dto.EvenementRappel;
import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EvenementParcoursIntegrationService {

    private YamlConfigEvenementParcoursIntegration yamlConfigEvenementParcoursIntegration;

    private EvenementGeneriqueAdapter evenementGeneriqueAdapter;

    private EvenementPersonneParcoursIntegrationDao evenementPersonneParcoursIntegrationDao;

    public EvenementParcoursIntegrationService(EvenementGeneriqueAdapter evenementGeneriqueAdapter,
                                               YamlConfigEvenementParcoursIntegration yamlConfigEvenementParcoursIntegration,
                                               EvenementPersonneParcoursIntegrationDao evenementPersonneParcoursIntegrationDao){
        this.evenementGeneriqueAdapter = evenementGeneriqueAdapter;
        this.yamlConfigEvenementParcoursIntegration = yamlConfigEvenementParcoursIntegration;
        this.evenementPersonneParcoursIntegrationDao = evenementPersonneParcoursIntegrationDao;
    }

    /**
     * Creation des evenements de type parcours d'integration (PI) pour la personne idPersonne
     *
     * @param idPersonne identifiant de la personne
     * @param dateArrivee date d'arrivée dans la société de la personne
     *
     */
    public List<EvenementGenerique> creationEvenementParcoursIntegration(String idPersonne, String dateArrivee) throws EvenementParcoursIntegrationException {
        List<EvenementGenerique> evenementGeneriqueList = new ArrayList<>();
        List<EvenementParcoursIntegration> evenementsParcoursIntegration = yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration();
        for (EvenementParcoursIntegration evenementParcoursIntegration : evenementsParcoursIntegration) {
            EvenementGenerique evenementGenerique = creationEvenementGeneriqueDepuisEvenementParcoursIntegration(evenementParcoursIntegration,dateArrivee);
            Integer idEvenement = evenementGeneriqueAdapter.ajouterEvenementGenerique(evenementGenerique);
            evenementGenerique.setIdEvenement(idEvenement);
            evenementPersonneParcoursIntegrationDao.creerEvenementPersonneParcoursIntegreation(idEvenement,idPersonne);
            evenementGeneriqueList.add(evenementGenerique);
        }
        return evenementGeneriqueList;
    }

    /**
     * Creation d'un evenement générique à partir d'un evenement parcours d'intégration
     *
     * @param evenementParcoursIntegration evenement à transformer en evenement generique
     * @param dateArrivee permet de trouver la date d'evenement
     * @return EvenementGenerique
     * @throws EvenementParcoursIntegrationException
     */
    private EvenementGenerique creationEvenementGeneriqueDepuisEvenementParcoursIntegration(
            EvenementParcoursIntegration evenementParcoursIntegration,String dateArrivee) throws EvenementParcoursIntegrationException {

        EvenementGenerique evenementGenerique = new EvenementGenerique();
        evenementGenerique.setNom(evenementParcoursIntegration.getNom());
        evenementGenerique.setDescription(evenementParcoursIntegration.getDescription());
        evenementGenerique.setDateEvenement(
                getDateEvenement(
                    dateArrivee,
                    evenementParcoursIntegration.getTypeDeclancheur(),
                    evenementParcoursIntegration.getValeurDeclancheur())
        );
        evenementGenerique.setType(evenementParcoursIntegration.getType());
        evenementGenerique.setCycle(evenementParcoursIntegration.getCycle());
        evenementGenerique.setTypeRecurrence(evenementParcoursIntegration.getTypeRecurrence());
        evenementGenerique.setValeurRecurrence(evenementParcoursIntegration.getValeurRecurrence());

        return evenementGenerique;
    }


    /**
     * Calcul de la date d'evenement en fonction d'un déclancheur (jour/semaine/mois/année)
     *
     * @param date date à laquel ajouter le déclancheur
     * @param typeDeclancheur (jour/semaine/mois/année) premiere lettre
     * @param valeurDeclancheur valeur à ajouter pour le calcul de la nouvelle date
     * @return identifiant de l'évènement générique
     */
    private String getDateEvenement(String date, String typeDeclancheur, Integer valeurDeclancheur) throws EvenementParcoursIntegrationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            LocalDate newlocalDate = null;
            switch (typeDeclancheur) {
                case "j":
                    newlocalDate = localDate.plusDays(valeurDeclancheur);
                    break;
                case "s":
                    newlocalDate = localDate.plusWeeks(valeurDeclancheur);
                    break;
                case "m":
                    newlocalDate = localDate.plusMonths(valeurDeclancheur);
                    break;
                case "a":
                    newlocalDate = localDate.plusYears(valeurDeclancheur);
                    break;
                default:
                    newlocalDate = localDate;
            }
            return newlocalDate.format(formatter);
        }catch (Exception e){
            throw new EvenementParcoursIntegrationException("Erreur dans la saisie de la date");
        }
    }

    /**
     * Permet de récupérer l'ensemble des évènements generique de type parcours intégration
     */
    public List<EvenementGenerique> recuperationEvenementsGeneriqueTypeParcoursIntegration() throws EvenementParcoursIntegrationException {
        List<EvenementParcoursIntegration> evenementParcoursIntegrationList =
                yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration();
        List<EvenementGenerique> evenementGeneriqueList =
                evenementGeneriqueAdapter.getEvenementGenerique();
        Set<String> nomEvenementParcoursIntegration =
                evenementParcoursIntegrationList.stream()
                        .map(EvenementParcoursIntegration::getNom)
                        .collect(Collectors.toSet());
        List<EvenementGenerique> evenementGeneriqueListParcoursIntegration =
                evenementGeneriqueList.stream()
                        .filter(e -> nomEvenementParcoursIntegration.contains(e.getNom()))
                        .collect(Collectors.toList());

        return evenementGeneriqueListParcoursIntegration;
    }

    /**
     * Permet de récupérer l'ensemble des évènements generique de chaque utilisateur
     */
    public Map<String, List<EvenementGenerique>> recuperationEvenementsParcoursIntegrationPersonne() throws EvenementParcoursIntegrationException {
        List<EvenementPersonneParcoursIntegration> evenementPersonneParcoursIntegrationList = evenementPersonneParcoursIntegrationDao.getAllEvenementPersonneParcoursIntegration();
        Map<String, List<Integer>> test = evenementPersonneParcoursIntegrationList.stream().collect(
                Collectors.groupingBy(EvenementPersonneParcoursIntegration::getIdPersonne,
                        Collectors.mapping(EvenementPersonneParcoursIntegration::getIdEvenement, Collectors.toList()))
        );
        List<EvenementGenerique> evenementGeneriqueList = recuperationEvenementsGeneriqueTypeParcoursIntegration();
        Map<Integer,EvenementGenerique> mapEvenementGenerique = evenementGeneriqueList
                .stream().collect(
                        Collectors.toMap(
                                EvenementGenerique::getIdEvenement,
                                Function.identity()
                        )
                );
        Map<String, List<EvenementGenerique>> evenementsParcoursIntegrationPersonne = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : test.entrySet()){
            evenementsParcoursIntegrationPersonne.put(entry.getKey(),new ArrayList<>());
            for (Integer idEvenement : entry.getValue()) {
                if(mapEvenementGenerique.get(idEvenement) != null){
                    evenementsParcoursIntegrationPersonne.get(entry.getKey()).add(mapEvenementGenerique.get(idEvenement));
                }
            }
        }
        return evenementsParcoursIntegrationPersonne;
    }

    /**
     * Permet de récupérer l'ensemble des évènements generiques pour lesquels il faut envoyer un rappel
     */
    public List<EvenementRappel> getEvenementsARappele()throws EvenementParcoursIntegrationException{
        List<EvenementParcoursIntegration> evenementParcoursIntegrationList = yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration();
        Map<String, Integer> evenementParcoursIntegrationMap =
                evenementParcoursIntegrationList.stream()
                .collect(Collectors.toMap(EvenementParcoursIntegration::getNom,EvenementParcoursIntegration::getNbJourAvantRappel));

        List<EvenementGenerique> evenementGeneriqueList = recuperationEvenementsGeneriqueTypeParcoursIntegration();
        List<EvenementGenerique> evenementGeneriqueARapeller = evenementGeneriqueList.stream().filter(
                e -> (LocalDate.parse(
                        e.getDateEvenement(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")).plusDays(-evenementParcoursIntegrationMap.get(e.getNom())).isEqual(LocalDate.now()
                ))
        ).collect(Collectors.toList());
        List<EvenementRappel> listeResultat = new ArrayList<>();
        for (EvenementGenerique evenementGenerique:evenementGeneriqueARapeller
             ) {
           Optional<EvenementParcoursIntegration> evenementParcoursIntegration =
                   evenementParcoursIntegrationList.stream().filter(e -> e.getNom().equals(evenementGenerique.getNom())).findFirst();
           if(evenementParcoursIntegration.isPresent()){
               EvenementRappel evenementRappel = new EvenementRappel();
               evenementRappel.setInformationEvenement(evenementGenerique);
               evenementRappel.setDestinataires(evenementParcoursIntegration.get().getDestinataireGroupe());
               listeResultat.add(evenementRappel);
           }
        }
        return listeResultat;
    }

    /**
     * Renvoi la liste des evenements génériques d'une personne
     * @param idPersonne identifiant de la personne
     * @return liste des evenements de la personne
     * @throws EvenementParcoursIntegrationException
     */
    public List<EvenementGenerique> getListEvenementByIdPersonne(String idPersonne)throws EvenementParcoursIntegrationException {
        Map<String, List<EvenementGenerique>> evenementGeneriqueList = new HashMap<>();
        evenementGeneriqueList = recuperationEvenementsParcoursIntegrationPersonne();
        List<EvenementParcoursIntegration> evenementParcoursIntegrationList =
                yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration();
        return evenementGeneriqueList.get(idPersonne);
    }

}
