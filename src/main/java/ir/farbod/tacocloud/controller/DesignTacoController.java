package ir.farbod.tacocloud.controller;

import ir.farbod.tacocloud.entity.Ingredient;
import ir.farbod.tacocloud.entity.Ingredient.Type;
import ir.farbod.tacocloud.entity.Taco;
import ir.farbod.tacocloud.service.IngredientService;
import ir.farbod.tacocloud.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignTacoController {

    private IngredientService ingredientService;
    private TacoService tacoService;

    @Autowired
    public DesignTacoController(IngredientService ingredientService, TacoService tacoService) {
        this.ingredientService = ingredientService;
        this.tacoService = tacoService;
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
    public ModelAndView proccessDesign(HttpSession session, @Valid Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Type[] types = Type.values();
            for (Type type : types)
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientService.getAll(), type));
            model.addAttribute("design", design);
            return new ModelAndView("design");
        }

        ModelAndView view = new ModelAndView(new RedirectView("/orders/current"));
        design = tacoService.save(design);
        //view.addObject("taco", design);

        session.setAttribute("taco", design);

        log.info("******  Proccessing design : " + design);
        //return "redirect:/orders/current";
        return view;
    }
}
