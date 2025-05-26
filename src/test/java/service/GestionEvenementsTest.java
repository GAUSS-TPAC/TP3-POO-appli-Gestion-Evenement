package service;

import exception.EvenementDejaExistantException;
import modele.Conference;
import modele.Evenement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GestionEvenementsTest {
    private GestionEvenements gestion;
    @BeforeEach
    public void setup(){
        gestion = GestionEvenements.getInstance();

        gestion.getEvenements().clear();
    }

    @Test
    @DisplayName("test ajouter Evenement Deja Present")
    public void testAjouterEvenementDejaPresent() {
        Evenement event =new Conference("1", "Conférence Test", LocalDateTime.now(), "Paris", 2, "IA");
        gestion.ajouterEvenement(event.getId(),event);

        assertTrue(gestion.getEvenements().containsKey(event.getId()));
        assertEquals(event, gestion.getEvenements().get(event.getId()));
    }

    @Test
    @DisplayName("test supprimer Evenement existant")
    public void testSupprimerEvenement() {
        Evenement event =new Conference("1", "Conférence Test", LocalDateTime.now(), "Paris", 2, "IA");
        gestion.ajouterEvenement(event.getId(), event);
        gestion.supprimerEvenement(event.getId());
        assertEquals(0, gestion.getEvenements().size());

    }

    @Test
    @DisplayName("test ajouter evenement doublon")
    public void testAjouterEvenementDoublon() {
        Evenement event =new Conference("1", "Conférence Test", LocalDateTime.now(), "Paris", 2, "IA");
        gestion.ajouterEvenement(event.getId(), event);

        assertThrows(EvenementDejaExistantException.class, () ->{
            gestion.ajouterEvenement(event.getId(), event);
        });
    }

    @Test
    @DisplayName("test sauvegarde des evenements")
    public void testSauvegardeDesEvenements() {
        String chemin="evenementsTest.json";
        Evenement event =new Conference("1", "Conférence Test", LocalDateTime.now(), "Paris", 2, "IA");
        gestion.ajouterEvenement(event.getId(), event);

        gestion.sauvegarder(chemin);

        File file = new File(chemin);
        assertTrue(file.exists());
        assertTrue(file.length()>0);

        file.delete();
    }

    @Test
    @DisplayName("test chargement des evenements")
    public void testChargementDesEvenements()
    {
        String chemin = "evenements_test_chargement.json";
        Conference conf = new Conference("2", "Conférence Chargement", LocalDateTime.now(), "Lyon", 50, "Test");
        gestion.ajouterEvenement(conf.getId(), conf);

        //gestion.getEvenements().clear();
        gestion.sauvegarder(chemin);

        gestion.charger(chemin);
        assertTrue(gestion.getEvenements().containsKey(conf.getId()));
        assertEquals(conf.getNom(), gestion.getEvenements().get(conf.getId()).getNom());

        new File(chemin).delete();
    }

}