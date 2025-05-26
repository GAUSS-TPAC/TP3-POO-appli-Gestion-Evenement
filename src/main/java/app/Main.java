package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import modele.Conference;
import service.GestionEvenements;
import modele.Participant;

import java.io.File;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Chemin unique pour le fichier JSON
        String chemin = "/home/gauss/Bureau/WORK DEV/AppGestionEvenement/evenements.json";

        // Instance du singleton
        GestionEvenements ge = GestionEvenements.getInstance();

        // TEST 1: Créer un événement et tester la sérialisation directe
        System.out.println("=== TEST DE SERIALISATION ===");
        Conference conf = new Conference("1", "Conférence IA", LocalDateTime.now(), "Paris", 100, "IA");
        Participant p1 = new Participant("p1", "Alice", "alice@example.com");

        try {
            conf.ajouterParticipant(p1);
            System.out.println("✓ Participant ajouté avec succès");
        } catch (Exception e) {
            System.out.println("✗ Erreur ajout participant: " + e.getMessage());
        }

        // TEST 2: Sérialisation directe avec ObjectMapper
        try {
            ObjectMapper mapper = new ObjectMapper();

            String jsonConference = mapper.writeValueAsString(conf);
            System.out.println("✓ JSON Conference créé:");
            System.out.println(jsonConference);

        } catch (Exception e) {
            System.out.println("✗ Erreur sérialisation directe: " + e.getMessage());
            e.printStackTrace();
        }

        // TEST 3: Test avec GestionEvenements
        System.out.println("\n=== TEST AVEC GESTION EVENEMENTS ===");

        // Vérifie si le fichier existe
        File fichier = new File(chemin);
        if (!fichier.exists()) {
            System.out.println("Fichier " + chemin + " introuvable. Initialisation vide.");
        }

        // Chargement
        ge.charger(chemin);
        System.out.println("Nombre d'événements chargés : " + ge.getEvenements().size());

        // Ajout d'un événement
        if (!ge.getEvenements().containsKey("1")) {
            ge.ajouterEvenement(conf.getId(), conf);
            System.out.println("✓ Événement ajouté à GestionEvenements.");
        } else {
            System.out.println("L'événement existe déjà.");
        }

        // TEST 4: Vérification avant sauvegarde
        System.out.println("\n=== VERIFICATION AVANT SAUVEGARDE ===");
        System.out.println("Taille de la Map : " + ge.getEvenements().size());
        System.out.println("Clés dans la Map : " + ge.getEvenements().keySet());

        if (ge.getEvenements().containsKey("1")) {
            Conference confRecuperee = (Conference) ge.getEvenements().get("1");
            System.out.println("Conference récupérée : " + confRecuperee.getNom());
            System.out.println("Nombre de participants : " + confRecuperee.getParticipants().size());
        }

        // TEST 5: Sauvegarde et vérification
        System.out.println("\n=== SAUVEGARDE ===");
        try {
            ge.sauvegarder(chemin);
            System.out.println("✓ Sauvegarde terminée dans : " + chemin);

            // Vérification du fichier créé
            File fichierCree = new File(chemin);
            if (fichierCree.exists()) {
                System.out.println("✓ Fichier créé, taille : " + fichierCree.length() + " bytes");
            } else {
                System.out.println("✗ Fichier non créé");
            }

        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
        }
    }
}