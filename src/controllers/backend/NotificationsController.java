package controllers.backend;

import javafx.application.Platform;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Uellington Conceição
 */
public class NotificationsController {

    private static NotificationsController controller;

    private NotificationsController() {
    }

    public static synchronized NotificationsController getInstance() {
        return (controller == null) ? controller = new NotificationsController() : controller;
    }

    public void infoNotification(String title, String message) {
        this.createNotification(title, message, NotificationType.INFORMATION);
    }

    public void warningNotification(String title, String message) {
        this.createNotification(title, message, NotificationType.WARNING);
    }

    public void errorNotification(String title, String message) {
        this.createNotification(title, message, NotificationType.ERROR);
    }

    public void sucessNotification(String title, String message) {
        this.createNotification(title, message, NotificationType.SUCCESS);
    }

    public void createNotification(String title, String message, NotificationType type) {
        Platform.runLater(() -> {
            TrayNotification notification = new TrayNotification();
            notification.setMessage(message);
            notification.setNotificationType(type);
            notification.setTitle(title);
            notification.setAnimationType(AnimationType.SLIDE);
            notification.showAndDismiss(Duration.seconds(3));
        });

    }
}
