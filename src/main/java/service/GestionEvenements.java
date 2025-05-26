package service;

import exception.EvenementDejaExistantException;
import exception.EvenementInexistantException;
import modele.Evenement;
import utils.EvenementSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestionEvenements {
    private Map<String, Evenement> evenements;
    private static GestionEvenements INSTANCE;
    private EvenementSerializer serialiser= new EvenementSerializer();

    public Map<String, Evenement> getEvenements() {
        return evenements;
    }

    private GestionEvenements() {
        this.evenements = new HashMap<>();
    }

    /// ////// singleton
    public static synchronized GestionEvenements getInstance(){
        if (INSTANCE == null){
            INSTANCE= new GestionEvenements();
        }
        return INSTANCE;
    }
    ///////
    public void ajouterEvenement( String id, Evenement e){
        if (evenements.containsKey(id)) {

            System.out.println("Événement déjà existant avec ID " + id);
            throw new EvenementDejaExistantException("L'événement avec l'ID " + id + " existe déjà.");
        }
        else {
            evenements.put(id, e);
            System.out.println("Événement ajouté : " + e.getNom());
        }
    }
    public void supprimerEvenement(String id){
        evenements.remove(id);
    }

    public Evenement rechercherEvenement(String id){
        if (evenements.containsKey(id)){
            return evenements.get(id);
        }
        else {
            throw new EvenementInexistantException("cet evenement n'existe pas");
        }

    }

    /// //////////persistance de la serialisation
    public void sauvegarder(String chemin){
        try {
            serialiser.sauvegarder(evenements, chemin);
        } catch (IOException e){
            System.err.println("Erreur lors de la sauvegarde :"+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void charger(String chemin){
        try {
            Map<String, Evenement> loaded = serialiser.charger(chemin);
            if (loaded != null){
                evenements.clear();
                evenements.putAll(loaded);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement:"+ e.getMessage());
        }
    }
}
