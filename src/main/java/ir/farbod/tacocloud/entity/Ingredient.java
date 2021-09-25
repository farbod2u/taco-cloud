package ir.farbod.tacocloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_ingredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {

    @Id
    @Column(name = "fld_key")
    private String id;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    public static enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }

//    public enum Type {
//        WRAP(1),
//        PROTEIN(2),
//        VEGGIES(3),
//        CHEESE(4),
//        SAUCE(5);
//
//        private int value;
//
//        Type(int value) {
//            this.value = value;
//        }
//    }

    /*@OneToMany(mappedBy = "taco")
    private List<TacoIngredient> tacoIngredients;*/
}
