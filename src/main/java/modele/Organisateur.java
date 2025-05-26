package modele;

import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant {
    private List<Evenement> evenementsOrganises = new ArrayList<>();

    public List<Evenement> getEvenementsOrganises() {
        return evenementsOrganises;
    }

    public Organisateur(String id, String nom, String email) {
        super(id, nom, email);
    }
}
