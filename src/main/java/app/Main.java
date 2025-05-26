package app;

import modele.Concert;
import modele.Conference;
import service.GestionEvenements;
import modele.Organisateur;
import modele.Participant;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        GestionEvenements gestion = GestionEvenements.getInstance();

        // 🔥 Charger les événements existants depuis JSON
        gestion.charger("evenements.json");

        // 🎤 Créer des participants
        Participant Tchapda = new Participant("p1", "Tchapda", "tchapda@example.com");
        Participant Alan = new Participant("p2", "Alan", "alan@example.com");

        // 👨‍💼 Créer un organisateur
        Organisateur Mr_Kungne = new Organisateur("o1", "Mr_Kungne", "Mr_Kungne@example.com");

        // 🎉 Créer une conférence
        Conference confPOO = new Conference("conf1", "Conférence POO", LocalDateTime.now().plusDays(10),
                "ENSPY", 2, "PRINCIPE S.O.L.I.D EN P.O.O");
        confPOO.ajouterParticipant(Tchapda);
        confPOO.ajouterParticipant(Alan);

        // 🎵 Créer un concert
        Concert concertRap = new Concert("concert1", "RAP", LocalDateTime.now().plusDays(5),
                "Canal Olympia", 100, "Eminem", "RAP");

        // 👨‍💼 Ajout d'événements par l'organisateur
        Mr_Kungne.getEvenementsOrganises().add(confPOO);
        Mr_Kungne.getEvenementsOrganises().add(concertRap);
        try {
            gestion.ajouterEvenement(confPOO.getId(), confPOO);
            gestion.ajouterEvenement(concertRap.getId(), concertRap);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // 📣 Afficher les détails des événements
        confPOO.afficherDetails();
        concertRap.afficherDetails();

        // 🚨 Annuler la conférence et envoyer notifications ASYNCHRONES
        confPOO.annuler();

        // 🕒 Attendre un peu pour que les notifications asynchrones s'affichent avant de quitter
        try {
            Thread.sleep(4000); // Attendre 4 secondes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 💾 Sauvegarder les événements modifiés
        gestion.sauvegarder("evenements.json");

        System.out.println("✅ Gestion des événements terminée.");
    }
}
