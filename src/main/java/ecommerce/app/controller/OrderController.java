package ecommerce.app.controller;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.service.CustomerService;
import ecommerce.app.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Order list
    @RequestMapping(value = {"order/list"}, method = RequestMethod.GET)
    public String orderList(Model model) {
        orderService.getAll().stream().forEach(System.out::println);
        model.addAttribute("orders", orderService.getAll());
        return "order/orderList";
    }

    //Single Order by id
    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String orderById(Model model, @RequestParam("id") Long id) {
        Order order = orderService.getById(id);
        System.out.println(order);
        model.addAttribute("order", order);
        return "order/order";
    }
}
