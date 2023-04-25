package com.inventory.facade;

import com.inventory.api.Facade;
import com.inventory.factory.CommunicationFactory;
import com.inventory.repository.InvoiceRepository;

public class SendMessage extends Facade{

	@Override
	protected void udpTrigger(String msg) {
		// TODO Auto-generated method stub
		new CommunicationFactory().getObject().triggerServerClient(msg);	
	}
	
	public static void message(String msg) {
		
		SendMessage send = new SendMessage();
		send.udpTrigger(msg);
	
	}

	@Override
	protected void pdfGen(int id, InvoiceRepository invoiceRepository) {
		// TODO Auto-generated method stub
		
	}

}
