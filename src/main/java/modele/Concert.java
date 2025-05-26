package modele;

import java.time.LocalDateTime;

public class Concert extends modele.Evenement{
    public String getGenreMusical() {
        return genreMusical;
    }

    public String getArtiste() {
        return artiste;
    }

    private String artiste;
    private String genreMusical;

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    @Override
    public void annuler() {
        this.estAnnuler=true;
        System.out.println("Le concert \"" + getNom() + "\" a été annulé.");
    }

    @Override
    public void afficherDetails(){
        System.out.println("=== Concert ===");
        System.out.println("Artiste       : " + getArtiste());
        System.out.println("Genre musical : " + getGenreMusical());
        super.afficherDetails();
    }
}
