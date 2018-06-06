package fr.softeam.evenementparcoursintegration.service;

import fr.softeam.evenementparcoursintegration.config.YamlConfigEvenementParcoursIntegration;
import fr.softeam.evenementparcoursintegration.dto.EvenementGenerique;
import fr.softeam.evenementparcoursintegration.dto.EvenementParcoursIntegration;
import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EvenementParcoursIntegrationServiceTest {

    @Mock
    private EvenementGeneriqueAdapter evenementGeneriqueAdapter;

    @Mock
    private YamlConfigEvenementParcoursIntegration yamlConfigEvenementParcoursIntegration;

    @InjectMocks
    private EvenementParcoursIntegrationService evenementParcoursIntegrationService;


    @Test
    public void when_call_recuperationEvenementsGeneriqueTypeParcoursIntegration_then_recuperation_evenementGeneriqueList() throws EvenementParcoursIntegrationException {
        //given
        EvenementGenerique evenementGenerique = new EvenementGenerique();
        evenementGenerique.setIdEvenement(1);
        evenementGenerique.setNom("Point d'accueil");
        EvenementGenerique evenementGenerique1 = new EvenementGenerique();
        evenementGenerique1.setIdEvenement(2);
        evenementGenerique1.setNom("test");
        List<EvenementGenerique> evenementGeneriqueList = new ArrayList<>();
        evenementGeneriqueList.add(evenementGenerique);
        evenementGeneriqueList.add(evenementGenerique1);

        EvenementParcoursIntegration evenementParcoursIntegration = new EvenementParcoursIntegration();
        evenementParcoursIntegration.setNom("Point d'accueil");
        List<EvenementParcoursIntegration> evenementParcoursIntegrationList = new ArrayList<>();
        evenementParcoursIntegrationList.add(evenementParcoursIntegration);

        Mockito.when(yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration())
                .thenReturn(evenementParcoursIntegrationList);

        Mockito.when(evenementGeneriqueAdapter.getEvenementGeneriqueAfterDate(Mockito.any()))
                .thenReturn(evenementGeneriqueList);
        //when & then
        Assertions.assertThat(evenementParcoursIntegrationService.recuperationEvenementsGeneriqueTypeParcoursIntegration().size()).isEqualTo(1);
    }

    @Test
    public void when_call_recuperationEvenementsARappele_then_recuperation_evenementGeneriqueList() throws EvenementParcoursIntegrationException {
        //given
        EvenementGenerique evenementGenerique = new EvenementGenerique();
        evenementGenerique.setIdEvenement(1);
        evenementGenerique.setNom("Point d'accueil");
        evenementGenerique.setDateEvenement("02/06/2018");
        EvenementGenerique evenementGenerique1 = new EvenementGenerique();
        evenementGenerique1.setIdEvenement(2);
        evenementGenerique1.setNom("test");
        List<EvenementGenerique> evenementGeneriqueList = new ArrayList<>();
        evenementGeneriqueList.add(evenementGenerique);
        evenementGeneriqueList.add(evenementGenerique1);

        EvenementParcoursIntegration evenementParcoursIntegration = new EvenementParcoursIntegration();
        evenementParcoursIntegration.setNom("Point d'accueil");
        evenementParcoursIntegration.setNbJourAvantRappel(1);
        List<EvenementParcoursIntegration> evenementParcoursIntegrationList = new ArrayList<>();
        evenementParcoursIntegrationList.add(evenementParcoursIntegration);

        Mockito.when(yamlConfigEvenementParcoursIntegration.getEvenementParcoursIntegration())
                .thenReturn(evenementParcoursIntegrationList);

        Mockito.when(evenementGeneriqueAdapter.getEvenementGeneriqueAfterDate(Mockito.any()))
                .thenReturn(evenementGeneriqueList);
        //when & then
        Assertions.assertThat(evenementParcoursIntegrationService.getEvenementsARappele().size()).isEqualTo(1);
    }

    @Test
    public void when_call_recuperationEvenementsParcoursIntegrationPersonne_then_evenements_de_chaque_personne(){

    }
}
