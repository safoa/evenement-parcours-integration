package fr.softeam.evenementparcoursintegration.service;

import fr.softeam.evenementparcoursintegration.dto.EvenementGenerique;
import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpStatusCodeException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EvenementGeneriqueAdapterTest {
    @Autowired
    private EvenementGeneriqueAdapter evenementGeneriqueAdapter;

    @Test
    public void testGetEvenementGenerique() throws EvenementParcoursIntegrationException {
        Assertions.assertThat(evenementGeneriqueAdapter.getEvenementGenerique().size()).isEqualTo(4);
    }

    @Test
    public void testGetEvenementGeneriqueAfterDate() throws EvenementParcoursIntegrationException {
        Assertions.assertThat(evenementGeneriqueAdapter.getEvenementGeneriqueAfterDate("20180531").size()).isEqualTo(4);
    }

    @Test
    public void ajouterEvenementGenerique() throws EvenementParcoursIntegrationException {
        EvenementGenerique evenementGenerique = new EvenementGenerique();
        evenementGenerique.setNom("test");
        evenementGenerique.setDateEvenement("04/06/2018");
        Assertions.assertThat(evenementGeneriqueAdapter.ajouterEvenementGenerique(evenementGenerique)).isNotNull();

    }

}
