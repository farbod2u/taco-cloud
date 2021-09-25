package ir.farbod.tacocloud.repository;

import ir.farbod.tacocloud.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
