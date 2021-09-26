package ir.farbod.tacocloud.controller;

import ir.farbod.tacocloud.entity.Order;
import ir.farbod.tacocloud.entity.Taco;
import ir.farbod.tacocloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order";
    }

    @PostMapping
    public String processOrder(HttpSession session, @Valid Order order, Errors errors) {
        if (errors.hasErrors())
            return "order";

        Taco taco = (Taco) session.getAttribute("taco");
        order.addDesign(taco);
        order = orderService.save(order);
        log.info("******   Order submited : " + order);
        return "redirect:/";
    }
}
