package fr.softeam.evenementparcoursintegration.dao;

import fr.softeam.evenementparcoursintegration.dto.EvenementPersonneParcoursIntegration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvenementPersonneParcoursIntegrationDao {

    private JdbcTemplate jdbcTemplate;

    public EvenementPersonneParcoursIntegrationDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void creerEvenementPersonneParcoursIntegreation(Integer idEvenement, String idPersonne){
        String sql = "insert into evenement_personne_parcours_integration (id_evenement, id_personne) values (?,?)";
        jdbcTemplate.update(sql,idEvenement, idPersonne);
    }

    public List<EvenementPersonneParcoursIntegration> getAllEvenementPersonneParcoursIntegration() {
        String sql = "select * from evenement_personne_parcours_integration";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            EvenementPersonneParcoursIntegration evenementPersonneParcoursIntegration = new EvenementPersonneParcoursIntegration();
            evenementPersonneParcoursIntegration.setIdEvenement(resultSet.getInt("id_evenement"));
            evenementPersonneParcoursIntegration.setIdPersonne(resultSet.getString("id_personne"));
            return evenementPersonneParcoursIntegration;
        });
    }


}
