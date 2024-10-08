package NateGroup.StoreFront.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCustomerId(Long customerId);
    List<Order> getAllOrders();

    Optional<Order> getOrder();
}