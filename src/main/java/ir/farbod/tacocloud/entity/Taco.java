package ir.farbod.tacocloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_taco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fldKey")
    private Long id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    @Column(nullable = false, length = 255)
    private String name;

    @NotNull(message = "You must choose at least 1 ingredient")
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    //@Transient /* non column variable */
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }

    /*@OneToMany(mappedBy = "ingredient")
    private List<TacoIngredient> tacoIngredients;*/
}
