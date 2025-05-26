package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class NotificationServiceImpl implements NotificationService{

    private List<ParticipantObserver> observers = new ArrayList<>();
    public void ajouterObservateur(ParticipantObserver o){
        observers.add(o);
    }
    public void supprimerObservateur(ParticipantObserver o){
        observers.remove(o);
    }

    @Override
    public void envoyerNotification(String message) {
        for (ParticipantObserver o: observers){
            o.recevoirNotification(message);
        }
    }

    @Override
    public void envoyerNotificationAsync(String message) {
        CompletableFuture.runAsync(()->{
           try{
               int delay = ThreadLocalRandom.current().nextInt(1000,3000);
               Thread.sleep(delay);
               envoyerNotification(message);
           } catch (InterruptedException e){
               Thread.currentThread().interrupt();
               System.err.println("Notification interrompue: "+ e.getMessage());
           }
        });
    }
}
