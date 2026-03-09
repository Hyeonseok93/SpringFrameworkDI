package mylab.user.di.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserServiceDI() {
        assertNotNull(userService, "UserService 빈이 주입되지 않았습니다.");

        assertNotNull(userService.getUserRepository(), "UserRepository가 주입되지 않았습니다.");

        assertEquals("MySQL", userService.getUserRepository().getDbType(), "DB 타입이 MySQL이 아닙니다.");

        assertNotNull(userService.getSecurityService(), "SecurityService가 주입되지 않았습니다.");

        boolean result = userService.registerUser("user1", "홍길동", "pass123");
        assertTrue(result, "사용자 등록 결과가 True여야 합니다.");
    }
}
