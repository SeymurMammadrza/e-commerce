package ecommerce.app.controller;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import ecommerce.app.service.OrderItemService;
import ecommerce.app.service.OrderService;
import ecommerce.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private OrderItemService orderItemService;
    private OrderService orderService;

    public ProductController(ProductService productService, OrderItemService orderItemService, OrderService orderService) {
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    //products list
    @RequestMapping(value = {"product/list"}, method = RequestMethod.GET)
    public String productList(Model model) {
        productService.getAll().stream().forEach(System.out::println);
        model.addAttribute("products", productService.getAll());
        return "product/productList";
    }

    //Single product by order Item id
    @RequestMapping(value = {"/product/product"}, method = RequestMethod.GET)
    public String productById(Model model, @RequestParam("id") Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        Product product = orderItem.getProduct();
        model.addAttribute("product", product);
        model.addAttribute("orderItem",orderItem);
        return "product/product";
    }

    //View product ops
    @RequestMapping(value = {"/product/operations"}, method = RequestMethod.GET)
    public String productOperations() {
        return "product/productOperations";
    }

    //New product
    @RequestMapping(value = {"/product/new"}, method = RequestMethod.GET)
    public String newProduct(Model model, Product product) {
        model.addAttribute("product", product);

        return "product/productOperations";
    }

    //New product by order item
    @RequestMapping(value = {"/orderitem/product/new"}, method = RequestMethod.GET)
    public String newOrderItemByOrder(Model model, @RequestParam("id") Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        List <OrderItem> orderItems=orderItemService.getAll();
        List <Product> products = productService.getAll();
        model.addAttribute("orders",orderService.getAll());
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("products", products);
        return "product/productOperationsByOrderItem";
    }


    //Save product
    @RequestMapping(value = {"/product/save"}, method = RequestMethod.POST)
    public String saveProduct(Model model,Product product) {
        productService.saveProduct(product);
        model.addAttribute("products", productService.getAll());
        return "product/productList";
    }

    //Delete product
    @RequestMapping(value = {"/product/delete"}, method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/product/list";
    }
    @RequestMapping(value = {"/product/update"}, method = RequestMethod.GET)
    public String updateProduct(Model model, @RequestParam("id") Long id) {
        Product product = productService.getById(id);
        System.out.println(product);
        model.addAttribute("product", product);
        return "product/productOperations";
    }
    }

