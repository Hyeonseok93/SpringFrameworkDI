package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    // 다이어그램에 명시된 변수명 'cart'로 수정
    @Autowired
    private ShoppingCart cart;

    // 다이어그램에 명시된 변수명 'service'로 수정
    @Autowired
    private OrderService service;

    @Test
    public void testShoppingCart() {
        assertNotNull(cart, "shoppingCart 객체가 Null입니다.");
        assertEquals(2, cart.getProducts().size(), "장바구니 상품 수는 2개여야 합니다.");
        assertEquals("노트북", cart.getProducts().get(0).getName(), "첫 번째 상품 이름이 '노트북'이 아닙니다.");
        assertEquals("스마트폰", cart.getProducts().get(1).getName(), "두 번째 상품 이름이 '스마트폰'이 아닙니다.");
    }

    @Test
    public void testOrderService() {
        assertNotNull(service, "orderService 객체가 Null입니다.");
        assertNotNull(service.getShoppingCart(), "orderService에 주입된 shoppingCart가 Null입니다.");
        
        assertEquals(950000.0, service.calculateOrderTotal(), 0.01, "총 주문 금액이 일치하지 않습니다.");
    }
}