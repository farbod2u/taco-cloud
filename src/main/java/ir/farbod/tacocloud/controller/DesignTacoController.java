package ir.farbod.tacocloud.controller;

import ir.farbod.tacocloud.entity.Ingredient;
import ir.farbod.tacocloud.entity.Ingredient.Type;
import ir.farbod.tacocloud.entity.Taco;
import ir.farbod.tacocloud.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignTacoController {

    private IngredientService ingredientService;

    @Autowired
    public DesignTacoController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = ingredientService.getAll();
        for (Ingredient.Type type : Type.values())
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));

        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
    }

    @PostMapping
    public String proccessDesign(@Valid Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Type[] types = Type.values();
            for (Type type : types)
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientService.getAll(), type));
            model.addAttribute("design", design);
            return "design";
        }

        log.info("Proccessing design : " + design);
        return "redirect:/orders/current";
    }
}
