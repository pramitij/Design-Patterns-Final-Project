package com.inventory.facade;

import com.inventory.api.Facade;
import com.inventory.model.Invoice;
import com.inventory.repository.InvoiceRepository;

public class PDFGen extends Facade{

	@Override
	protected void udpTrigger(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pdfGen(int invoiceID, InvoiceRepository invoiceRepository) {
		Invoice insertedInvoice = invoiceRepository.getInvoicebyID(invoiceID);
		createPDF pdf = new createPDF();
		pdf.generatePDF(insertedInvoice);
	}

	public static void pdfGenerator(int invoiceID, InvoiceRepository invoiceRepository) {
		new PDFGen().pdfGen(invoiceID, invoiceRepository);
	}
}
