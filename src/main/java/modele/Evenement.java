package modele;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import exception.CapaciteMaxAtteinteException;
import exception.ParticipantDejaInscrit;
import exception.ParticipantNonTrouvé;
import service.EvenementObservable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= Conference.class, name="Conference"),
        @JsonSubTypes.Type(value=Concert.class, name="Concert")
})

public abstract class  Evenement implements EvenementObservable {
    private  String id;
    private  String nom;
    private  LocalDateTime date;
    private  String lieu;
    private  int capaciteMax;
    private  List<Participant> participants;

    protected boolean estAnnuler = false;

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax){
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.participants = new ArrayList<>();
    }

    public Evenement(){
        this.participants= new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String getId() {
        return id;
    }

    public String getLieu() {
        return lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void ajouterParticipant(Participant p) {
        if (participants.size() >= capaciteMax) {
            throw new CapaciteMaxAtteinteException("capacité maximal deja atteinte");
        } else if (participants.contains(p)) {
            throw new ParticipantDejaInscrit("le participant" + p.getNom() + " est deja enregistré!");
        } else {
            participants.add(p);
        }

    }
    public void supprimerParticipant(Participant p){
        if (participants.contains(p)){
            participants.remove(p);
        }
        else  {
            throw new ParticipantNonTrouvé("l'individu n'est pas inscrit à cette evenement");
        }
    }

    public void setEstAnnuler(boolean estAnnuler){
        this.estAnnuler= estAnnuler;
    }

    public void annuler(){
        this.estAnnuler= true;
        notifierObservateurs("l'evenement"+nom +"est annulé.");
    }


    public void afficherDetails(){
        System.out.println("ID : " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Date : " + date);
        System.out.println("Lieu : " + lieu);
        System.out.println("Capacité Max : " + capaciteMax);
        System.out.println("Participants :");
        for (Participant p : participants) {
            System.out.println(" - " + p.getNom() + " (" + p.getEmail() + ")");
        }
    }


    @Override
    public void notifierObservateurs(String message){
        for (Participant p: participants){
            p.recevoirNotification(message);
        }
    }
}
