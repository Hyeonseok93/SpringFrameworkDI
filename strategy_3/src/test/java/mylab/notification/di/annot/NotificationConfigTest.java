package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class, loader = AnnotationConfigContextLoader.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void testNotificationManagerDI() {
        assertNotNull(notificationManager, "NotificationManager 빈이 주입되지 않았습니다.");
        
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertNotNull(emailService, "1) 이메일 서비스가 Not Null인지 검증");
        assertEquals("smtp.gmail.com", emailService.getSmtpServer(), "2) SMTP 서버 주소 비교");
        assertEquals(587, emailService.getPort(), "3) 포트 번호 검증");
        
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertNotNull(smsService, "a) SMS 서비스가 Not Null인지 검증");
        assertEquals("SKT", smsService.getProvider(), "b) 제공업체 값 비교");
    }

    @Test
    void testSendNotifications() {
        notificationManager.sendNotificationByEmail("테스트 이메일"); 
        notificationManager.sendNotificationBySms("테스트 SMS");    
    }
}
