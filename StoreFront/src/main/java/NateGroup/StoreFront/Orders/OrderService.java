package NateGroup.StoreFront.Orders;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final Dotenv dotenv;

    private final RestClient restClient;

    private final String PAYPAL_API_BASE_URL;

    private final OrderRepository orderRepository;

    public OrderService(RestClient.Builder restClientBuilder, OrderRepository orderRepository) {
        this.dotenv = Dotenv.load();

        this.PAYPAL_API_BASE_URL = this.dotenv.get("PAYPAL_API_BASE_URL", "https://api-m.sandbox.paypal.com");

        this.restClient = restClientBuilder.baseUrl(this.PAYPAL_API_BASE_URL).build();
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Map<String, Object>> createOrder(String accessToken, Map<String, Object> payload) {
        ResponseEntity<Map<String, Object>> result
                = this.restClient.post()
                .uri("/v2/checkout/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> {
                    header.add("PayPal-Request-Id", Long.toString(System.currentTimeMillis()));
                    header.add("Authorization", "Bearer " + accessToken);
                })
                .body(payload)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                });

        return result;
    }

    public ResponseEntity<Order> getAllOrders() {
        ResponseEntity<Order> result = (ResponseEntity<Order>) orderRepository.getAllOrders();
        return result;
    }

    public ResponseEntity<Order> deleteOrder(Long Id){
        Order order = orderRepository.getById(Id);
        orderRepository.delete(order);
        return ResponseEntity.ok(order);
    }
    public ResponseEntity<Order> getOrderById(Long Id){
        Order order = orderRepository.getById(Id);
        return ResponseEntity.ok(order);
    }
}