package modele;

import exception.CapaciteMaxAtteinteException;
import exception.ParticipantDejaInscrit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
class EvenementTest {
/// ///// inscription Participant
    @Test
    @DisplayName("test ajoutParticipant depassant  capacité max de 1")
    public void testAjouterParticipantDepasseCapaciteMax() {
        Evenement evenement =new Conference("1","Conference test", LocalDateTime.now(),"paris",1,"IA");

        Participant p1 =new Participant("p1","Alan","alan@gmail.com");
        Participant p2 =new Participant("p2","lin","lin@gmail.com");

        evenement.ajouterParticipant(p1);

        assertThrows(CapaciteMaxAtteinteException.class, () ->{
            evenement.ajouterParticipant(p2);
        });
    }

    @Test
    @DisplayName("test participant deja inscrit")
    public void testParticipantDejaInscrit(){
        Evenement event = new Conference("1", "Conférence Test", LocalDateTime.now(), "Paris", 2, "IA");

        Participant p1 = new Participant("p1", "Alice", "alice@example.com");

        event.ajouterParticipant(p1);

        assertThrows(ParticipantDejaInscrit.class, () ->{
           event.ajouterParticipant(p1);
        });
    }

    @Test
    @DisplayName("test participant valide, nombre participant")
    public void testParticipantValideNombreParticipant() {
        Evenement evenement= new Concert("2", "Concert Test", LocalDateTime.now(), "Lyon", 5, "Artiste XYZ", "Pop");

        Participant p1= new Participant("p1", "Alice", "alice@example.com");

        evenement.ajouterParticipant(p1);

        assertTrue(evenement.getParticipants().contains(p1));
        assertEquals(1, evenement.getParticipants().size());
    }

    /// /////////// Desinscription participant
    @Test
    @DisplayName("Desinscription participant")
    public void testSupprimerPaticipant(){
        Evenement event=new Concert("2", "Concert Test", LocalDateTime.now(), "Lyon", 5, "Artiste XYZ", "Pop");

        Participant p1 = new Participant("p1", "Alice", "alice@example.com");

        event.ajouterParticipant(p1);
        assertTrue(event.getParticipants().contains(p1));

        event.supprimerParticipant(p1);
        assertEquals(0,event.getParticipants().size());

    }

}