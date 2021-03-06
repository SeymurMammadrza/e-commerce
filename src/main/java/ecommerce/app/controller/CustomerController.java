package ecommerce.app.controller;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.service.CustomerService;
import ecommerce.app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Slf4j
@Controller
public class CustomerController {
    private CustomerService customerService;
    private OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    // TEST
    @RequestMapping(value = {"/", "/hello"}, method = RequestMethod.GET)
    public String hello(Model model,
                        @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    //Customer list
    @RequestMapping(value = {"customer/list"}, method = RequestMethod.GET)
    public String customerList(Model model) {
        customerService.getAll().stream().forEach(System.out::println);
        model.addAttribute("customers", customerService.getAll());
        return "customer/customerList";
    }

    //Single Customer by id
    @RequestMapping(value = {"/customer"}, method = RequestMethod.GET)
    public String customerById(Model model, @RequestParam("id") Long id) {
        Customer customer = customerService.getById(id);
        System.out.println(customer);

        model.addAttribute("customer", customer);
        return "customer/customer";
    }

    //New customer
    @RequestMapping(value = {"/customer/new"}, method = RequestMethod.GET)
    public String newCustomer(Model model, Customer customer) {
        model.addAttribute("customer", customer);

        return "customer/customerOperations";
    }

    //Update customer

    @RequestMapping(value = {"/customer/update"}, method = RequestMethod.GET)
    public String updateCustomer(Model model, @RequestParam("id") Long id) {

        Customer customer = customerService.getById(id);
        System.out.println(customer);

        model.addAttribute("customer", customer);
        return "customer/customerOperations";
    }

    //Delete customer
    @RequestMapping(value = {"/customer/delete"}, method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customer/list";
    }

    //Save customer
    @RequestMapping(value = {"/customer/save"}, method = RequestMethod.POST)
    public String saveCustomer(Model model, @ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    //View orders
    @RequestMapping(value = {"/customer/orders"}, method = RequestMethod.GET)
    public String CustomerOrders(Model model, @RequestParam("id") Long id) {
        Customer customer=customerService.getById(id);
        List <Order> orders=customerService.findOrdersByCustomer(customer);
        log.info("customer ={}",customer);
        log.info("orders = {}",orders);
        model.addAttribute("customer",customer);
        model.addAttribute("orders",orders );
        return "order/orderListByCustomer";
    }
}
