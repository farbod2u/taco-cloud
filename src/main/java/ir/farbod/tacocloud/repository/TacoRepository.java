package ir.farbod.tacocloud.repository;

import ir.farbod.tacocloud.entity.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
