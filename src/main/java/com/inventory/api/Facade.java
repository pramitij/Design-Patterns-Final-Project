package com.inventory.api;

import com.inventory.repository.InvoiceRepository;

public abstract class Facade {

	protected abstract void udpTrigger(String msg);
	
	protected abstract void pdfGen(int id, InvoiceRepository invoiceRepository);
	
}
