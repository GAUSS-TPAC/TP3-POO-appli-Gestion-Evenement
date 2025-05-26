package service;

public interface NotificationService {
    void envoyerNotification(String message);
    void envoyerNotificationAsync(String message);
}
