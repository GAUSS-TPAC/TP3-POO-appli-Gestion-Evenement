package modele;

import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant {
    private List<Evenement> evenements;

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public Organisateur(String id, String nom, String email, List<Evenement> evenements) {
        super(id, nom, email);
        this.evenements = new ArrayList<>(evenements);
    }
}
