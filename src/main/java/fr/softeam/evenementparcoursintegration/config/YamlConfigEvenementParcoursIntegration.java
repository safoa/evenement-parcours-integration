package fr.softeam.evenementparcoursintegration.config;

import fr.softeam.evenementparcoursintegration.dto.EvenementParcoursIntegration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties
public class YamlConfigEvenementParcoursIntegration {

    private List<EvenementParcoursIntegration> evenementParcoursIntegration;


    public YamlConfigEvenementParcoursIntegration(List<EvenementParcoursIntegration> evenementParcoursIntegration) {
        this.evenementParcoursIntegration = evenementParcoursIntegration;
    }

    public YamlConfigEvenementParcoursIntegration() {
    }

    public List<EvenementParcoursIntegration> getEvenementParcoursIntegration() {
        return evenementParcoursIntegration;
    }

    public void setEvenementParcoursIntegration(List<EvenementParcoursIntegration> evenementParcoursIntegration) {
        this.evenementParcoursIntegration = evenementParcoursIntegration;
    }
}
