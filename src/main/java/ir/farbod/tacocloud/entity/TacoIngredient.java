package ir.farbod.tacocloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "tbl_taco_ingredient")
@Data
public class TacoIngredient implements Serializable {

    @Id
    @ManyToOne(
            targetEntity = Taco.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    @JsonIgnoreProperties("tacoIngredients")
    private Taco taco;

    @Id
    @ManyToOne(
            targetEntity = Ingredient.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    @JsonIgnoreProperties("tacoIngredients")
    private Ingredient ingredient;
}
