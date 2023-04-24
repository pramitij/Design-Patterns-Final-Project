package com.inventory.controller;

import com.inventory.model.*;
import com.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/fetchData")
public class FetchAllDataController {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPORepository productPORepository;

    @GetMapping("/getAll")
    public Map<String,Integer> getAll() {
        List<Integer> AllData = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        List<Buyer> buyers = buyerRepository.getBuyers();
        map.put("buyers",buyers.size());
        AllData.add(buyers.size());

        List<Employee> employees = employeeRepository.getEmployees();
        AllData.add(employees.size());
        map.put("employees",employees.size());

        List<Invoice> invoices = invoiceRepository.getInvoices();
        AllData.add(invoices.size());
        map.put("invoices",invoices.size());


        List<Product> products = productRepository.getProducts();
        AllData.add(products.size());
        map.put("products",products.size());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.getPurchaseOrders();
        AllData.add(purchaseOrders.size());
        map.put("purchaseOrders",purchaseOrders.size());


        List<ProductPO> productPOs = productPORepository.getProductPOs();
        AllData.add(productPOs.size());
        map.put("productPOs",productPOs.size());

        return map;
    }

}
