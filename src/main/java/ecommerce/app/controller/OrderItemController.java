package ecommerce.app.controller;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import ecommerce.app.service.CustomerService;
import ecommerce.app.service.OrderItemService;
import ecommerce.app.service.OrderService;
import ecommerce.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderItemController {
    private OrderItemService orderItemService;
    private ProductService productService;
    private OrderService orderService;
    private CustomerService customerService;

    public OrderItemController(OrderItemService orderItemService, ProductService productService, OrderService orderService, CustomerService customerService) {
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    //Order items list
    @RequestMapping(value = {"orderitem/list"}, method = RequestMethod.GET)
    public String orderItemList(Model model) {
        orderItemService.getAll().stream().forEach(System.out::println);
        model.addAttribute("orderItems", orderItemService.getAll());
        return "orderItem/orderItemList";
    }

    //Order items list by order id
    @RequestMapping(value = {"order/orderitems"}, method = RequestMethod.GET)
    public String orderItemListByOrder(Model model, @RequestParam("id") Long id) {
        Order order = orderService.getById(id);
        List<OrderItem> orderItems = orderService.findOrderItemsByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "orderItem/orderItemListByOrder";
    }


    //Single Order item by id
    @RequestMapping(value = {"/orderitem"}, method = RequestMethod.GET)
    public String orderItemById(Model model, @RequestParam("id") Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        System.out.println(orderItem);
        model.addAttribute("orderItem", orderItem);
        return "orderItem/orderItem";
    }

    //View order item ops
    @RequestMapping(value = {"/orderitem/operations"}, method = RequestMethod.GET)
    public String OrderItemOperations() {
        return "orderItem/orderItemOperations";
    }

    //New order item by Order
    @RequestMapping(value = {"/orderitem/new"}, method = RequestMethod.GET)
    public String newOrderItemByOrder(Model model, @RequestParam("id") Long id, OrderItem orderItem) {
        Order order = orderService.getById(id);
        List<Order> ordersByCustomer= (orderService.getCustomerByOrder(order)).getOrders();
        List<Product> products = productService.getAll();
        model.addAttribute("customers",customerService.getAll() );
        model.addAttribute("order", order);
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("products", products);
        model.addAttribute("orders",ordersByCustomer);
        return "orderItem/orderItemOperations";
    }

    //Save order item
    @RequestMapping(value = {"/orderitem/save"}, method = RequestMethod.POST)
    public String saveOrderItem(Model model, OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        model.addAttribute("orderItems", orderService.findOrderItemsByOrder(orderItem.getOrder()));
        model.addAttribute("product", orderItemService.ProductByOrderItem(orderItem));
        return "orderItem/orderItemListByOrder";
    }

    //Delete order
    @RequestMapping(value = {"/orderitem/delete"}, method = RequestMethod.GET)
    public String deleteOrderItem(Model model, @RequestParam("id") Long id) {
        Order order = orderService.getById(id);
        List<OrderItem> orderItems = orderService.findOrderItemsByOrder(order);
        orderItemService.deleteOrderItemById(id);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", order);
        return "redirect:/customer/list";
    }
    //Update order item

    @RequestMapping(value = {"/orderitem/update"}, method = RequestMethod.GET)
    public String updateOrderItem(Model model, @RequestParam("id") Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        Order order = orderService.OrderByOrderItem(orderItem);
        model.addAttribute("order", order);
        model.addAttribute("orderItem", orderItem);
        return "orderItem/orderItemOperations";
    }
}
