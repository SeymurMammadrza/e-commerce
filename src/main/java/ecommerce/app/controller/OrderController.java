package ecommerce.app.controller;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.service.CustomerService;
import ecommerce.app.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    private OrderService orderService;
    private CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
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

    //View order ops
    @RequestMapping(value = {"/order/operations"}, method = RequestMethod.GET)
    public String OrderOperations() {
        return "order/orderOperations";
    }

    //New order
    @RequestMapping(value = {"/order/new"}, method = RequestMethod.GET)
    public String newOrder(Model model, Order order) {
        model.addAttribute("order", order);
        model.addAttribute("customers",customerService.getAll());
        return "order/orderOperations";
    }

    //Save order
    @RequestMapping(value = {"/order/save"}, method = RequestMethod.POST)
    public String saveOrder(Model model,Order order) {
        orderService.saveOrder(order);
        model.addAttribute("orders",customerService.findOrdersByCustomer(order.getCustomer()));
        model.addAttribute("customer",orderService.getCustomerByOrder(order));
        return "/order/orderListByCustomer";
    }
    //Delete order
    @RequestMapping(value = {"/order/delete"}, method = RequestMethod.GET)
    public String deleteOrder(Model model, @RequestParam("id") Long id) {
        Order order=orderService.getById(id);
        Customer customer = orderService.getCustomerByOrder(order);
        orderService.deleteOrderById(id);
        model.addAttribute("customer",customer);
        model.addAttribute("orders",customerService.findOrdersByCustomer(customer));
        return "redirect:/order/list";
    }
    //Update order

    @RequestMapping(value = {"/order/update"}, method = RequestMethod.GET)
    public String updateOrder(Model model, @RequestParam("id") Long id) {

        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        model.addAttribute("customers",customerService.getAll());
        return "order/orderOperations";
    }
}
