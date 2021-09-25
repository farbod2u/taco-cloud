package ir.farbod.tacocloud.controller;

import ir.farbod.tacocloud.entity.Ingredient;
import ir.farbod.tacocloud.entity.Design;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ir.farbod.tacocloud.entity.Ingredient.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        Type[] types = Ingredient.Type.values();
        for (Type type : types)
            model.addAttribute(type.toString().toLowerCase(), filterByType(getIngredients(), type));

        model.addAttribute("design", new Design());

        return "design";
    }

    private List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        return ingredients;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
    }

    @PostMapping
    public String proccessDesign(@Valid Design design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Type[] types = Ingredient.Type.values();
            for (Type type : types)
                model.addAttribute(type.toString().toLowerCase(), filterByType(getIngredients(), type));
            model.addAttribute("design", design);
            return "design";
        }

        log.info("Proccessing design : " + design);
        return "redirect:/orders/current";
    }
}
