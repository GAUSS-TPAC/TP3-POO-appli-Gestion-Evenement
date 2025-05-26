package modele;

import java.time.LocalDateTime;

public class Conference extends modele.Evenement{
    private String theme;
    private String intervenants;

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme) {
        super(id, nom, date, lieu, capaciteMax );
        this.theme = theme;
        this.intervenants=intervenants;
    }
    public Conference() {
        super();
        this.theme = theme;
        this.intervenants=intervenants;

    }


    public String getIntervenants() {return intervenants;}
    public String getTheme() {
        return theme;
    }


    public void setTheme(String theme) { this.theme = theme; }
    public void setIntervenants(String intervenants) { this.intervenants = intervenants; }

    @Override
    public void afficherDetails() {
        System.out.println("=== Conférence ===");
        System.out.println("Thème     : " + getTheme());
        System.out.println("Intervenants : " + getIntervenants());
        super.afficherDetails();
    }

    @Override
    public void annuler(){
        this.estAnnuler=true;
        System.out.println("La conférence \"" + getNom() + "\" a été annulée.");

    }
}
