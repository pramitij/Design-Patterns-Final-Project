package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Invoice;
import com.inventory.repository.InvoiceRepository;
import com.inventory.repository.PurchaseOrderRepository;
import com.inventory.strategy.InvoiceStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

	@PostMapping("/generateInvoice/{id}")
	public void save(@PathVariable int id) {
		
		/*PurchaseOrder po = purchaseOrderRepository.getPurchaseOrderbyID(id);
		po.setPaid(true);
		purchaseOrderRepository.update(po);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String paymentDate = formatter.format(date);
		
		Invoice invoice = new Invoice();
		invoice.setPaymentDate(paymentDate);
		invoice.setPurchaseOrder(po);
		*/
		StrategyTrigger trig = new StrategyTrigger(new InvoiceStrategy(invoiceRepository, id,purchaseOrderRepository));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<Invoice> getAll() {
		return invoiceRepository.getInvoices();
	}
	
	@GetMapping("/getInvoice/{id}")
	public Invoice getInvoice(@PathVariable int id) {
		return invoiceRepository.getInvoicebyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Invoice invoice) {
		StrategyTrigger trig = new StrategyTrigger(new InvoiceStrategy(invoiceRepository, invoice));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new InvoiceStrategy(invoiceRepository, id));
		trig.executeDelete();
	}
}
