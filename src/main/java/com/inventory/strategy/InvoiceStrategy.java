package com.inventory.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inventory.api.StrategyAPI;
import com.inventory.facade.PDFGen;
import com.inventory.facade.createPDF;
import com.inventory.model.Invoice;
import com.inventory.model.PurchaseOrder;
import com.inventory.repository.InvoiceRepository;
import com.inventory.repository.PurchaseOrderRepository;

public class InvoiceStrategy implements StrategyAPI{

	private InvoiceRepository invoiceRepository;
	private int id;
	private Invoice invoice;
	private PurchaseOrderRepository purchaseOrderRepository;
	
	public InvoiceStrategy(InvoiceRepository invoiceRepository, Invoice invoice) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.invoice = invoice;
	}

	public InvoiceStrategy(InvoiceRepository invoiceRepository, int id ,PurchaseOrderRepository purchaseOrderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.id = id;
		this.purchaseOrderRepository = purchaseOrderRepository;
	}

	public InvoiceStrategy(InvoiceRepository invoiceRepository, int id) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.id = id;
	}

	@Override
	public void add() {
		PurchaseOrder po = purchaseOrderRepository.getPurchaseOrderbyID(id);
		po.setPaid(true);
		purchaseOrderRepository.update(po);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String paymentDate = formatter.format(date);
		
		Invoice invoice = new Invoice();
		invoice.setPaymentDate(paymentDate);
		invoice.setPurchaseOrder(po);
		int invoiceID = invoiceRepository.save(invoice);
		PDFGen.pdfGenerator(invoiceID, invoiceRepository);
	}

	@Override
	public void update() {
		invoiceRepository.update(invoice);
	}

	@Override
	public void delete() {
		Invoice invoice = invoiceRepository.getInvoicebyID(id);
		invoiceRepository.delete(invoice);
	}

}
