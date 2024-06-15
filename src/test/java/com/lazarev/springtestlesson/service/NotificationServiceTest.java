package com.lazarev.springtestlesson.service;

import com.lazarev.springtestlesson.entity.Notification;
import com.lazarev.springtestlesson.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private NotificationRepository notificationRepository;

    @Captor
    private ArgumentCaptor<Notification> captor;

    @Test
    void sendNotification_emailSent_ifValidationCorrect() {
        String to = "Mike";
        String text = "Mail text";

        doNothing()
                .when(emailService)
                .sendEmail(to, text);

        boolean result = notificationService.sendNotification(to, text);

        assertTrue(result);
    }

    @Test
    void sendNotification_emailNotSent_ifValidationIncorrect() {
        String to = "Mike";
        String text = "Mail text";

        doThrow(IllegalArgumentException.class)
                .when(emailService)
                .sendEmail(to, text);

        boolean result = notificationService.sendNotification(to, text);

        assertFalse(result);
    }

    @Test
    void getNotificationsCount_correctSize_ifEmailListIsNotEmpty() {
        List<String> emails = List.of("Mail1", "Mail2", "Mail3");

        when(emailService.getAllEmails())
                .thenReturn(emails);

        int result = notificationService.getNotificationsCount();
        assertEquals(emails.size(), result);
    }

    @Test
    void getNotificationsCount_NPE_ifEmailListIsNull() {
        List<String> emails = null;

        when(emailService.getAllEmails())
                .thenReturn(emails);

        assertThrows(NullPointerException.class,
                () -> notificationService.getNotificationsCount()
        );
    }

    @Test
    void sendNotifications_emailSent_ifAddressesListIsNotEmpty() {
        List<String> addresses = List.of("Mike", "Bob");
        String text = "Mail text";
        String expectedText = "[Mail text]";

        notificationService.sendNotifications(addresses, text);

        verify(emailService, times(2)).sendEmail(anyString(), eq(expectedText));
    }

    @Test
    void saveNotification_notificationSaved() {
        String to = "Mike";
        String text = "Mail text";

        notificationService.saveNotification(text, to);

        verify(notificationRepository).save(captor.capture());

        Notification notification = captor.getValue();
        assertEquals(to, notification.getTo());
        assertEquals(text, notification.getText());
    }
}