package ir.farbod.tacocloud.repository;

import ir.farbod.tacocloud.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
