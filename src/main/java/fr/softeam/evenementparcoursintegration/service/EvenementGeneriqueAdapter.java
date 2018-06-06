package fr.softeam.evenementparcoursintegration.service;

import fr.softeam.evenementparcoursintegration.dto.EvenementGenerique;
import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.List;

@Service
public class EvenementGeneriqueAdapter {

    private RestTemplate restTemplate;

    public EvenementGeneriqueAdapter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${EVENEMENT_GENERIQUE_URI}")
    private String EVENEMENT_GENERIQUE_URI;

    @Value("${CREATION_EVENEMENT_GENERIQUE_URI}")
    private String CREATION_EVENEMENT_GENERIQUE_URI;

    /**
     * Ajouter un evenement générique
     *
     * @param evenementGenerique evenement générique à ajouter en base
     * @return identifiant de l'évènement générique
     */
    public int ajouterEvenementGenerique(EvenementGenerique evenementGenerique) throws EvenementParcoursIntegrationException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EvenementGenerique> entity = new HttpEntity<>(evenementGenerique, headers);
            EvenementGenerique responseEntity =
                    restTemplate.postForObject(EVENEMENT_GENERIQUE_URI + CREATION_EVENEMENT_GENERIQUE_URI,
                            entity,
                            EvenementGenerique.class);
            return responseEntity.getIdEvenement();
        }catch(HttpClientErrorException  exception){
            throw new EvenementParcoursIntegrationException(exception.getResponseBodyAsString());
        }
    }

    /**
     * Récupération de la liste des évènements génériques
     *
     * @return liste des évènements génériques
     */
    public List<EvenementGenerique> getEvenementGenerique() throws EvenementParcoursIntegrationException{
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>("", headers);
            ResponseEntity<List<EvenementGenerique>> responseEntity =
                    restTemplate.exchange(EVENEMENT_GENERIQUE_URI + CREATION_EVENEMENT_GENERIQUE_URI,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<EvenementGenerique>>(){});
            return responseEntity.getBody();
        }catch(HttpClientErrorException  exception){
            throw new EvenementParcoursIntegrationException(exception.getResponseBodyAsString());
        }
    }

    /**
     * Récupération de la liste des évènements génériques dans la date d'evènement est après la date limite
     *
     * @param date date limite
     *
     * @return liste des évènements générique
     */
    public List<EvenementGenerique> getEvenementGeneriqueAfterDate(String date) throws EvenementParcoursIntegrationException{
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>("", headers);
            ResponseEntity<List<EvenementGenerique>> responseEntity =
                    restTemplate.exchange(EVENEMENT_GENERIQUE_URI + CREATION_EVENEMENT_GENERIQUE_URI+"/limite?limite="+date,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<EvenementGenerique>>(){});
            return responseEntity.getBody();
        }catch(HttpClientErrorException  exception){
            throw new EvenementParcoursIntegrationException(exception.getResponseBodyAsString());
        }
    }
}
