package com.lazarev.springtestlesson.service;

import com.lazarev.springtestlesson.entity.Notification;
import com.lazarev.springtestlesson.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    public boolean sendNotification(String to, String text){
        try{
            emailService.sendEmail(to, text);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public int getNotificationsCount(){
        return emailService.getAllEmails().size();
    }

    public void sendNotifications(List<String> addresses, String text){
        for(String to : addresses){
            String modifiedText = "[" + text + "]";
            emailService.sendEmail(to, modifiedText);
        }
    }

    public void saveNotification(String text, String to){
        Notification notification = new Notification();
        notification.setText(text);
        notification.setTo(to);
        notification.setSentAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}
